package com.example.manhvan.datn_mocsneaker.View;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.manhvan.datn_mocsneaker.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ViewThemNhanVien extends AppCompatActivity implements View.OnClickListener{
    private Toolbar toolbar;
    private TextView edtNgaySinhNV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_them_nhan_vien);
        toolbar=findViewById(R.id.tb2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
        clickview();
    }

    private void clickview() {
        edtNgaySinhNV.setOnClickListener(this);
    }

    private void initView() {
        edtNgaySinhNV=findViewById(R.id.edt_ngaysinhnv);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.edt_ngaysinhnv:{
                chonNgay();
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
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                edtNgaySinhNV.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
