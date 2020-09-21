package com.example.manhvan.datn_mocsneaker.View;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.manhvan.datn_mocsneaker.Model.MoDanhSachNhanVien;
import com.example.manhvan.datn_mocsneaker.Presenter.PreDanhSachNV;
import com.example.manhvan.datn_mocsneaker.R;
import com.example.manhvan.datn_mocsneaker.adapter.NhanVienAdapter;

public class ViewQuanLyNhanVIen extends AppCompatActivity implements View.OnClickListener,LayDanhSachNVKQ2{
    private Toolbar toolbar;
    private Button btnThemNV;
    private ListView listviewNhanVien;
    private PreDanhSachNV preDanhSachNV;
    private Thread thread;
    private NhanVienAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_quan_ly_nhan_vien);
        toolbar=findViewById(R.id.tb1);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
        clickButton();

        thread=new Thread(runnable);
        thread.start();

    }
    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            preDanhSachNV=new PreDanhSachNV(ViewQuanLyNhanVIen.this);
            preDanhSachNV.DanhSachNhanVien();
        }
    };



    private void clickButton() {
        btnThemNV.setOnClickListener(this);
    }

    private void initView() {
        btnThemNV=findViewById(R.id.btn_themVN);
        listviewNhanVien=findViewById(R.id.lst_nhanvien);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_themVN:{
                startActivity(new Intent(ViewQuanLyNhanVIen.this,ViewThemNhanVien.class));
                break;
            }
        }
    }

    @Override
    public void onSuccessed() {
        adapter=new NhanVienAdapter(this,R.layout.itemlstnhanvien, MoDanhSachNhanVien.arrayListNV);
        listviewNhanVien.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFailed() {
        Toast.makeText(this,"Lỗi kết nối",Toast.LENGTH_SHORT).show();
    }
}
