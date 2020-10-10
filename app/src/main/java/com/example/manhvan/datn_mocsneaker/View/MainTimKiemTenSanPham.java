package com.example.manhvan.datn_mocsneaker.View;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.manhvan.datn_mocsneaker.Model.MoTimKiemSanPham;
import com.example.manhvan.datn_mocsneaker.Presenter.PreTimKiemSanPham;
import com.example.manhvan.datn_mocsneaker.R;
import com.example.manhvan.datn_mocsneaker.adapter.TimKiemSanPhamAdapter;

public class MainTimKiemTenSanPham extends AppCompatActivity implements View.OnClickListener,TimKiemSanPhamInterface {
    private ActionBar actionBar;
    private ImageButton btnBackSP,btnhuySearch;
    private EditText edtSearchSP;
    private PreTimKiemSanPham preTimKiemSanPham;
    private TimKiemSanPhamAdapter timKiemSanPhamAdapter;
    private RecyclerView recyclerViewTimKiem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tim_kiem_ten_san_pham);
        actionBar=getSupportActionBar();
        actionBar.hide();
        initView();
        edtChenageText();
        evenClick();
    }

    private void evenClick() {
        btnBackSP.setOnClickListener(this);
        btnhuySearch.setOnClickListener(this);
    }

    private void edtChenageText() {
        edtSearchSP.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(final CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().equals("")){
                    btnhuySearch.setVisibility(View.GONE);
                    return;
                }
                btnhuySearch.setVisibility(View.VISIBLE);
                preTimKiemSanPham=new PreTimKiemSanPham(MainTimKiemTenSanPham.this);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        preTimKiemSanPham.timKiemSanPham(charSequence.toString());
                    }
                }).start();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void initView() {
        btnBackSP=findViewById(R.id.btn_backSP);
        btnhuySearch=findViewById(R.id.btn_searchSP);
        edtSearchSP=findViewById(R.id.edt_searchSP);
        recyclerViewTimKiem=findViewById(R.id.timkeim_recycleview);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_backSP:{
                finish();
                break;
            }
            case R.id.btn_searchSP:{
                edtSearchSP.setText("");
                break;
            }
        }
    }

    @Override
    public void onSuceessed() {
        edtSearchSP.post(new Runnable() {
            @Override
            public void run() {
                timKiemSanPhamAdapter=new TimKiemSanPhamAdapter(MainTimKiemTenSanPham.this,R.layout.item_timkiem_san_pham, MoTimKiemSanPham.lstTimKiemSP);
                Log.d("timkiemsp",MoTimKiemSanPham.lstTimKiemSP.size()+"");
                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(MainTimKiemTenSanPham.this, LinearLayout.VERTICAL,false);
                recyclerViewTimKiem.setLayoutManager(linearLayoutManager);
                recyclerViewTimKiem.setAdapter(timKiemSanPhamAdapter);
            }
        });
    }

    @Override
    public void onFailed() {

    }
}