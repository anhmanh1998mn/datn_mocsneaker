package com.example.manhvan.datn_mocsneaker.View.QuanLyNhanVien;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manhvan.datn_mocsneaker.Presenter.PreSuaThongTinNhanVien;
import com.example.manhvan.datn_mocsneaker.R;
import com.example.manhvan.datn_mocsneaker.View.PKInterface.SuaNhanVienKQ2;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainSuaNhanVIen extends AppCompatActivity implements View.OnClickListener, SuaNhanVienKQ2 {

    private EditText edtSTenNV,edtSSDT,edtSDiaChi,edtSCMT;
    private TextView txtSNgaySinh,txtTrangThaiTK;
    private Button btnSNV,btnSKhoaTK,btnSHuy;
    private int idnhan=0;
    private int trangThaiTaiKhoan=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sua_nhan_vien);
        getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
        getData();
        EvenClick();


    }

    private void getData() {
        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("thongtinNV");
        idnhan=Integer.parseInt(bundle.getString("id"));
        edtSTenNV.setText(bundle.getString("tenNV"));
        edtSSDT.setText(bundle.getString("soDT"));
        edtSDiaChi.setText(bundle.getString("queQuan"));
        txtSNgaySinh.setText(bundle.getString("ngaySinh"));
        edtSCMT.setText(bundle.getString("soCMT"));
        trangThaiTaiKhoan=Integer.parseInt(bundle.getString("TrangThai"));
        if (trangThaiTaiKhoan==2){
            btnSKhoaTK.setVisibility(View.GONE);
            btnSNV.setVisibility(View.GONE);
            txtTrangThaiTK.setText("Tài khoản hiện đang bị khóa");
            txtTrangThaiTK.setTextColor(Color.RED);
        }
    }

    private void EvenClick() {
        txtSNgaySinh.setOnClickListener(this);
        btnSNV.setOnClickListener(this);
        btnSKhoaTK.setOnClickListener(this);
    }

    private void initView() {
        edtSTenNV=findViewById(R.id.edt_stennv);
        edtSSDT=findViewById(R.id.edt_ssdtnv);
        edtSDiaChi=findViewById(R.id.edt_sdcnv);
        edtSCMT=findViewById(R.id.edt_scmtnv);
        txtSNgaySinh=findViewById(R.id.edt_snsnv);
        btnSNV=findViewById(R.id.btn_snv);
        btnSHuy=findViewById(R.id.btn_shnv);
        btnSKhoaTK=findViewById(R.id.btn_sknv);
        txtTrangThaiTK=findViewById(R.id.txt_trangthaitk);
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
    public void getDate(){
        final Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        int day=calendar.get(Calendar.DATE);

        DatePickerDialog datePickerDialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(i,i1,i2);
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
                txtSNgaySinh.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },year,month,day);
        datePickerDialog.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.edt_snsnv:{
                getDate();
                break;
            }
            case R.id.btn_snv:{
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        PreSuaThongTinNhanVien preSuaThongTinNhanVien=new PreSuaThongTinNhanVien(MainSuaNhanVIen.this);
                        preSuaThongTinNhanVien.SuaThongTin(idnhan,edtSTenNV.getText().toString().trim(),edtSSDT.getText().toString().trim(),
                                txtSNgaySinh.getText().toString().trim(),edtSDiaChi.getText().toString().trim(),edtSCMT.getText().toString().trim());
                    }
                }).start();
                break;
            }
            case R.id.btn_sknv:{
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        PreSuaThongTinNhanVien preSuaThongTinNhanVien=new PreSuaThongTinNhanVien(MainSuaNhanVIen.this);
                        preSuaThongTinNhanVien.KhoaTaiKhoan(idnhan);
                    }
                }).start();
                break;
            }
        }
    }

    @Override
    public void onSuccess() {
        Toast.makeText(this,"Sửa thành công",Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onSuccesKhoa() {
        Toast.makeText(this,"Khóa tài khoản thành công",Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onFailed() {
        Toast.makeText(this,"Sửa thất bại",Toast.LENGTH_SHORT).show();

    }
}
