package com.example.manhvan.datn_mocsneaker.View.QuanLyTaiKhoan;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.manhvan.datn_mocsneaker.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class MainGuiMaXacThucQuenMK extends AppCompatActivity implements View.OnClickListener{
    private ActionBar actionBar;
    private String phoneNumber;
    private EditText edtOTP;
    private Button btnOTP, btnGuiLaiOTP;
    private String mVerificationId;
    private FirebaseAuth mAuth;
    private PhoneAuthProvider.ForceResendingToken mtoken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_gui_ma_xac_thuc_quen_mk);
        actionBar=getSupportActionBar();
        actionBar.setTitle("Gửi lại mật khẩu");
        actionBar.setDisplayHomeAsUpEnabled(true);
        initView();
        nhanSoDienThoai();
        mAuth = FirebaseAuth.getInstance();
        sendVerificationCode();
        eventClick();
    }
    private void eventClick() {
        btnOTP.setOnClickListener(this);
        btnGuiLaiOTP.setOnClickListener(this);
    }
    private void initView() {
        edtOTP = findViewById(R.id.edt_otp1);
        btnOTP = findViewById(R.id.btn_otp1);
        btnGuiLaiOTP = findViewById(R.id.btn_otp11);
    }
    private void sendVerificationCode() {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+84" + phoneNumber, 60, TimeUnit.SECONDS, this, mCallbacks
        );
    }

    private void resendVerificationCode(String phoneNumber,
                                        PhoneAuthProvider.ForceResendingToken token) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+84" + phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks,         // OnVerificationStateChangedCallbacks
                token);             // ForceResendingToken from callbacks
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            //Getting the code sent by SMS
//            String code = phoneAuthCredential.getSmsCode();

        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(MainGuiMaXacThucQuenMK.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            mVerificationId = s;
            mtoken = forceResendingToken;
        }
    };

    private void verifyVerificationCode(String otp) {
        //creating the credential
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, otp);
        //signing the user
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(MainGuiMaXacThucQuenMK.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent=new Intent(MainGuiMaXacThucQuenMK.this, MainDatLaiMatKhauQuenMK.class);
                            intent.putExtra("phoneSDT",phoneNumber);
                            startActivity(intent);
                            finish();
//                            Toast.makeText(MainGuiMaXacThucQuenMK.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(MainGuiMaXacThucQuenMK.this, "Đăng ký thất bại1", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void nhanSoDienThoai() {
        Intent intent=getIntent();
        phoneNumber=(intent.getStringExtra("soDienThoai"));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_otp1: {
                verifyVerificationCode(edtOTP.getText().toString().trim());
                break;
            }
            case R.id.btn_otp11: {
                resendVerificationCode(phoneNumber, mtoken);
                break;
            }
        }
    }
}
