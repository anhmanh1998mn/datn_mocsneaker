package com.example.manhvan.datn_mocsneaker.View.QuanLySanPham;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.manhvan.datn_mocsneaker.Model.MoTimKiemSanPham;
import com.example.manhvan.datn_mocsneaker.Model.MoTimKiemTheoKhoangGia;
import com.example.manhvan.datn_mocsneaker.Presenter.PreTimKiemTheoKhoangGia;
import com.example.manhvan.datn_mocsneaker.R;
import com.example.manhvan.datn_mocsneaker.adapter.QLTimKiemSanPhamAdapter;

public class MainLocSanPham extends AppCompatActivity implements View.OnClickListener,PreTimKiemTheoKhoangGia.TimKiemGiaSuccessInter{
    private ActionBar actionBar;
    private Button btnLocGiayNam,btnLocGiayNu,btnLocGiayDoi,btnLocMoi,btnLocApDung;
    private EditText edtLocToi1,edtLocToi2;
    private RecyclerView recyclerViewLoc;
    private PreTimKiemTheoKhoangGia preTimKiemTheoKhoangGia;
    private QLTimKiemSanPhamAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_loc_san_pham);
        actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Bộ lọc tìm kiếm");
        initView();
        evenClick();
    }

    private void evenClick() {
        btnLocGiayNam.setOnClickListener(this);
        btnLocGiayNu.setOnClickListener(this);
        btnLocGiayDoi.setOnClickListener(this);
        btnLocMoi.setOnClickListener(this);
        btnLocApDung.setOnClickListener(this);
    }

    private void initView() {
        recyclerViewLoc=findViewById(R.id.re_locSanPham);
        btnLocGiayNam=findViewById(R.id.btn_locGiayNam);
        btnLocGiayNu=findViewById(R.id.btn_locGiayNu);
        btnLocGiayDoi=findViewById(R.id.btn_locGiayDoi);
        btnLocMoi=findViewById(R.id.btn_locGiayMoi);
        btnLocApDung=findViewById(R.id.btn_locApDung);
        edtLocToi1=findViewById(R.id.edt_locGia1);
        edtLocToi2=findViewById(R.id.edt_locGia2);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_locGiayNam:{
                Intent intent=new Intent(this, ViewSanPham.class);
                intent.putExtra("idLoaiSanPham",3);
                startActivity(intent);
                break;
            }
            case R.id.btn_locGiayNu:{
                Intent intent=new Intent(this, ViewSanPham.class);
                intent.putExtra("idLoaiSanPham",4);
                startActivity(intent);
                break;
            }
            case R.id.btn_locGiayDoi:{
                Intent intent=new Intent(this, ViewSanPham.class);
                intent.putExtra("idLoaiSanPham",5);
                startActivity(intent);
                break;
            }
            case R.id.btn_locGiayMoi:{
                Intent intent=new Intent(this, ViewSanPham.class);
                intent.putExtra("idLoaiSanPham",1);
                startActivity(intent);
                break;
            }
            case R.id.btn_locApDung:{
                if(edtLocToi1.getText().toString().trim().equals("")||edtLocToi2.getText().toString().trim().equals("")){
                    return;
                }
                timKiem();
                break;
            }
        }
    }

    private void timKiem() {
        preTimKiemTheoKhoangGia=new PreTimKiemTheoKhoangGia(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                preTimKiemTheoKhoangGia.timKiemTHeoGia(Integer.parseInt(edtLocToi1.getText().toString().trim()),Integer.parseInt(
                        edtLocToi2.getText().toString().trim()
                ));
            }
        }).start();
    }

    @Override
    public void onSuccessed() {
        recyclerViewLoc.post(new Runnable() {
            @Override
            public void run() {
                recyclerViewLoc.setNestedScrollingEnabled(false);
//        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayout.VERTICAL,false);
                GridLayoutManager gridLayoutManager=new GridLayoutManager(MainLocSanPham.this,3);
                recyclerViewLoc.setLayoutManager(gridLayoutManager);
                adapter=new QLTimKiemSanPhamAdapter(MainLocSanPham.this,R.layout.item_timkiem_san_pham, MoTimKiemTheoKhoangGia.lstKhoanGia);
                recyclerViewLoc.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
