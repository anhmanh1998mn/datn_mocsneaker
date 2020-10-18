package com.example.manhvan.datn_mocsneaker.View;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.manhvan.datn_mocsneaker.Model.MoLayMaNguoiLapDH;
import com.example.manhvan.datn_mocsneaker.Presenter.PreLayMaNguoiLapDH;
import com.example.manhvan.datn_mocsneaker.R;
import com.example.manhvan.datn_mocsneaker.adapter.GioHangAdapter;
import com.example.manhvan.datn_mocsneaker.util.GioHang;

import java.text.DecimalFormat;

public class ViewGioHang extends AppCompatActivity implements  GioHangAdapter.OnDialogCloseListener,MaNguoiLapDHInterface{
    private ActionBar actionBar;
    private RecyclerView recyclerView;
    private GioHangAdapter adapter;
    private Button btnTongTien,btnDatHang;
    private EditText edtDiaChi;
    private PreLayMaNguoiLapDH preLayMaNguoiLapDH;
    private SharedPreferences sharedPreferences;

    private int idNguoiLap=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_gio_hang);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Giỏ hàng");
        initView();
        hienRecycleView();
        tongTien();
        getMaNguoiLap();
        datHangSP();
    }

    private void getMaNguoiLap() {

        sharedPreferences= getSharedPreferences("QuyenTK", Context.MODE_PRIVATE);
        if(sharedPreferences.getString("quyen","").equals("")){
            //startActivity(new Intent(ViewGioHang.this, MainLogin.class));
            return;
        }
        preLayMaNguoiLapDH=new PreLayMaNguoiLapDH(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                preLayMaNguoiLapDH.layMaNguoiLap(sharedPreferences.getString("phone",""),Integer.parseInt(sharedPreferences.getString("quyen","")));
            }
        }).start();
    }

    private void datHangSP() {
        final SharedPreferences sharedPreferences=getSharedPreferences("QuyenTK", Context.MODE_PRIVATE);
        btnDatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(sharedPreferences.getString("quyen","")==null||sharedPreferences.getString("quyen","").equals("")){
//                    startActivity(new Intent(ViewGioHang.this, MainLogin.class));
//                    return;
//                }
                //Toast.makeText(ViewGioHang.this,idNguoiLap+"",Toast.LENGTH_SHORT).show();

//                if(edtDiaChi.getText().toString().trim().equals("")){
//                    edtDiaChi.setError("Vui lòng nhập địa chỉ nhận hàng");
//                    return;
//                }

            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        tongTien();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }



    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void hienRecycleView() {
        adapter = new GioHangAdapter(this, R.layout.itemgiohang, GioHang.arrGioHang);
        adapter.setListener(this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    private void tongTien() {
        int tong = 0;
        if (GioHang.arrGioHang == null) {
            return;
        }
        for (int i = 0; i < GioHang.arrGioHang.size(); i++) {
            tong += GioHang.arrGioHang.get(i).getDonGia() * GioHang.arrGioHang.get(i).getSoLuong();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        btnTongTien.setText("Tổng tiền: " + decimalFormat.format(tong) + " đ");
    }

    private void initView() {
        btnTongTien = findViewById(R.id.btn_tongTien);
        recyclerView = findViewById(R.id.giohang_recycle);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayout.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        edtDiaChi=findViewById(R.id.txtDiaChiNhan_giohnag);
        btnDatHang=findViewById(R.id.btn_DatHang);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDialogClose() {
        for(int i=0;i<GioHang.arrGioHang.size();i++){
            for(int j=i+1;j<GioHang.arrGioHang.size();j++){
                if(GioHang.arrGioHang.get(i).getIdSP()==GioHang.arrGioHang.get(j).getIdSP()&&
                        GioHang.arrGioHang.get(i).getKichCo().equals(GioHang.arrGioHang.get(j).getKichCo())){
                    GioHang.arrGioHang.get(i).setSoLuong(GioHang.arrGioHang.get(i).getSoLuong()+
                    GioHang.arrGioHang.get(j).getSoLuong());
                    GioHang.arrGioHang.remove(j);
                }
            }
        }
        adapter.notifyDataSetChanged();
        tongTien();
    }


    @Override
    public void onSuccedID() {
        idNguoiLap=Integer.parseInt(MoLayMaNguoiLapDH.lstMaNguoiLap.get(0).getId());
    }

    @Override
    public void onFailedID() {

    }


}