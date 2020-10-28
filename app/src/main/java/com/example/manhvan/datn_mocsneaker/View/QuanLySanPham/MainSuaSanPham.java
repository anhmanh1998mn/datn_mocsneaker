package com.example.manhvan.datn_mocsneaker.View.QuanLySanPham;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.manhvan.datn_mocsneaker.R;

public class MainSuaSanPham extends AppCompatActivity {
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sua_san_pham);
        actionBar=getSupportActionBar();
        actionBar.setTitle("Sửa thông tin sản phẩm");
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}