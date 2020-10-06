package com.example.manhvan.datn_mocsneaker.View;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.manhvan.datn_mocsneaker.Model.MoQuanLyKhachHang;
import com.example.manhvan.datn_mocsneaker.Model.MoTimKiemKhachHang;
import com.example.manhvan.datn_mocsneaker.Presenter.PreQuanLyKhachHang;
import com.example.manhvan.datn_mocsneaker.Presenter.PreTimKiemKhachHang;
import com.example.manhvan.datn_mocsneaker.R;
import com.example.manhvan.datn_mocsneaker.adapter.KhachHangAdapter;

public class MainQuanLyKhachHang extends AppCompatActivity implements QuanLyKhachHangInterface{
    private ActionBar actionBar;
    private PreQuanLyKhachHang preQuanLyKhachHang;
    private RecyclerView recyclerView,recyclerView1;
    private KhachHangAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private Button btnSearchKH;
    private EditText edtSearchKH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_quan_ly_khach_hang);
        actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Quản lý khách hàng");
        initView();
        getData();
        timKiemKhachHang();
    }

    private void timKiemKhachHang() {
        btnSearchKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtSearchKH.getText().toString().trim().equals("")){
                    recyclerView.setVisibility(View.VISIBLE);
                    recyclerView1.setVisibility(View.GONE);
                    getData();
                    return;
                }
                final PreTimKiemKhachHang preTimKiemKhachHang=new PreTimKiemKhachHang(MainQuanLyKhachHang.this);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        preTimKiemKhachHang.timKiemKhachHang(edtSearchKH.getText().toString().trim());
                    }
                }).start();
            }
        });
    }

    private void initView() {
        recyclerView=findViewById(R.id.recylce_KH);

        recyclerView1=findViewById(R.id.recylce_KH1);
        btnSearchKH=findViewById(R.id.btn_searchQLKH);
        edtSearchKH=findViewById(R.id.edt_timkiemQLKH);
    }

    private void getData() {
        preQuanLyKhachHang=new PreQuanLyKhachHang(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                preQuanLyKhachHang.quanLyKhachHang();
            }
        }).start();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void danhSachKHThanhCong() {

        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                linearLayoutManager=new LinearLayoutManager(MainQuanLyKhachHang.this, LinearLayout.VERTICAL,false);
                recyclerView.setLayoutManager(linearLayoutManager);
                adapter=new KhachHangAdapter(MainQuanLyKhachHang.this,R.layout.itemquanlykhachhang, MoQuanLyKhachHang.lstKH);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void thatBai() {
        Toast.makeText(this,"Kiểm tra kết nối mạng",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void danhSachTimKiem() {
        recyclerView1.post(new Runnable() {
            @Override
            public void run() {
                linearLayoutManager=new LinearLayoutManager(MainQuanLyKhachHang.this, LinearLayout.VERTICAL,false);
                recyclerView1.setLayoutManager(linearLayoutManager);
                recyclerView1.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                adapter=new KhachHangAdapter(MainQuanLyKhachHang.this,R.layout.itemquanlykhachhang, MoTimKiemKhachHang.lstKHTimKiem);
                recyclerView1.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                Log.d("timKH:",MoTimKiemKhachHang.lstKHTimKiem.size()+"");
            }
        });
    }
}