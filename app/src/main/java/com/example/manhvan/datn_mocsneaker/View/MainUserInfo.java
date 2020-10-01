package com.example.manhvan.datn_mocsneaker.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.manhvan.datn_mocsneaker.R;

public class MainUserInfo extends AppCompatActivity implements View.OnClickListener {
    private Button btnUserThietLapTaiKhoan;
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
    }

    public void eventClick(){
        btnUserThietLapTaiKhoan.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_ChangePass:{
                startActivity(new Intent(MainUserInfo.this,MainXacNhanMatKhau.class));
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