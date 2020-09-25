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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.manhvan.datn_mocsneaker.Model.MoDanhSachNhanVien;
import com.example.manhvan.datn_mocsneaker.Model.MoTimKiemNV;
import com.example.manhvan.datn_mocsneaker.Presenter.PreDanhSachNV;
import com.example.manhvan.datn_mocsneaker.Presenter.PreTimKiemNhanVien;
import com.example.manhvan.datn_mocsneaker.R;
import com.example.manhvan.datn_mocsneaker.adapter.NhanVienAdapter;

public class ViewQuanLyNhanVIen extends AppCompatActivity implements View.OnClickListener,LayDanhSachNVKQ2,TimKiemNVKQ2{

    private Button btnThemNV,btnSearch;
    private EditText edtSearch;
    private ListView listviewNhanVien,listViewNVTimKiem;
    private PreDanhSachNV preDanhSachNV;
    private Thread thread;
    private NhanVienAdapter adapter;
    private PreTimKiemNhanVien preTimKiemNhanVien;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_quan_ly_nhan_vien);

        getSupportActionBar();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
        clickButton();

        thread=new Thread(runnable);
        thread.start();
        preTimKiemNhanVien=new PreTimKiemNhanVien(this);


    }
    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            preDanhSachNV=new PreDanhSachNV(ViewQuanLyNhanVIen.this);
            preDanhSachNV.DanhSachNhanVien();
        }
    };

    @Override
    protected void onRestart() {
        super.onRestart();
        thread=new Thread(runnable);
        thread.start();
        adapter.notifyDataSetChanged();
    }

    private void clickButton() {
        btnThemNV.setOnClickListener(this);
        btnSearch.setOnClickListener(this);

        listviewNhanVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(ViewQuanLyNhanVIen.this,MainSuaNhanVIen.class);
                Bundle bundle=new Bundle();
                bundle.putString("TrangThai",MoDanhSachNhanVien.arrayListNV.get(i).getUserStatus());
                bundle.putString("id",MoDanhSachNhanVien.arrayListNV.get(i).getId());
                bundle.putString("tenNV",MoDanhSachNhanVien.arrayListNV.get(i).getStaffName());
                bundle.putString("soDT",MoDanhSachNhanVien.arrayListNV.get(i).getStaffPhone());
                bundle.putString("queQuan",MoDanhSachNhanVien.arrayListNV.get(i).getStaffAddress());
                bundle.putString("ngaySinh",MoDanhSachNhanVien.arrayListNV.get(i).getDateOfBirth());
                bundle.putString("soCMT",MoDanhSachNhanVien.arrayListNV.get(i).getIdCardNumber());
                intent.putExtra("thongtinNV",bundle);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        btnThemNV=findViewById(R.id.btn_themVN);
        listviewNhanVien=findViewById(R.id.lst_nhanvien);
        btnSearch=findViewById(R.id.btn_search);
        edtSearch=findViewById(R.id.edt_search);
        listViewNVTimKiem=findViewById(R.id.lst_nhanvientimkiem);
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
            case R.id.btn_search:{
                listviewNhanVien.setVisibility(View.GONE);
                listViewNVTimKiem.setVisibility(View.VISIBLE);
                preTimKiemNhanVien.TimKiemNhanVien(edtSearch.getText().toString().trim());
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
    public void onFailed(String t) {
        Toast.makeText(this,t,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccessed1() {

        adapter=new NhanVienAdapter(this,R.layout.itemlstnhanvien, MoTimKiemNV.arrTimKiem);
        listViewNVTimKiem.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFailed1() {
        Toast.makeText(this,"Lỗi tìm kiếm",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFialed2() {
        listViewNVTimKiem.setVisibility(View.GONE);
        listviewNhanVien.setVisibility(View.VISIBLE);
        adapter=new NhanVienAdapter(this,R.layout.itemlstnhanvien, MoDanhSachNhanVien.arrayListNV);
        listviewNhanVien.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
