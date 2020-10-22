package com.example.manhvan.datn_mocsneaker.ui.fragmentThongKe;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.manhvan.datn_mocsneaker.Model.MoThongKeThang;
import com.example.manhvan.datn_mocsneaker.Presenter.PreThongKeThang;
import com.example.manhvan.datn_mocsneaker.R;
import com.example.manhvan.datn_mocsneaker.adapter.SanPhamThongKeAdapter;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class FragmentThongKeThang extends Fragment implements View.OnClickListener, PreThongKeThang.KetQuaTinhTienInterface {
    private TextView txtDate,txtTongDoanhThu;
    private Button btnThongKe;
    private int year,month,day;
    private PreThongKeThang preThongKeThang;
    private String tiennhapTC="";
    private String tienBanTC="";
    private PieChart pieChart;
    private SanPhamThongKeAdapter sanPhamThongKeAdapter;
    private RecyclerView reSanPhamBanNhieu,reSanPhamBanIt;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_thong_ke_thang,container,false);
        initView(view);
        getDateNow();
        onClickButton();
        tinhDoanhThu();
        layDSSanPhamBanNhieu();
        //Toast.makeText(getContext(),txtTienBan.getText()+"-"+txtTienNhap.getText(),Toast.LENGTH_SHORT).show();
        return view;
    }

    private void initView(View view) {
        txtDate=view.findViewById(R.id.txt_dateTK);
        btnThongKe=view.findViewById(R.id.btn_thongKe);
        txtTongDoanhThu=view.findViewById(R.id.txt_tongDoanhThu);
        pieChart=view.findViewById(R.id.pie_chart);
        reSanPhamBanNhieu=view.findViewById(R.id.re_thongKe1);
        reSanPhamBanIt=view.findViewById(R.id.re_thongKe2);
        //aaa
    }

    private void getDateNow(){
        Calendar calendar=Calendar.getInstance();
        int year1=calendar.get(Calendar.YEAR);
        int month1=calendar.get(Calendar.MONTH);
        int day1=calendar.get(Calendar.DATE);
        calendar.set(year1,month1,day1);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("MM/yyyy");
        txtDate.setText(simpleDateFormat.format(calendar.getTime()));
    }

    private void showDatepickerDialog(){
        final Calendar calendar=Calendar.getInstance();
        year=calendar.get(Calendar.YEAR);
        month=calendar.get(Calendar.MONTH);
        day=calendar.get(Calendar.DATE);
        DatePickerDialog datePickerDialog=new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(i,i1,i2);
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("MM/yyyy");
                txtDate.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },year,month,day);
        datePickerDialog.show();
    }
    private void onClickButton(){
        txtDate.setOnClickListener(this);
        btnThongKe.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.txt_dateTK:{
                showDatepickerDialog();
                break;
            }
            case R.id.btn_thongKe:{
                tinhDoanhThu();
                layDSSanPhamBanNhieu();
                break;
            }
        }
    }
    private void tinhDoanhThu(){
        final String[] date=txtDate.getText().toString().split("/");

        preThongKeThang=new PreThongKeThang(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                preThongKeThang.thongKeThang(date[0],date[1]);
            }
        }).start();
//        Toast.makeText(getContext(),date[0]+"-"+date[1],Toast.LENGTH_SHORT).show();

    }

    @Override
    public void tienBan(String tienBan) {
        tienBanTC=tienBan;
        txtDate.post(new Runnable() {
            @Override
            public void run() {
            }
        });
    }

    private void layDSSanPhamBanNhieu(){
        final String[] date=txtDate.getText().toString().split("/");
        new Thread(new Runnable() {
            @Override
            public void run() {
                preThongKeThang.LaySanPhamBanNhieu(date[0],date[1]);
            }
        }).start();
    }

    @Override
    public void tienNhap(final String tienBan) {
        tiennhapTC=tienBan;
        txtDate.post(new Runnable() {
            @Override
            public void run() {
                DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
                txtTongDoanhThu.setText("Tổng doanh thu: "+decimalFormat.format(Integer.parseInt(tienBanTC)-Integer.parseInt(tiennhapTC))+"đ");
                showPieChart();
            }
        });
    }
    private void showPieChart(){
        int[] colorPie=new int[]{Color.RED,Color.BLUE};
        PieDataSet pieDataSet=new PieDataSet(dataValues(),"");
        pieDataSet.setColors(colorPie);

        PieData pieData=new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
        final String[] date=txtDate.getText().toString().split("/");
        pieChart.setCenterText("Tháng "+date[0]);
        pieChart.setHoleRadius(30);
        pieChart.setTransparentCircleRadius(30);

        //Set text size, text color value data
        pieData.setValueTextSize(20);
        pieData.setValueTextColor(Color.WHITE);
    }

    private ArrayList<PieEntry> dataValues(){
        ArrayList<PieEntry>daVal=new ArrayList<>();
        daVal.add(new PieEntry(Integer.parseInt(tienBanTC),"Tiền Bán"));
        daVal.add(new PieEntry(Integer.parseInt(tiennhapTC),"Tiền Nhập"));
        return daVal;
    }

    @Override
    public void thatbai() {

    }

    @Override
    public void dsSanPhamBanNhieu() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayout.VERTICAL,false);
        reSanPhamBanNhieu.setLayoutManager(linearLayoutManager);
        reSanPhamBanNhieu.setNestedScrollingEnabled(false);
        reSanPhamBanNhieu.post(new Runnable() {
            @Override
            public void run() {
                sanPhamThongKeAdapter=new SanPhamThongKeAdapter(getActivity(),R.layout.item_san_pham_thong_ke, MoThongKeThang.arrSPBanNhieu);
                reSanPhamBanNhieu.setAdapter(sanPhamThongKeAdapter);
                sanPhamThongKeAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void dsSanPhamBanIt() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayout.VERTICAL,false);
        reSanPhamBanIt.setLayoutManager(linearLayoutManager);
        reSanPhamBanIt.setNestedScrollingEnabled(false);
        reSanPhamBanIt.post(new Runnable() {
            @Override
            public void run() {
                sanPhamThongKeAdapter=new SanPhamThongKeAdapter(getActivity(),R.layout.item_san_pham_thong_ke,MoThongKeThang.arrSPBanIt);
                reSanPhamBanIt.setAdapter(sanPhamThongKeAdapter);
                sanPhamThongKeAdapter.notifyDataSetChanged();
            }
        });
    }
}
