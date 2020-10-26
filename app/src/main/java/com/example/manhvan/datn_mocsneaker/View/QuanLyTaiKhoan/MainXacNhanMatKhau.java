package com.example.manhvan.datn_mocsneaker.View.QuanLyTaiKhoan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.manhvan.datn_mocsneaker.R;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainXacNhanMatKhau extends AppCompatActivity implements View.OnClickListener {
    private Button btnTiepTuc;
    private ActionBar actionBar;
    private EditText editTextMK;

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
        editTextMK=findViewById(R.id.edt_matkhauDoi);
    }
    private void eventClick() {
        btnTiepTuc.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("thongtin");
        String matkhau=editTextMK.getText().toString().trim();
//        Toast.makeText(this,bundle.getString("matkhau"),Toast.LENGTH_SHORT).show();

        try {
            if (maHoaMD5(matkhau).equals(bundle.getString("matkhau"))){
                Intent intent1=new Intent(this, MainDoiMatKhau.class);
                intent1.putExtra("MatKhau",bundle.getString("idtaikhoan"));
                Log.d("idDoiMK",bundle.getString("idtaikhoan"));
                startActivity(intent1);
                finish();
                return;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        Toast.makeText(this,"Mật khẩu hiện tại không chính xác",Toast.LENGTH_SHORT).show();
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
    private String maHoaMD5(String matkhau) throws NoSuchAlgorithmException {
        String result="";
        MessageDigest md=MessageDigest.getInstance("MD5");
        md.update(matkhau.getBytes());
        BigInteger bigInteger=new BigInteger(1,md.digest());
        result=bigInteger.toString(16);
        return result;
    }

}