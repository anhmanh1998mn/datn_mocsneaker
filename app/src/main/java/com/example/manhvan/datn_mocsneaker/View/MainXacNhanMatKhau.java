package com.example.manhvan.datn_mocsneaker.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.manhvan.datn_mocsneaker.R;

public class MainXacNhanMatKhau extends AppCompatActivity implements View.OnClickListener {
    private Button btnTiepTuc;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_xac_nhan_mat_khau);
        actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Gửi lại mật khẩu");
        initView();
        eventClick();
    }
    private void initView(){
        btnTiepTuc=findViewById(R.id.btn_tieptucMKDoi);
    }
    private void eventClick() {
        btnTiepTuc.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(this,MainDoiMatKhau.class));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                finish();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}