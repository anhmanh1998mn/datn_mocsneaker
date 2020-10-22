package com.example.manhvan.datn_mocsneaker.View;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.manhvan.datn_mocsneaker.R;

public class MainQuenMatKhau1 extends AppCompatActivity {
    private ActionBar actionBar;
    private EditText edtGuiLaiPhone;
    private Button btnGuiLaiTiep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_quen_mat_khau1);
        actionBar=getSupportActionBar();
        actionBar.setTitle("Gửi lại mật khẩu");
        actionBar.setDisplayHomeAsUpEnabled(true);
        initView();
    }

    private void initView() {
        edtGuiLaiPhone=findViewById(R.id.edt_guiLaiSDT);
        btnGuiLaiTiep=findViewById(R.id.btn_guiLaiTiepTheo);
        btnGuiLaiTiep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtGuiLaiPhone.getText().toString().trim().equals("")){
                    edtGuiLaiPhone.setError("Số điện thoại không được bỏ trống");
                    return;
                }
                Intent intent=new Intent(MainQuenMatKhau1.this,MainGuiMaXacThucQuenMK.class);
                intent.putExtra("soDienThoai",edtGuiLaiPhone.getText().toString().trim());
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
