package com.example.manhvan.datn_mocsneaker.View;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.manhvan.datn_mocsneaker.R;

public class MainRegister extends AppCompatActivity implements View.OnClickListener {
    private EditText edtPassword1,edtConfirmPass;
    private ImageButton btnShow,btnHinde,btnShow2,btnHide2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_register);
        initView();
        evenClick();
    }

    private void initView() {
        edtPassword1=findViewById(R.id.edt_regisPassword);
        btnShow=findViewById(R.id.btn_show1);
        btnHinde=findViewById(R.id.btn_hide);
        btnShow2=findViewById(R.id.btn_show2);
        btnHide2=findViewById(R.id.btn_hide2);
        edtConfirmPass=findViewById(R.id.edt_confirmPass);
    }
    private void evenClick() {
        btnShow.setOnClickListener(this);
        btnHinde.setOnClickListener(this);
        btnHide2.setOnClickListener(this);
        btnShow2.setOnClickListener(this);
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
        }
    }
}