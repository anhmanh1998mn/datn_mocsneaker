package com.example.manhvan.datn_mocsneaker.ui.fragmentThongKe;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manhvan.datn_mocsneaker.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class FragmentThongKeThang extends Fragment implements View.OnClickListener {
    private TextView txtDate;
    private Button btnThongKe;
    private int year,month,day;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_thong_ke_thang,container,false);
        initView(view);
        getDateNow();
        onClickButton();
        tinhDoanhThu();
        return view;
    }

    private void initView(View view) {
        txtDate=view.findViewById(R.id.txt_dateTK);
        btnThongKe=view.findViewById(R.id.btn_thongKe);
    }

    private void getDateNow(){
        Calendar calendar=Calendar.getInstance();
        int year1=calendar.get(Calendar.YEAR);
        int month1=calendar.get(Calendar.MONTH);
        int day1=calendar.get(Calendar.DATE);
        calendar.set(year1,month1,day1);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("MM/yyyy");
        txtDate.setText(simpleDateFormat.format(calendar.getTime()));
    }

    private void showDatepickerDialog(){
        final Calendar calendar=Calendar.getInstance();
        year=calendar.get(Calendar.YEAR);
        month=calendar.get(Calendar.MONTH);
        day=calendar.get(Calendar.DATE);
        DatePickerDialog datePickerDialog=new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(i,i1,i2);
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("MM/yyyy");
                txtDate.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },year,month,day);
        datePickerDialog.show();
    }
    private void onClickButton(){
        txtDate.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.txt_dateTK:{
                showDatepickerDialog();
                break;
            }
        }
    }
    private void tinhDoanhThu(){
        String[] date=txtDate.getText().toString().split("/");
        Toast.makeText(getContext(),date[0]+"-"+date[1],Toast.LENGTH_SHORT).show();
    }
}
