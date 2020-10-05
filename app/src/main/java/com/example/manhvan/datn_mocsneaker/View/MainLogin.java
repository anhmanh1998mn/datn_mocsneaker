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
import android.widget.EditText;
import android.widget.Toast;

import com.example.manhvan.datn_mocsneaker.MainActivity2;
import com.example.manhvan.datn_mocsneaker.Presenter.PreDangNhap;
import com.example.manhvan.datn_mocsneaker.R;

import java.security.NoSuchAlgorithmException;

public class MainLogin extends AppCompatActivity implements View.OnClickListener,DangNhap {
    private ActionBar actionBar;
    private EditText edtAccountName,edtAccountPassword;
    Button btnLogin,btnRegister,btnQuenMK;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_loginn);
        actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Đăng nhập.");
        initView();
        eventClick();
    }

    private void initView() {
        edtAccountName=findViewById(R.id.edt_username);
        edtAccountPassword=findViewById(R.id.edt_password);
        btnLogin=findViewById(R.id.btn_login);
        btnRegister=findViewById(R.id.btn_register);
        btnQuenMK=findViewById(R.id.btn_unpassword);
    }

    private void eventClick() {
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        btnQuenMK.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login:{
                login();
                break;
            }
            case R.id.btn_register:{
                startActivity(new Intent(this,MainRegister.class));
                finish();
                break;
            }
            case R.id.btn_unpassword:{
                break;
            }
        }
    }

    private void login() {
        final PreDangNhap preDangNhap=new PreDangNhap(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    preDangNhap.dangNhapTaiKhoan(edtAccountName.getText().toString().trim(),edtAccountPassword.getText().toString().trim());
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void thanhCong(String quyen) {
        btnLogin.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainLogin.this,"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
            }
        });
        Intent intent=new Intent(this, MainActivity2.class);
        SharedPreferences sharedPreferences=getSharedPreferences("QuyenTK", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("quyen",quyen);
        editor.putString("phone",edtAccountName.getText().toString().trim());
        editor.commit();
        startActivity(intent);

        finish();
    }

    @Override
    public void thatBai() {
        btnLogin.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainLogin.this,"Kiểm tra lại tài khoản, mật khẩu",Toast.LENGTH_SHORT).show();
            }
        });
    }
}