package com.example.manhvan.datn_mocsneaker.View;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.manhvan.datn_mocsneaker.R;

public class MainQLNhapHangDanhSach extends AppCompatActivity {
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_qlnhap_hang_danh_sach);
        actionBar=getSupportActionBar();
        actionBar.setTitle("Quản lý nhập hàng");
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
