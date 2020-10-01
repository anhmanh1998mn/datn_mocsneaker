package com.example.manhvan.datn_mocsneaker.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.manhvan.datn_mocsneaker.Presenter.PreCheckRegister;
import com.example.manhvan.datn_mocsneaker.R;

public class MainRegister extends AppCompatActivity implements View.OnClickListener,CheckRegister {
    private EditText edtPassword1,edtConfirmPass,edtFullName,edtPhone,edtAddress,edtUserName;
    private ImageButton btnShow,btnHinde,btnShow2,btnHide2;
    private Button btnRegister;
    private boolean checkPass=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_register);
        initView();
        evenClick();
        confirmPassword();

    }

    private void initView() {
        edtPassword1=findViewById(R.id.edt_regisPassword);
        btnShow=findViewById(R.id.btn_show1);
        btnHinde=findViewById(R.id.btn_hide);
        btnShow2=findViewById(R.id.btn_show2);
        btnHide2=findViewById(R.id.btn_hide2);
        edtConfirmPass=findViewById(R.id.edt_confirmPass);
        btnRegister=findViewById(R.id.btn_regisSucess);
        edtFullName=findViewById(R.id.edt_regisHoTen);
        edtPhone=findViewById(R.id.edt_regisPhone);
        edtAddress=findViewById(R.id.edt_regisAddress);
        edtUserName=findViewById(R.id.edt_regisUsername);
    }
    private void evenClick() {
        btnShow.setOnClickListener(this);
        btnHinde.setOnClickListener(this);
        btnHide2.setOnClickListener(this);
        btnShow2.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_show1:{
                btnShow.setVisibility(View.GONE);
                btnHinde.setVisibility(View.VISIBLE);
                edtPassword1.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                break;
            }
            case R.id.btn_hide:{
                edtPassword1.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                btnShow.setVisibility(View.VISIBLE);
                btnHinde.setVisibility(View.GONE);
                break;
            }
            case R.id.btn_show2:{
                btnShow2.setVisibility(View.GONE);
                btnHide2.setVisibility(View.VISIBLE);
                edtConfirmPass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                break;
            }
            case R.id.btn_hide2:{
                edtConfirmPass.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
                btnShow2.setVisibility(View.VISIBLE);
                btnHide2.setVisibility(View.GONE);
                break;
            }
            case R.id.btn_regisSucess:{
                if(edtConfirmPass.getText().toString().trim().equals("")||checkPass==false){
                    return;
                }
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        PreCheckRegister preCheckRegister=new PreCheckRegister(MainRegister.this);
                        preCheckRegister.checkRegister(edtUserName.getText().toString().trim(),edtPhone.getText().toString().trim(),
                                edtFullName.getText().toString().trim(),edtConfirmPass.getText().toString().trim(),
                                edtConfirmPass.getText().toString().trim());
                    }
                }).start();
                break;
            }
        }
    }

    public void confirmPassword(){
        edtConfirmPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                Toast.makeText(MainRegister.this,charSequence.toString(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void afterTextChanged(Editable editable) {
//                Toast.makeText(MainRegister.this,editable.toString(),Toast.LENGTH_SHORT).show();
                if(editable.toString().equals(edtPassword1.getText().toString().trim())){
                    checkPass=true;
                    return;
                }
                edtConfirmPass.setError("Mật khẩu xác nhận không đúng");
                checkPass=false;
            }
        });


        edtPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                edtUserName.setText(editable.toString().trim());
            }
        });
    }

    @Override
    public void checkNull() {
        btnRegister.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainRegister.this,"Các thông tin không được bỏ trống",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void tonTai() {
        btnRegister.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainRegister.this,"Số điện thoại đã được đăng ký",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void failed() {
        startActivity(new Intent(this,MainVertifyOTP.class));
    }
}