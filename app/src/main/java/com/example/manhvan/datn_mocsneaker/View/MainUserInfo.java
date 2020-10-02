package com.example.manhvan.datn_mocsneaker.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.manhvan.datn_mocsneaker.MainActivity2;
import com.example.manhvan.datn_mocsneaker.R;

public class MainUserInfo extends AppCompatActivity implements View.OnClickListener {
    private Button btnUserThietLapTaiKhoan,btnDangXuat;
    private ActionBar actionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user_info);
        actionBar=getSupportActionBar();
        actionBar.setTitle("Thiết lập tài khoản");
        actionBar.setDisplayHomeAsUpEnabled(true);
        initView();
        eventClick();
    }

    private void initView() {
        btnUserThietLapTaiKhoan=findViewById(R.id.btn_ChangePass);
        btnDangXuat=findViewById(R.id.btn_dangXuat);
    }

    public void eventClick(){
        btnUserThietLapTaiKhoan.setOnClickListener(this);
        btnDangXuat.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_ChangePass:{
                startActivity(new Intent(MainUserInfo.this,MainXacNhanMatKhau.class));
                break;
            }
            case R.id.btn_dangXuat:{
                SharedPreferences sharedPreferences=getSharedPreferences("QuyenTK", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("quyen","");
                editor.commit();
                startActivity(new Intent(this, MainActivity2.class));
                finish();
                break;
            }
        }
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