package com.example.manhvan.datn_mocsneaker.View;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.example.manhvan.datn_mocsneaker.R;

public class MainOrderDetail extends AppCompatActivity {
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_order_detail);
        actionBar=getSupportActionBar();
        actionBar.setTitle("Chi tiết đơn hàng");
    }
}