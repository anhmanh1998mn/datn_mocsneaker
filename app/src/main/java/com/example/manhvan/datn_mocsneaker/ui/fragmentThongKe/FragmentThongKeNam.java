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

import com.example.manhvan.datn_mocsneaker.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FragmentThongKeNam extends Fragment implements View.OnClickListener {
    private TextView txtDate;
    private Button btnThongKe;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_thong_ke_nam,container,false);
        initView(view);
        getDateNow();
        onClickButton();
        return view;
    }
    private void initView(View view) {
        txtDate=view.findViewById(R.id.txt_dateTK1);
        btnThongKe=view.findViewById(R.id.btn_thongKe1);
    }

    private void getDateNow(){
        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        int day=calendar.get(Calendar.DATE);
        calendar.set(year,month,day);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy");
        txtDate.setText(simpleDateFormat.format(calendar.getTime()));
    }

    private void showDatepickerDialog(){
        final Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        int day=calendar.get(Calendar.DATE);
        DatePickerDialog datePickerDialog=new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(i,i1,i2);
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy");
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
            case R.id.txt_dateTK1:{
                showDatepickerDialog();
                break;
            }
        }
    }
}
