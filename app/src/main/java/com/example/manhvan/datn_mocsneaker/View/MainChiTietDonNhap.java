package com.example.manhvan.datn_mocsneaker.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manhvan.datn_mocsneaker.Model.MoLayChiTietDonNhap;
import com.example.manhvan.datn_mocsneaker.Presenter.PreLayChiTietDonNhap;
import com.example.manhvan.datn_mocsneaker.R;
import com.example.manhvan.datn_mocsneaker.adapter.ChiTietDNLayDSAdapter;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainChiTietDonNhap extends AppCompatActivity implements LayCTDonNhapInterface{
    private ActionBar actionBar;
    private TextView txtMaDonNhapCT,txtNgayLapCT,txtTongSL,txtTongTienCT;
    private Spinner spinner;
    private RecyclerView recyclerViewCT;
    private ArrayList<String> arrSpinner;
    private int maDonNhap=0;
    private ChiTietDNLayDSAdapter adapter;
    private PreLayChiTietDonNhap preLayChiTietDonNhap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chi_tiet_don_nhap);
        actionBar=getSupportActionBar();
        actionBar.setTitle("Chi tiết đơn nhập hàng");
        actionBar.setDisplayHomeAsUpEnabled(true);
        initView();
        checkRole();
        nhanDuLieu();
        layChiTietDN();
//        tinhToan();
    }

    private void initView() {
        txtMaDonNhapCT=findViewById(R.id.txt_maDonNhapCT);
        txtNgayLapCT=findViewById(R.id.txt_ngayLapCT);
        spinner=findViewById(R.id.spinner_CT);
        recyclerViewCT=findViewById(R.id.recycle_viewCT);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this, LinearLayout.VERTICAL,false);
        recyclerViewCT.setLayoutManager(linearLayoutManager);
        txtTongSL=findViewById(R.id.txt_tongSL);
        txtTongTienCT=findViewById(R.id.txt_tongTienCT);

        arrSpinner=new ArrayList<>();
        arrSpinner.add("Duyệt");
        arrSpinner.add("Không duyệt");
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,arrSpinner);
        spinner.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    public void checkRole(){
        SharedPreferences sharedPreferences = getSharedPreferences("QuyenTK", Context.MODE_PRIVATE);
        if (sharedPreferences.getString("quyen", "").equals("2")){
            spinner.setEnabled(false);
            return;
        }
    }
    public void nhanDuLieu(){
        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("ttDonNhap");
        maDonNhap=Integer.parseInt(bundle.getString("idDonNhap"));
        txtMaDonNhapCT.setText(bundle.getString("idDonNhap"));
        txtNgayLapCT.setText(bundle.getString("ngayLap",""));
    }
    public void layChiTietDN(){
        preLayChiTietDonNhap=new PreLayChiTietDonNhap(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                preLayChiTietDonNhap.layChiTietDonNhap(maDonNhap);
            }
        }).start();
    }

    @Override
    public void onSuccessed() {
        recyclerViewCT.post(new Runnable() {
            @Override
            public void run() {
                adapter=new ChiTietDNLayDSAdapter(MainChiTietDonNhap.this,R.layout.item_lay_chi_tiet_don_nhap, MoLayChiTietDonNhap.arrListChiTiet);
                recyclerViewCT.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                tinhToan();
            }
        });


    }

    @Override
    public void onFailed() {
        Toast.makeText(MainChiTietDonNhap.this,"Kiểm tra kết nói mạng",Toast.LENGTH_SHORT).show();
    }

    public void tinhToan(){
        int tongSL=0;
        int tongTien=0;
        for(int i=0;i<MoLayChiTietDonNhap.arrListChiTiet.size();i++){
            tongSL+=Integer.parseInt(MoLayChiTietDonNhap.arrListChiTiet.get(i).getQuantity());
            tongTien+=Integer.parseInt(MoLayChiTietDonNhap.arrListChiTiet.get(i).getQuantity())*Integer.parseInt(MoLayChiTietDonNhap.arrListChiTiet.get(i).getPriceIn());
        }
        txtTongSL.setText("Tổng số hàng: "+tongSL);
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        txtTongTienCT.setText("Tổng tiền nhập: "+decimalFormat.format(tongTien)+"đ");
    }
}