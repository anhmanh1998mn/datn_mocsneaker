package com.example.manhvan.datn_mocsneaker.View;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.manhvan.datn_mocsneaker.R;

public class MainChiTietDonNhap extends AppCompatActivity {
    private ActionBar actionBar;
    private TextView txtMaDonNhapCT,txtNgayLapCT,txtTongSL,txtTongTienCT;
    private Spinner spinner;
    private RecyclerView recyclerViewCT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chi_tiet_don_nhap);
        actionBar=getSupportActionBar();
        actionBar.setTitle("Chi tiết đơn nhập hàng");
        actionBar.setDisplayHomeAsUpEnabled(true);
        initView();
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
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}