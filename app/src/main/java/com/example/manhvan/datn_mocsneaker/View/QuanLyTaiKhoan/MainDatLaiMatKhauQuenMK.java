package com.example.manhvan.datn_mocsneaker.View.QuanLyTaiKhoan;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.manhvan.datn_mocsneaker.Presenter.PreDatLaiMatKhauQuen;
import com.example.manhvan.datn_mocsneaker.R;

import java.security.NoSuchAlgorithmException;

public class MainDatLaiMatKhauQuenMK extends AppCompatActivity implements PreDatLaiMatKhauQuen.KetQuaDatLaiMKInterface{
    private ActionBar actionBar;
    private EditText edtMatKhau;
    private Button btnDongY;
    private PreDatLaiMatKhauQuen preDatLaiMatKhauQuen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dat_lai_mat_khau_quen_mk);
        actionBar=getSupportActionBar();
        actionBar.setTitle("Gửi lại mật khẩu");
        actionBar.setDisplayHomeAsUpEnabled(true);
        initView();
        datLaiMatKhau();
    }

    private void initView() {
        edtMatKhau=findViewById(R.id.edt_matKhauDatLai);
        btnDongY=findViewById(R.id.btn_dongY);
    }
    private void datLaiMatKhau(){
        final Intent intent=getIntent();

        btnDongY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtMatKhau.getText().toString().trim().equals("")){
                    edtMatKhau.setError("Kiểm tra lại dữ liệu");
                    return;
                }
                preDatLaiMatKhauQuen=new PreDatLaiMatKhauQuen(MainDatLaiMatKhauQuenMK.this);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            preDatLaiMatKhauQuen.datLaiMatKhau(edtMatKhau.getText().toString().trim(),intent.getStringExtra("phoneSDT").trim());
                        } catch (NoSuchAlgorithmException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }

    @Override
    public void onSuccessed() {
        edtMatKhau.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainDatLaiMatKhauQuenMK.this,"Lấy lại mật khẩu thành công",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainDatLaiMatKhauQuenMK.this, MainLogin.class));
                finish();
            }
        });
    }

    @Override
    public void onFailed() {

    }
}
