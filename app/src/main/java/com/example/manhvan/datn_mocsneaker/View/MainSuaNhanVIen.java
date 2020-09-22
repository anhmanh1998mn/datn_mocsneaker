package com.example.manhvan.datn_mocsneaker.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.manhvan.datn_mocsneaker.R;

public class MainSuaNhanVIen extends AppCompatActivity {
    private Toolbar toolbar;
    private EditText edtSTenNV,edtSSDT,edtSDiaChi,edtSCMT;
    private TextView txtSNgaySinh;
    private Button btnSNV,btnSKhoaTK,btnSHuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sua_nhan_vien);
        toolbar=findViewById(R.id.tbsnv);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
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
}
