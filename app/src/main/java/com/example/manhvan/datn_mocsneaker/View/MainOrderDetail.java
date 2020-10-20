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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manhvan.datn_mocsneaker.Model.MoOrderDetail;
import com.example.manhvan.datn_mocsneaker.Presenter.PreOrderDetail;
import com.example.manhvan.datn_mocsneaker.R;
import com.example.manhvan.datn_mocsneaker.adapter.OrderDetailAdapter;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainOrderDetail extends AppCompatActivity implements PreOrderDetail.GetDataOrInterface, View.OnClickListener {
    private ActionBar actionBar;
    private TextView txtNgayLap,txtDiaChiNhan,txtTongTien,txtMaDH;
    private RecyclerView recyclerOrderDetail;
    private PreOrderDetail preOrderDetail;
    private int maDonHang=0,trangThai=0;
    private OrderDetailAdapter adapter;
    private Button btnHuyDon,btnCapNhatTinhTrang;
    private SharedPreferences sharedPreferences;
    private Spinner spinner;
    private LinearLayout linearLayoutDH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_order_detail);
        actionBar=getSupportActionBar();
        actionBar.setTitle("Chi tiết đơn hàng");
        actionBar.setDisplayHomeAsUpEnabled(true);
        initView();
        loadDataSpinner();
        getData();
        getDataOrderDetail();
        checkRole();
        onClickButton();
    }

    private void loadDataSpinner() {
        ArrayList<String> arrSpiner=new ArrayList<>();
        arrSpiner.add("Xác nhận");
        arrSpiner.add("Đang giao");
        arrSpiner.add("Đã giao");
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,arrSpiner);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:{
                        trangThai=2;break;
                    }
                    case 1:{
                        trangThai=3;break;
                    }
                    case 2:{
                        trangThai=5;break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void checkRole(){
        sharedPreferences=getSharedPreferences("QuyenTK", Context.MODE_PRIVATE);
        if(sharedPreferences.getString("quyen","").equals("3")){
            btnCapNhatTinhTrang.setVisibility(View.GONE);
            linearLayoutDH.setVisibility(View.GONE);
        }
    }

    private void initView(){
        txtNgayLap=findViewById(R.id.txt_odetailDate);
        txtDiaChiNhan=findViewById(R.id.txt_odetailAddress);
        txtTongTien=findViewById(R.id.txt_odetailTotal);
        txtMaDH=findViewById(R.id.txt_odetailID);
        recyclerOrderDetail=findViewById(R.id.recycle_odetail);
        btnHuyDon=findViewById(R.id.btn_huyDonHang);
        btnCapNhatTinhTrang=findViewById(R.id.btn_capNhatTinhTrang);
        spinner=findViewById(R.id.spinner_trangThai);
        linearLayoutDH=findViewById(R.id.linea_DH);
    }
    private void onClickButton(){
        btnHuyDon.setOnClickListener(this);
        btnCapNhatTinhTrang.setOnClickListener(this);
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
        recyclerOrderDetail.setNestedScrollingEnabled(true);
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

    @Override
    public void onHuyThanhCong() {
        btnHuyDon.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainOrderDetail.this,"Hủy đơn hàng thành công",Toast.LENGTH_SHORT).show();
                btnHuyDon.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onCapNhatDH() {
        btnCapNhatTinhTrang.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainOrderDetail.this,"Cập nhật trạng thái thành công",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_huyDonHang:{
                preOrderDetail=new PreOrderDetail(this);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        preOrderDetail.huyDonHang(maDonHang);
                    }
                }).start();
                break;
            }
            case R.id.btn_capNhatTinhTrang:{
                preOrderDetail=new PreOrderDetail(this);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        preOrderDetail.capNhatDonHang(maDonHang,trangThai);
                    }
                }).start();
                break;
            }

        }
    }
}