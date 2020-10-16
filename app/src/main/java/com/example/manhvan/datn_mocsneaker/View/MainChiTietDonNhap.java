package com.example.manhvan.datn_mocsneaker.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.manhvan.datn_mocsneaker.R;

import java.util.ArrayList;

public class MainChiTietDonNhap extends AppCompatActivity {
    private ActionBar actionBar;
    private TextView txtMaDonNhapCT,txtNgayLapCT,txtTongSL,txtTongTienCT;
    private Spinner spinner;
    private RecyclerView recyclerViewCT;
    private ArrayList<String> arrSpinner;
    private int maDonNhap=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chi_tiet_don_nhap);
        actionBar=getSupportActionBar();
        actionBar.setTitle("Chi tiết đơn nhập hàng");
        actionBar.setDisplayHomeAsUpEnabled(true);
        initView();
        checkRole();
        nhanDuLieu();
    }

    private void initView() {
        txtMaDonNhapCT=findViewById(R.id.txt_maDonNhapCT);
        txtNgayLapCT=findViewById(R.id.txt_ngayLapCT);
        spinner=findViewById(R.id.spinner_CT);
        recyclerViewCT=findViewById(R.id.recycle_viewCT);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this, LinearLayout.VERTICAL,false);
        recyclerViewCT.setLayoutManager(linearLayoutManager);
        txtTongSL=findViewById(R.id.txt_tongSL);
        txtTongTienCT=findViewById(R.id.txt_tongTienCT);

        arrSpinner=new ArrayList<>();
        arrSpinner.add("Duyệt");
        arrSpinner.add("Không duyệt");
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,arrSpinner);
        spinner.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    public void checkRole(){
        SharedPreferences sharedPreferences = getSharedPreferences("QuyenTK", Context.MODE_PRIVATE);
        if (sharedPreferences.getString("quyen", "").equals("2")){
            spinner.setEnabled(false);
            return;
        }
    }
    public void nhanDuLieu(){
        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("ttDonNhap");
        maDonNhap=Integer.parseInt(bundle.getString("idDonNhap"));
        txtMaDonNhapCT.setText(bundle.getString("idDonNhap"));
        txtNgayLapCT.setText(bundle.getString("ngayLap",""));
    }
}