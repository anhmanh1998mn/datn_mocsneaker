package com.example.manhvan.datn_mocsneaker.View.QuanLyDonNhapHang;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.manhvan.datn_mocsneaker.Model.MoKichCoTheoSP;
import com.example.manhvan.datn_mocsneaker.Presenter.PreKichCoTheoSP;
import com.example.manhvan.datn_mocsneaker.R;
import com.example.manhvan.datn_mocsneaker.View.PKInterface.ProductDetail;
import com.example.manhvan.datn_mocsneaker.entity.ChiTietDonNhap;
import com.example.manhvan.datn_mocsneaker.util.AndroidDeviceHelper;
import com.example.manhvan.datn_mocsneaker.util.DuongDan;
import com.example.manhvan.datn_mocsneaker.util.GioHang;

public class MainProductInsert extends AppCompatActivity implements ProductDetail {
    private ActionBar actionBar;
    private ImageView imageView;
    private TextView txtProductName, txtKichCoSoLuong, getTxtKichCoSoLuong2;
    //    private RadioButton radioButton39, radioButton40, radioButton41, radioButton42, radioButton43, radioButtonChecked;
//    private EditText edtProductQuantity;
    private Button btnAdd, btnAddSucess;
    private PreKichCoTheoSP preKichCoTheoSP;
    private int idnhan = 0;
    private EditText edtS39Nhap, edtS40Nhap, edtS41Nhap, edtS42Nhap, edtS43Nhap;
//    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_product_insert);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Sản phẩm nhập");
        initView();
        getData();
        saveData();
        checkQuantitySize();
    }

    private void initView() {
        imageView = findViewById(R.id.image_in);
        imageView.getLayoutParams().width = AndroidDeviceHelper.getWithScreen(this);
        imageView.requestLayout();

        txtProductName = findViewById(R.id.txt_ProductInName);
//        radioButton39 = findViewById(R.id.rad_btn39);
//        radioButton40 = findViewById(R.id.rad_btn40);
//        radioButton41 = findViewById(R.id.rad_btn41);
//        radioButton42 = findViewById(R.id.rad_btn42);
//        radioButton43 = findViewById(R.id.rad_btn43);
//        edtProductQuantity = findViewById(R.id.edt_ProductQuantity);
        btnAdd = findViewById(R.id.btn_Add);
//        radioGroup = findViewById(R.id.radiogroup_in);
        btnAddSucess = findViewById(R.id.btn_AddSucess);
        txtKichCoSoLuong = findViewById(R.id.txt_KichCoSoLuongIN);
        getTxtKichCoSoLuong2 = findViewById(R.id.txt_KichCoSoLuongIN2);
        edtS39Nhap = findViewById(R.id.edt_size39Nhap);
        edtS40Nhap = findViewById(R.id.edt_size40Nhap);
        edtS41Nhap = findViewById(R.id.edt_size41Nhap);
        edtS42Nhap = findViewById(R.id.edt_size42Nhap);
        edtS43Nhap = findViewById(R.id.edt_size43Nhap);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    public void getData() {
        Intent intent = getIntent();
        idnhan = Integer.parseInt(intent.getStringExtra("ProductInfoID"));
//        Log.d("idNhan:",idNhan+"");
        preKichCoTheoSP = new PreKichCoTheoSP(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                preKichCoTheoSP.kichCoTheoSanPham(idnhan);
            }
        }).start();
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFailed() {

    }

    @Override
    public void kichCoSuccess() {
        txtProductName.post(new Runnable() {
            @Override
            public void run() {
                Glide.with(MainProductInsert.this).load(DuongDan.url + MoKichCoTheoSP.lstKichCo.get(0).getProductUrl()).into(imageView);
                txtProductName.setText(MoKichCoTheoSP.lstKichCo.get(0).getProductName());
                txtKichCoSoLuong.setText("Size 39: " + MoKichCoTheoSP.lstKichCo.get(0).getStock() +
                        "     Size 40: " + MoKichCoTheoSP.lstKichCo.get(1).getStock());
                getTxtKichCoSoLuong2.setText("Size 41: " + MoKichCoTheoSP.lstKichCo.get(2).getStock() +
                        "     Size 42: " + MoKichCoTheoSP.lstKichCo.get(3).getStock() +
                        "     Size 43: " + MoKichCoTheoSP.lstKichCo.get(4).getStock());
            }
        });
    }

    public void saveData() {


//        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup1, int i) {
////                int id=radioGroup1.getCheckedRadioButtonId();
//                radioButtonChecked = findViewById(i);
//
////                Log.d("Value:",rb.getText().toString());
//            }
//        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtS39Nhap.getText().toString().trim().equals("") && edtS40Nhap.getText().toString().trim().equals("") &&
                        edtS41Nhap.getText().toString().trim().equals("") && edtS42Nhap.getText().toString().trim().equals("") &&
                        edtS43Nhap.getText().toString().trim().equals("")) {
                    Toast.makeText(MainProductInsert.this, "Vui lòng nhập số lượng kích cỡ muốn nhập", Toast.LENGTH_SHORT).show();
                    return;
                }

                GioHang.arrChiTietDonNhap.add(new ChiTietDonNhap(idnhan, Integer.parseInt(edtS39Nhap.getText().toString().trim()),
                        Integer.parseInt(MoKichCoTheoSP.lstKichCo.get(0).getPriceIn()),
                        "39", MoKichCoTheoSP.lstKichCo.get(0).getProductName()));
                GioHang.arrChiTietDonNhap.add(new ChiTietDonNhap(idnhan, Integer.parseInt(edtS40Nhap.getText().toString().trim()),
                        Integer.parseInt(MoKichCoTheoSP.lstKichCo.get(0).getPriceIn()),
                        "40", MoKichCoTheoSP.lstKichCo.get(0).getProductName()));
                GioHang.arrChiTietDonNhap.add(new ChiTietDonNhap(idnhan, Integer.parseInt(edtS41Nhap.getText().toString().trim()),
                        Integer.parseInt(MoKichCoTheoSP.lstKichCo.get(0).getPriceIn()),
                        "41", MoKichCoTheoSP.lstKichCo.get(0).getProductName()));
                GioHang.arrChiTietDonNhap.add(new ChiTietDonNhap(idnhan, Integer.parseInt(edtS42Nhap.getText().toString().trim()),
                        Integer.parseInt(MoKichCoTheoSP.lstKichCo.get(0).getPriceIn()),
                        "42", MoKichCoTheoSP.lstKichCo.get(0).getProductName()));
                GioHang.arrChiTietDonNhap.add(new ChiTietDonNhap(idnhan, Integer.parseInt(edtS43Nhap.getText().toString().trim()),
                        Integer.parseInt(MoKichCoTheoSP.lstKichCo.get(0).getPriceIn()),
                        "43", MoKichCoTheoSP.lstKichCo.get(0).getProductName()));

                for(int i=0;i<GioHang.arrChiTietDonNhap.size();i++){
                    if(GioHang.arrChiTietDonNhap.get(i).getSoLuong()==0){
                        GioHang.arrChiTietDonNhap.remove(i);
                    }
                }

                for(int i=0;i<GioHang.arrChiTietDonNhap.size();i++){
                    for(int j=i+1;j<GioHang.arrChiTietDonNhap.size();j++){
                        if(GioHang.arrChiTietDonNhap.get(i).getIdSanPham()==GioHang.arrChiTietDonNhap.get(j).getIdSanPham()&&
                                GioHang.arrChiTietDonNhap.get(i).getKichCo().equals(GioHang.arrChiTietDonNhap.get(j).getKichCo())){
                            GioHang.arrChiTietDonNhap.get(i).setSoLuong(GioHang.arrChiTietDonNhap.get(i).getSoLuong()+
                                    GioHang.arrChiTietDonNhap.get(j).getSoLuong());
                            GioHang.arrChiTietDonNhap.remove(j);
                        }
                    }
                }
                finish();

//                for (int i = 0; i < GioHang.arrChiTietDonNhap.size(); i++) {
//                    if (GioHang.arrChiTietDonNhap.get(i).getIdSanPham() == idnhan && GioHang.arrChiTietDonNhap.get(i).getKichCo().equals("39")) {
//                        GioHang.arrChiTietDonNhap.get(i).setSoLuong(GioHang.arrChiTietDonNhap.get(i).getSoLuong() + Integer.parseInt(edtS39Nhap.getText().toString()));
//                        Toast.makeText(MainProductInsert.this, "Đã thêm", Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                }
//                GioHang.arrChiTietDonNhap.add(new ChiTietDonNhap(idnhan, Integer.parseInt(edtS39Nhap.getText().toString().trim()),
//                        Integer.parseInt(MoKichCoTheoSP.lstKichCo.get(0).getPriceIn()),
//                        "39", MoKichCoTheoSP.lstKichCo.get(0).getProductName()));
//                }


//                if(edtProductQuantity.getText().toString().trim().equals("")){
//                    edtProductQuantity.setError("Mời nhập số lượng");
//                    return;
//                }
//                for(int i=0;i<GioHang.arrChiTietDonNhap.size();i++){
//                    if(GioHang.arrChiTietDonNhap.get(i).getIdSanPham()==idnhan&&GioHang.arrChiTietDonNhap.get(i).getKichCo().equals(radioButtonChecked.getText())){
////                        Log.d("Check:","true");
//                        GioHang.arrChiTietDonNhap.get(i).setSoLuong(GioHang.arrChiTietDonNhap.get(i).getSoLuong()+Integer.parseInt(edtProductQuantity.getText().toString()));
//                        Toast.makeText(MainProductInsert.this,"Đã thêm",Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                }
//                GioHang.arrChiTietDonNhap.add(new ChiTietDonNhap(idnhan,Integer.parseInt(edtProductQuantity.getText().toString().trim()),Integer.parseInt(MoKichCoTheoSP.lstKichCo.get(0).getPriceIn()),radioButtonChecked.getText().toString(),MoKichCoTheoSP.lstKichCo.get(0).getProductName()));
//                Toast.makeText(MainProductInsert.this,"Đã thêm",Toast.LENGTH_SHORT).show();
            }
        });

        btnAddSucess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void checkQuantitySize(){
        edtS39Nhap.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString().equals("")){
                    edtS39Nhap.setText("0");
                }
            }
        });

        edtS40Nhap.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString().equals("")){
                    edtS40Nhap.setText("0");
                }
            }
        });

        edtS41Nhap.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString().equals("")){
                    edtS41Nhap.setText("0");
                }
            }
        });

        edtS42Nhap.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString().equals("")){
                    edtS42Nhap.setText("0");
                }
            }
        });

        edtS43Nhap.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString().equals("")){
                    edtS43Nhap.setText("0");
                }
            }
        });
    }

}