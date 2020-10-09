package com.example.manhvan.datn_mocsneaker.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manhvan.datn_mocsneaker.MainActivity2;
import com.example.manhvan.datn_mocsneaker.Presenter.PreThongTinTaiKhoan;
import com.example.manhvan.datn_mocsneaker.R;
import com.example.manhvan.datn_mocsneaker.entity.ThongTinKhachHang;
import com.example.manhvan.datn_mocsneaker.entity.ThongTinNV;

import java.util.ArrayList;

public class MainUserInfo extends AppCompatActivity implements View.OnClickListener,ThongTinKHInterKQ2{
    private Button btnUserThietLapTaiKhoan,btnDangXuat;
    private ActionBar actionBar;
    private TextView txtHoTen,txtTaiKhoan,txtSDT,txtDiaChi,txtID,txtMK;
    private PreThongTinTaiKhoan preThongTinTaiKhoan;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user_info);
        actionBar=getSupportActionBar();
        actionBar.setTitle("Thiết lập tài khoản");
        actionBar.setDisplayHomeAsUpEnabled(true);
        initView();
        eventClick();
        getData();
    }

    private void getData() {
        preThongTinTaiKhoan=new PreThongTinTaiKhoan(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                preThongTinTaiKhoan.thongTinTaiKhoan(sharedPreferences.getString("quyen",""),sharedPreferences.getString("phone",""));
            }
        }).start();
    }

    private void initView() {
        btnUserThietLapTaiKhoan=findViewById(R.id.btn_ChangePass);
        btnDangXuat=findViewById(R.id.btn_dangXuat);
        txtHoTen=findViewById(R.id.txt_uFName1);
        txtTaiKhoan=findViewById(R.id.txt_uName1);
        txtSDT=findViewById(R.id.txt_uPhone1);
        txtDiaChi=findViewById(R.id.txt_uAddress1);
        txtID=findViewById(R.id.txt_id);
        txtMK=findViewById(R.id.txt_mk);
        sharedPreferences=getSharedPreferences("QuyenTK", Context.MODE_PRIVATE);

    }

    public void eventClick(){
        btnUserThietLapTaiKhoan.setOnClickListener(this);
        btnDangXuat.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_ChangePass:{
                Intent intent=new Intent(MainUserInfo.this,MainXacNhanMatKhau.class);
                Bundle bundle=new Bundle();
                bundle.putString("idtaikhoan",txtID.getText().toString().trim());
                bundle.putString("matkhau",txtMK.getText().toString().trim());
                intent.putExtra("thongtin",bundle);
                startActivity(intent);
                break;
            }
            case R.id.btn_dangXuat:{

                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("quyen","");
                editor.putString("phone","");
                editor.commit();
                startActivity(new Intent(this, MainActivity2.class));
                finish();
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

    @Override
    public void thongTinNhanVien(final ArrayList<ThongTinNV> arrayList) {
        txtHoTen.post(new Runnable() {
            @Override
            public void run() {
                txtHoTen.setText(arrayList.get(0).getStaffName());
                txtTaiKhoan.setText(sharedPreferences.getString("phone",""));
                txtSDT.setText(sharedPreferences.getString("phone",""));
                txtDiaChi.setText(arrayList.get(0).getStaffAddress());
                txtID.setText(arrayList.get(0).getId());
                Log.d("IdNV",txtID.getText().toString());
                txtMK.setText(arrayList.get(0).getUserPassword());
            }
        });
    }

    @Override
    public void thongTinKhachHang(final ArrayList<ThongTinKhachHang> arrayList) {
        txtHoTen.post(new Runnable() {
            @Override
            public void run() {
                txtHoTen.setText(arrayList.get(0).getCustomerName());
                txtTaiKhoan.setText(sharedPreferences.getString("phone",""));
                txtSDT.setText(sharedPreferences.getString("phone",""));
                txtDiaChi.setText(arrayList.get(0).getCustomerAddress());
                txtID.setText(arrayList.get(0).getId());
                Log.d("IdKH",txtID.getText().toString());
                txtMK.setText(arrayList.get(0).getUserPassword());
            }
        });
    }

    @Override
    public void loi() {
        Toast.makeText(this,"Vui lòng đăng nhập",Toast.LENGTH_SHORT).show();
    }
}