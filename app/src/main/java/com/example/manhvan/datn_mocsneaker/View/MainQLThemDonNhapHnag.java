package com.example.manhvan.datn_mocsneaker.View;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manhvan.datn_mocsneaker.Model.MoTimKiemSanPham;
import com.example.manhvan.datn_mocsneaker.Presenter.PreTimKiemSanPham;
import com.example.manhvan.datn_mocsneaker.R;
import com.example.manhvan.datn_mocsneaker.adapter.ChiTietDonNhapAdapter;
import com.example.manhvan.datn_mocsneaker.adapter.NhapHangTimKiemSPAdapter;
import com.example.manhvan.datn_mocsneaker.util.GioHang;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.text.DecimalFormat;

public class MainQLThemDonNhapHnag extends AppCompatActivity implements TimKiemSanPhamInterface,View.OnClickListener{
    private ActionBar actionBar;
    private EditText edtTimKiem;
    private NhapHangTimKiemSPAdapter adapter;
    private PreTimKiemSanPham preTimKiemSanPham;
    private RecyclerView recyclerViewTimKiem,recyclerViewChiTietDN;
    private ChiTietDonNhapAdapter chiTietDonNhapAdapter;
    private TextView txtSoLuongNhap,txtTongTienNhap;
    private Button btnQRCode;
    private static final int CAMERA_CODE=101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_q_l_them_don_nhap_hnag);
        actionBar=getSupportActionBar();
        actionBar.setTitle("Quản lý nhập hàng");
        actionBar.setDisplayHomeAsUpEnabled(true);
        initView();
        eventClick();
        edtSearchTextChange();
        getDataDonNhap();
    }
    public void eventClick(){
        btnQRCode.setOnClickListener(this);
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
        recyclerViewChiTietDN=findViewById(R.id.recycle_danhsachNhap);
        txtSoLuongNhap=findViewById(R.id.txt_soLuongNhap);
        txtTongTienNhap=findViewById(R.id.txt_tienNhap);
        btnQRCode=findViewById(R.id.btn_qrCode);
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

    @Override
    protected void onRestart() {
        super.onRestart();
        getDataDonNhap();
    }

    public void getDataDonNhap(){
        if(GioHang.arrChiTietDonNhap!=null){

            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayout.VERTICAL, false);
            recyclerViewChiTietDN.setLayoutManager(linearLayoutManager);
            chiTietDonNhapAdapter=new ChiTietDonNhapAdapter(this,R.layout.itemchitietnhaphang, GioHang.arrChiTietDonNhap);
            recyclerViewChiTietDN.setAdapter(chiTietDonNhapAdapter);
            chiTietDonNhapAdapter.notifyDataSetChanged();
        }
        int soLuong=0;
        int tongTien=0;
        for (int i=0;i<GioHang.arrChiTietDonNhap.size();i++){
            soLuong+=GioHang.arrChiTietDonNhap.get(i).getSoLuong();
            tongTien+=(GioHang.arrChiTietDonNhap.get(i).getSoLuong()*GioHang.arrChiTietDonNhap.get(i).getGiaSP());
        }
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        txtSoLuongNhap.setText("Tổng số hàng: "+soLuong);
        txtTongTienNhap.setText("Tổng tiền: "+decimalFormat.format(tongTien)+"đ");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_qrCode:{
                if(Build.VERSION.SDK_INT>=23){
                    if (checkPermission(Manifest.permission.CAMERA)){
                        openScan();
                    }else {
                        requestPermission(Manifest.permission.CAMERA,CAMERA_CODE);
                    }
                }else {
                    openScan();
                }
                break;
            }
        }
    }
    private void openScan() {
        new IntentIntegrator(MainQLThemDonNhapHnag.this).initiateScan();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult intentResult=IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(intentResult!=null){
            if(intentResult.getContents()==null){
                Toast.makeText(MainQLThemDonNhapHnag.this,"Blank",Toast.LENGTH_SHORT).show();
            }else {
                //Log.d("Result:",intentResult.getContents()+"");
                //editText.setText(intentResult.getContents()+"");
//                edtTimKiem.setText(intentResult.getContents()+"");
                Intent intent=new Intent(MainQLThemDonNhapHnag.this, MainProductInsert.class);
                intent.putExtra("ProductInfoID",intentResult.getContents().toString().trim());
                startActivity(intent);
            }
        }else {
            Toast.makeText(MainQLThemDonNhapHnag.this,"Blank",Toast.LENGTH_SHORT).show();
        }
    }
    private boolean checkPermission(String permission){
        int result= ContextCompat.checkSelfPermission(MainQLThemDonNhapHnag.this,permission);
        if(result== PackageManager.PERMISSION_GRANTED){
            return  true;
        }else return false;
    }
    private void requestPermission(String permission,int code){
        if(ActivityCompat.shouldShowRequestPermissionRationale(MainQLThemDonNhapHnag.this,permission)){

        }else {
            ActivityCompat.requestPermissions(MainQLThemDonNhapHnag.this,new String[]{permission},code);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case CAMERA_CODE:{
                if(grantResults.length>0&&grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    openScan();
                }
            }
        }
    }
}