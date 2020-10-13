package com.example.manhvan.datn_mocsneaker.View;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.manhvan.datn_mocsneaker.R;
import com.example.manhvan.datn_mocsneaker.adapter.GioHangAdapter;
import com.example.manhvan.datn_mocsneaker.util.GioHang;

import java.text.DecimalFormat;

public class ViewGioHang extends AppCompatActivity implements  GioHangAdapter.OnDialogCloseListener{
    private ActionBar actionBar;
    private RecyclerView recyclerView;
    private GioHangAdapter adapter;
    private Button btnTongTien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_gio_hang);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Giỏ hàng");
        initView();
        hienRecycleView();
        tongTien();

    }

    @Override
    protected void onResume() {
        super.onResume();
        tongTien();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }



    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void hienRecycleView() {
        adapter = new GioHangAdapter(this, R.layout.itemgiohang, GioHang.arrGioHang);
        adapter.setListener(this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    private void tongTien() {
        int tong = 0;
        if (GioHang.arrGioHang == null) {
            return;
        }
        for (int i = 0; i < GioHang.arrGioHang.size(); i++) {
            tong += GioHang.arrGioHang.get(i).getDonGia() * GioHang.arrGioHang.get(i).getSoLuong();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        btnTongTien.setText("Tổng tiền: " + decimalFormat.format(tong) + " đ");
    }

    private void initView() {
        btnTongTien = findViewById(R.id.btn_tongTien);
        recyclerView = findViewById(R.id.giohang_recycle);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayout.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDialogClose() {
        tongTien();
    }
}