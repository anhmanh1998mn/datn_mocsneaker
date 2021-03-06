package com.example.manhvan.datn_mocsneaker.View.QuanLySanPham;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.manhvan.datn_mocsneaker.Model.MoTimKiemSanPham;
import com.example.manhvan.datn_mocsneaker.Presenter.PreTimKiemSanPham;
import com.example.manhvan.datn_mocsneaker.R;
import com.example.manhvan.datn_mocsneaker.View.PKInterface.TimKiemSanPhamInterface;
import com.example.manhvan.datn_mocsneaker.adapter.QLTimKiemSanPhamAdapter;

public class MainQuanLySanPham extends AppCompatActivity implements View.OnClickListener, TimKiemSanPhamInterface {
    private PreTimKiemSanPham preTimKiemSanPham;
    private Button btnSearchSP,btnThemSP;
    private RecyclerView recyclerViewSP;
    private EditText edtSearch1;
    private QLTimKiemSanPhamAdapter adapter;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_quan_ly_san_pham);
        actionBar=getSupportActionBar();
        actionBar.setTitle("Quản lý sản phẩm");
        actionBar.setDisplayHomeAsUpEnabled(true);
        initView();
        enventClickButton();

        timkiemSP();
    }

    private void timkiemSP(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                preTimKiemSanPham.timKiemSanPham(edtSearch1.getText().toString().trim());
            }
        }).start();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    private void initView() {
        btnThemSP=findViewById(R.id.btn_themSP);
        btnSearchSP=findViewById(R.id.btn_search1);
        recyclerViewSP=findViewById(R.id.recy_qlsp);
        recyclerViewSP.setNestedScrollingEnabled(false);
//        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayout.VERTICAL,false);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,3);
        recyclerViewSP.setLayoutManager(gridLayoutManager);
        edtSearch1=findViewById(R.id.edt_search1);
        preTimKiemSanPham=new PreTimKiemSanPham(this);
    }
    private void enventClickButton() {
        btnSearchSP.setOnClickListener(this);
        btnThemSP.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_search1:{
                timkiemSP();
                break;
            }
            case R.id.btn_themSP:{
                startActivity(new Intent(MainQuanLySanPham.this, MainQLThemSanPham.class));
                break;
            }
        }
    }

    @Override
    protected void onRestart() {
        edtSearch1.setText("");
        adapter.notifyDataSetChanged();
        timkiemSP();
        super.onRestart();
    }

    @Override
    public void onSuceessed() {
        recyclerViewSP.post(new Runnable() {
            @Override
            public void run() {
                adapter=new QLTimKiemSanPhamAdapter(MainQuanLySanPham.this,R.layout.item_timkiem_san_pham, MoTimKiemSanPham.lstTimKiemSP);
                recyclerViewSP.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onFailed() {

    }

    @Override
    public void themThanhCong() {

    }
}