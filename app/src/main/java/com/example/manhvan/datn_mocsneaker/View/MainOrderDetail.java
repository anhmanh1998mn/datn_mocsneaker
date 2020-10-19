package com.example.manhvan.datn_mocsneaker.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.manhvan.datn_mocsneaker.Model.MoOrderDetail;
import com.example.manhvan.datn_mocsneaker.Presenter.PreOrderDetail;
import com.example.manhvan.datn_mocsneaker.R;
import com.example.manhvan.datn_mocsneaker.adapter.OrderDetailAdapter;

import java.text.DecimalFormat;

public class MainOrderDetail extends AppCompatActivity implements PreOrderDetail.GetDataOrInterface{
    private ActionBar actionBar;
    private TextView txtNgayLap,txtDiaChiNhan,txtTongTien,txtMaDH;
    private RecyclerView recyclerOrderDetail;
    private PreOrderDetail preOrderDetail;
    private int maDonHang=0;
    private OrderDetailAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_order_detail);
        actionBar=getSupportActionBar();
        actionBar.setTitle("Chi tiết đơn hàng");
        actionBar.setDisplayHomeAsUpEnabled(true);
        initView();
        getData();
        getDataOrderDetail();

    }

    private void initView(){
        txtNgayLap=findViewById(R.id.txt_odetailDate);
        txtDiaChiNhan=findViewById(R.id.txt_odetailAddress);
        txtTongTien=findViewById(R.id.txt_odetailTotal);
        txtMaDH=findViewById(R.id.txt_odetailID);
        recyclerOrderDetail=findViewById(R.id.recycle_odetail);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
    private void getData(){
        Intent intent=getIntent();

        txtMaDH.setText("Mã đơn hàng: "+intent.getStringExtra("maDH"));
        txtNgayLap.setText(intent.getStringExtra("ngayLap"));
        txtDiaChiNhan.setText(intent.getStringExtra("diaChi"));
        maDonHang=Integer.parseInt(intent.getStringExtra("maDH"));
    }
    private void getDataOrderDetail(){
        preOrderDetail=new PreOrderDetail(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                preOrderDetail.orderDetail(maDonHang);
            }
        }).start();
    }
    private void tongTien(){
        int tong=0;
        for (int i=0;i<MoOrderDetail.lstOrderDetail.size();i++){
            tong+=Integer.parseInt(MoOrderDetail.lstOrderDetail.get(i).getQuantity())*Integer.parseInt(MoOrderDetail.lstOrderDetail.get(i).getPriceOut());

        }
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        txtTongTien.setText("Tổng tiền: "+decimalFormat.format(tong)+"đ");
    }
    @Override
    public void onSuccessed() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this, LinearLayout.VERTICAL,false);
        recyclerOrderDetail.setLayoutManager(linearLayoutManager);
        adapter=new OrderDetailAdapter(this,R.layout.itemgiohang, MoOrderDetail.lstOrderDetail);
        recyclerOrderDetail.post(new Runnable() {
            @Override
            public void run() {
                recyclerOrderDetail.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                tongTien();
            }
        });
    }

    @Override
    public void onFailed() {

    }
}