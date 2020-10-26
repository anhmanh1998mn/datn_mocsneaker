package com.example.manhvan.datn_mocsneaker.View.QuanLyDonNhapHang;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.manhvan.datn_mocsneaker.R;
import com.example.manhvan.datn_mocsneaker.adapter.QLNhapHangAdapter;

public class MainQLNhapHangDanhSach extends AppCompatActivity implements View.OnClickListener {
    private ActionBar actionBar;
    private TabLayout tableLayout;
    private ViewPager viewPager;
    private Button btnLapDanhSach;
    private QLNhapHangAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_qlnhap_hang_danh_sach);
        actionBar=getSupportActionBar();
        actionBar.setTitle("Quản lý nhập hàng");
        actionBar.setDisplayHomeAsUpEnabled(true);
        initView();
        initViewpager();

    }


    private void initViewpager() {
        FragmentManager fragmentManager=getSupportFragmentManager();
        adapter=new QLNhapHangAdapter(fragmentManager);
        viewPager.setAdapter(adapter);
        tableLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tableLayout));
        tableLayout.setTabsFromPagerAdapter(adapter);
        tableLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
    }

    private void initView() {
        tableLayout=findViewById(R.id.tblayout_qlnh);
        viewPager=findViewById(R.id.viewpager_qlnh);
        btnLapDanhSach=findViewById(R.id.btn_themDonNhap);
        btnLapDanhSach.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_themDonNhap:{
                startActivity(new Intent(MainQLNhapHangDanhSach.this, MainQLThemDonNhapHnag.class));
                break;
            }
        }
    }
}
