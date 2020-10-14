package com.example.manhvan.datn_mocsneaker.View;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.manhvan.datn_mocsneaker.Model.MoTimKiemSanPham;
import com.example.manhvan.datn_mocsneaker.Presenter.PreTimKiemSanPham;
import com.example.manhvan.datn_mocsneaker.R;
import com.example.manhvan.datn_mocsneaker.adapter.NhapHangTimKiemSPAdapter;

public class MainQLThemDonNhapHnag extends AppCompatActivity implements TimKiemSanPhamInterface{
    private ActionBar actionBar;
    private EditText edtTimKiem;
    private NhapHangTimKiemSPAdapter adapter;
    private PreTimKiemSanPham preTimKiemSanPham;
    private RecyclerView recyclerViewTimKiem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_q_l_them_don_nhap_hnag);
        actionBar=getSupportActionBar();
        actionBar.setTitle("Quản lý nhập hàng");
        actionBar.setDisplayHomeAsUpEnabled(true);
        initView();
        edtSearchTextChange();

    }

    private void edtSearchTextChange() {
        edtTimKiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                timkiemsanpham(charSequence.toString().trim());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void initView() {
        edtTimKiem=findViewById(R.id.edt_themNhapHang);
        recyclerViewTimKiem=findViewById(R.id.recycle_timKiem);
    }

    private void timkiemsanpham(final String keySearch) {
        preTimKiemSanPham=new PreTimKiemSanPham(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                preTimKiemSanPham.timKiemSanPham(keySearch);
            }
        }).start();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSuceessed() {
        recyclerViewTimKiem.post(new Runnable() {
            @Override
            public void run() {
                adapter=new NhapHangTimKiemSPAdapter(MainQLThemDonNhapHnag.this,R.layout.item_timkiem_san_pham, MoTimKiemSanPham.lstTimKiemSP);
                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(MainQLThemDonNhapHnag.this, LinearLayout.VERTICAL,false);
                recyclerViewTimKiem.setLayoutManager(linearLayoutManager);
                recyclerViewTimKiem.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onFailed() {

    }
}