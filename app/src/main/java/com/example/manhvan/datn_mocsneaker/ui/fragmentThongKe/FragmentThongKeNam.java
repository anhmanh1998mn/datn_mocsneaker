package com.example.manhvan.datn_mocsneaker.ui.fragmentThongKe;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.manhvan.datn_mocsneaker.Model.MoThongKeTheoNam;
import com.example.manhvan.datn_mocsneaker.Presenter.PreThongKeTheoNam;
import com.example.manhvan.datn_mocsneaker.R;
import com.example.manhvan.datn_mocsneaker.entity.NamThongKe;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FragmentThongKeNam extends Fragment implements View.OnClickListener, PreThongKeTheoNam.ThongKeKetQuaInterface {
    private TextView txtDate,txtDoanhThuNam;
    private Button btnThongKe;
    private PreThongKeTheoNam preThongKeTheoNam;
    private List<NamThongKe> arrThongKe;
    private BarChart barChart;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_thong_ke_nam,container,false);
        initView(view);
        getDateNow();
        onClickButton();
        layDoanhThuHangThang();

        return view;
    }
    private void initView(View view) {
        txtDate=view.findViewById(R.id.txt_dateTK1);
        btnThongKe=view.findViewById(R.id.btn_thongKe1);
        txtDoanhThuNam=view.findViewById(R.id.txt_doanhTHuNam);
        barChart=view.findViewById(R.id.bar_chart);
    }

    private void getDateNow(){
        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        int day=calendar.get(Calendar.DATE);
        calendar.set(year,month,day);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy");
        txtDate.setText(simpleDateFormat.format(calendar.getTime()));
    }

    private void showDatepickerDialog(){
        final Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        int day=calendar.get(Calendar.DATE);
        DatePickerDialog datePickerDialog=new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(i,i1,i2);
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy");
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
            case R.id.txt_dateTK1:{
                showDatepickerDialog();
                break;
            }
            case R.id.btn_thongKe1:{
                layDoanhThuHangThang();
                break;
            }
        }
    }
    private void layDoanhThuHangThang(){
        preThongKeTheoNam=new PreThongKeTheoNam(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                preThongKeTheoNam.thongKeNam(txtDate.getText().toString());
            }
        }).start();
    }

    @Override
    public void thongKeBanThanhCong() {
        txtDate.post(new Runnable() {
            @Override
            public void run() {
                //Toast.makeText(getActivity(), MoThongKeTheoNam.arrBan.size()+"",Toast.LENGTH_SHORT).show();
                //Log.d("TongBan", MoThongKeTheoNam.arrBan.size()+"-"+MoThongKeTheoNam.arrNhap.size());
            }
        });
    }

    @Override
    public void thongKeNhapThanhCong() {
        txtDate.post(new Runnable() {
            @Override
            public void run() {
                arrThongKe=new ArrayList<>();
                for(int i = 0; i< MoThongKeTheoNam.arrBan.size(); i++){
                    for(int j=0;j<MoThongKeTheoNam.arrNhap.size();j++){

                        if(MoThongKeTheoNam.arrBan.get(i).getThang().contains(MoThongKeTheoNam.arrNhap.get(j).getThang())){
                            arrThongKe.add(new NamThongKe(MoThongKeTheoNam.arrBan.get(i).getThang(),String.valueOf(Integer.parseInt(
                                    MoThongKeTheoNam.arrBan.get(i).getTongtien()
                            )-Integer.parseInt(MoThongKeTheoNam.arrNhap.get(j).getTongtien()))));
                        }
                        else {
                            arrThongKe.add(
                                    new NamThongKe(MoThongKeTheoNam.arrBan.get(i).getThang(),String.valueOf(Integer.parseInt(
                                    MoThongKeTheoNam.arrBan.get(i).getTongtien()
                            ))));
//                            arrThongKe.add(new NamThongKe(MoThongKeTheoNam.arrNhap.get(j).getThang(),String.valueOf(0-Integer.parseInt(MoThongKeTheoNam.arrNhap.get(j).getTongtien()))));
                        }
                    }
                }

                int tong=0;
                for(int i=0;i<arrThongKe.size();i++){
                    tong+=Integer.parseInt(arrThongKe.get(i).getTongtien());
                    Log.d("W11111",arrThongKe.get(i).getTongtien());
                }
                DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
                txtDoanhThuNam.setText("Tổng doanh thu: "+decimalFormat.format(tong)+"đ");
//                Log.d("TongBan", MoThongKeTheoNam.arrBan.get(0).getTongtien()+"-"
//                        +MoThongKeTheoNam.arrNhap.get(0).getTongtien()+"-"+arrThongKe.get(0).getTongtien()+"");

                //show bar chart
                BarDataSet barDataSet=new BarDataSet(dataValues(),"");
                barDataSet.setColor(Color.RED);
                BarData barData=new BarData();
                barData.addDataSet(barDataSet);
                barData.setValueTextSize(13);
                barChart.setData(barData);
                barChart.invalidate();
            }
        });
    }

    private ArrayList<BarEntry> dataValues(){

        ArrayList<BarEntry> dataV=new ArrayList<>();
        for(int i=0;i<arrThongKe.size();i++){
            dataV.add(new BarEntry(Integer.parseInt(arrThongKe.get(i).getThang()),Integer.parseInt(arrThongKe.get(i).getTongtien())));
        }
//        dataV.add(new BarEntry(1,400000));
//        dataV.add(new BarEntry(2,600000));
//        dataV.add(new BarEntry(3,400000));
//        dataV.add(new BarEntry(4,400000));
//        dataV.add(new BarEntry(5,200000));
//        dataV.add(new BarEntry(6,400000));
//        dataV.add(new BarEntry(7,400000));
//        dataV.add(new BarEntry(8,Integer.parseInt(arrThongKe.get(2).getTongtien())));
//        dataV.add(new BarEntry(9,Integer.parseInt(arrThongKe.get(1).getTongtien())));
//        dataV.add(new BarEntry(10,Integer.parseInt(arrThongKe.get(0).getTongtien())));
//        dataV.add(new BarEntry(11,300000));
//        dataV.add(new BarEntry(12,400000));
        return dataV;
    }
}
