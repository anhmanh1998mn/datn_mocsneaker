package com.example.manhvan.datn_mocsneaker.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.manhvan.datn_mocsneaker.Presenter.PreDoiMatKhau;
import com.example.manhvan.datn_mocsneaker.R;

import java.security.NoSuchAlgorithmException;

public class MainDoiMatKhau extends AppCompatActivity implements DoiMatKhauInterface{
    private ActionBar actionBar;
    private EditText edtMatKhauMoi,edtXacNhanMK;
    private PreDoiMatKhau preDoiMatKhau;
    private Button btn_doiMatKhau;
    private boolean checkPass=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_doi_mat_khau);
        actionBar=getSupportActionBar();
        actionBar.setTitle("Gửi lại mật khẩu");
        actionBar.setDisplayHomeAsUpEnabled(true);

        initView();
        kiemTraXacNhanMatKhau();
        getData();
    }

    private void kiemTraXacNhanMatKhau() {


        edtXacNhanMK.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().trim().equals(edtMatKhauMoi.getText().toString().trim())){
                    checkPass=true;
                    return;
                }
                checkPass=false;
                edtXacNhanMK.setError("Mật khẩu nhập lại không chính xác");
            }
        });
    }

    private void initView() {
        edtMatKhauMoi=findViewById(R.id.edt_doimatkhau1);
        edtXacNhanMK=findViewById(R.id.edt_doimatkhau2);
        btn_doiMatKhau=findViewById(R.id.btn_doiMatKhau);
    }

    private void getData() {
        btn_doiMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preDoiMatKhau=new PreDoiMatKhau(MainDoiMatKhau.this);
                final Intent intent=getIntent();
                if(checkPass==false || edtMatKhauMoi.getText().toString().trim().equals("")){
                    Toast.makeText(MainDoiMatKhau.this,"Vui lòng kiểm tra lại các thông tin",Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    preDoiMatKhau.doiMatKhau(intent.getStringExtra("MatKhau"),edtXacNhanMK.getText().toString().trim());
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
        });
        //Toast.makeText(this,intent.getStringExtra("MatKhau"),Toast.LENGTH_SHORT).show();
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

    @Override
    public void onSuccessed() {
        btn_doiMatKhau.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainDoiMatKhau.this,"Đổi mật khẩu thành công",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainDoiMatKhau.this,MainUserInfo.class));
                finish();
            }
        });
    }

    @Override
    public void onFailed() {

    }
}