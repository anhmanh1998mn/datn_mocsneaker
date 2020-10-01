package com.example.manhvan.datn_mocsneaker.View;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manhvan.datn_mocsneaker.Presenter.PreThemNV;
import com.example.manhvan.datn_mocsneaker.R;

import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ViewThemNhanVien extends AppCompatActivity implements View.OnClickListener,ThemNhanVienKQ2{
    private PreThemNV preThemNV;

    private TextView edtNgaySinhNV;
    private EditText edtTenNV,edtSoDienThoai,edtDiaChiNV,edtSoCMT,edtTaiKhoanNV,edtMatKhauNV;
    private Button btnThemNV1,btnHuyNV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_them_nhan_vien);
        getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        preThemNV=new PreThemNV(this);
        initView();
        setPhoneUserName();
        clickview();
    }

    private void clickview() {
        edtNgaySinhNV.setOnClickListener(this);
        btnThemNV1.setOnClickListener(this);
        btnHuyNV.setOnClickListener(this);
    }

    private void initView() {
        edtNgaySinhNV=findViewById(R.id.edt_ngaysinhnv);
        edtTenNV=findViewById(R.id.edt_tenNV);
        edtSoDienThoai=findViewById(R.id.edt_sdtNV);
        edtDiaChiNV=findViewById(R.id.edt_diachiNV);
        edtSoCMT=findViewById(R.id.edt_cmtNV);
        edtTaiKhoanNV=findViewById(R.id.edt_taikhoanNV);
        edtMatKhauNV=findViewById(R.id.edt_matkhauNV);
        btnThemNV1=findViewById(R.id.btn_themNV1);
        btnHuyNV=findViewById(R.id.btn_huyThemNV);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.edt_ngaysinhnv:{
                chonNgay();
                break;
            }
            case R.id.btn_themNV1:{

                try {
                    preThemNV.ThemMoiNV(edtTenNV.getText().toString().trim(),edtSoDienThoai.getText().toString().trim(),
                            edtDiaChiNV.getText().toString().trim(),edtNgaySinhNV.getText().toString().trim(),
                            edtSoCMT.getText().toString().trim(),edtTaiKhoanNV.getText().toString().trim(),
                            edtMatKhauNV.getText().toString().trim());
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                break;
            }
            case R.id.btn_huyThemNV:{
                finish();
                break;
            }
        }
    }

    private void chonNgay() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(i, i1, i2);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                edtNgaySinhNV.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    public void setPhoneUserName(){
        edtSoDienThoai.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                edtTaiKhoanNV.setText(editable.toString().trim());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSuccessed() {
        Toast.makeText(this,"Thêm nhân viên thành công",Toast.LENGTH_SHORT).show();
        finish();
//        startActivity(new Intent(this,ViewQuanLyNhanVIen.class));
    }

    @Override
    public void onFailed() {
        Toast.makeText(this,"Thêm nhân viên thất bại",Toast.LENGTH_SHORT).show();
    }
}
