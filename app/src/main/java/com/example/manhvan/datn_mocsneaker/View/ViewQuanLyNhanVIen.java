package com.example.manhvan.datn_mocsneaker.View;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.manhvan.datn_mocsneaker.R;

public class ViewQuanLyNhanVIen extends AppCompatActivity implements View.OnClickListener{
    private Toolbar toolbar;
    private Button btnThemNV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_quan_ly_nhan_vien);
        toolbar=findViewById(R.id.tb1);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
        clickButton();
    }

    private void clickButton() {
        btnThemNV.setOnClickListener(this);
    }

    private void initView() {
        btnThemNV=findViewById(R.id.btn_themVN);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_themVN:{
                startActivity(new Intent(ViewQuanLyNhanVIen.this,ViewThemNhanVien.class));
                break;
            }
        }
    }
}
