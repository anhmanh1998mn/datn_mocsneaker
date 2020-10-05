package com.example.manhvan.datn_mocsneaker.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.manhvan.datn_mocsneaker.Presenter.PreDangKyTaiKhoanKH;
import com.example.manhvan.datn_mocsneaker.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

public class MainVertifyOTP extends AppCompatActivity implements View.OnClickListener,DangKyTaiKhoanKH {
    private EditText edtOTP;
    private Button btnOTP, btnGuiLaiOTP;
    String hoTen, phone, diaChi, matKhau, mVerificationId;
    private FirebaseAuth mAuth;
    private PhoneAuthProvider.ForceResendingToken mtoken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_vertify_o_t_p);
        initView();
        nhanDuLieuGui();
        mAuth = FirebaseAuth.getInstance();
        sendVerificationCode();
        eventClick();

    }

    private void eventClick() {
        btnOTP.setOnClickListener(this);
        btnGuiLaiOTP.setOnClickListener(this);
    }

    private void sendVerificationCode() {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+84" + phone, 60, TimeUnit.SECONDS, this, mCallbacks
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
            Toast.makeText(MainVertifyOTP.this, e.getMessage(), Toast.LENGTH_LONG).show();
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
                .addOnCompleteListener(MainVertifyOTP.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //Toast.makeText(MainVertifyOTP.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                            dangkyTaiKhoanKhachHang();
                        } else {
                            Toast.makeText(MainVertifyOTP.this, "Đăng ký thất bại1", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void dangkyTaiKhoanKhachHang() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                PreDangKyTaiKhoanKH preDangKyTaiKhoanKH=new PreDangKyTaiKhoanKH(MainVertifyOTP.this);
                try {
                    preDangKyTaiKhoanKH.dangKyTaiKhoanKH(hoTen,phone,diaChi,matKhau);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void nhanDuLieuGui() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("thongTin");
        hoTen = bundle.getString("hoTen");
        diaChi = bundle.getString("diaChi");
        phone = bundle.getString("taiKhoan");
        matKhau = bundle.getString("matKhau");
    }

    private void initView() {
        edtOTP = findViewById(R.id.edt_otp);
        btnOTP = findViewById(R.id.btn_otp);
        btnGuiLaiOTP = findViewById(R.id.btn_otp1);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_otp: {
                verifyVerificationCode(edtOTP.getText().toString().trim());
                break;
            }
            case R.id.btn_otp1: {
                resendVerificationCode(phone, mtoken);
                break;
            }
        }
    }

    @Override
    public void dangKyThanhCong() {
        btnGuiLaiOTP.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainVertifyOTP.this,"Đăng ký thành công",Toast.LENGTH_SHORT).show();
            }
        });
        startActivity(new Intent(this,MainLogin.class));
        finish();
    }

    @Override
    public void thatBai() {
        btnGuiLaiOTP.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainVertifyOTP.this,"Đăng ký thất bại",Toast.LENGTH_SHORT).show();
            }
        });
    }
}