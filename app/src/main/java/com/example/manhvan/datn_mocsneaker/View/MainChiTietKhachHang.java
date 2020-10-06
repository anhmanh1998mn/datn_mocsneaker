package com.example.manhvan.datn_mocsneaker.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.manhvan.datn_mocsneaker.R;

public class MainChiTietKhachHang extends AppCompatActivity {
    private TextView txt1,txt2,txt3,txt4;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_chi_tiet_khach_hang);
        actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Quản lý khách hàng");
        initView();
        getData();
    }

    private void getData() {
        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("thongTin");
        txt1.setText("Tên khách hàng: "+bundle.getString("hoTen"));
        txt2.setText("Tên đăng nhập: "+bundle.getString("soDT"));
        txt3.setText("Số điện thoại: "+bundle.getString("soDT"));
        txt4.setText("Địa chỉ: "+bundle.getString("diaChi"));
    }

    private void initView() {
        txt1=findViewById(R.id.txt_khHT);
        txt2=findViewById(R.id.txt_khPhone);
        txt3=findViewById(R.id.txt_khDiaChi);
        txt4=findViewById(R.id.txt_khTK);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}