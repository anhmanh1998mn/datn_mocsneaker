package com.example.manhvan.datn_mocsneaker.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.manhvan.datn_mocsneaker.Model.MoKichCoTheoSP;
import com.example.manhvan.datn_mocsneaker.Presenter.PreKichCoTheoSP;
import com.example.manhvan.datn_mocsneaker.R;
import com.example.manhvan.datn_mocsneaker.entity.ChiTietDonNhap;
import com.example.manhvan.datn_mocsneaker.util.AndroidDeviceHelper;
import com.example.manhvan.datn_mocsneaker.util.DuongDan;
import com.example.manhvan.datn_mocsneaker.util.GioHang;

public class MainProductInsert extends AppCompatActivity implements ProductDetail {
    private ActionBar actionBar;
    private ImageView imageView;
    private TextView txtProductName;
    private RadioButton radioButton39, radioButton40, radioButton41, radioButton42, radioButton43, radioButtonChecked;
    private EditText edtProductQuantity;
    private Button btnAdd,btnAddSucess;
    private PreKichCoTheoSP preKichCoTheoSP;
    private int idnhan = 0;
    private RadioGroup radioGroup;

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
    }

    private void initView() {
        imageView = findViewById(R.id.image_in);
        imageView.getLayoutParams().width = AndroidDeviceHelper.getWithScreen(this);
        imageView.requestLayout();

        txtProductName = findViewById(R.id.txt_ProductInName);
        radioButton39 = findViewById(R.id.rad_btn39);
        radioButton40 = findViewById(R.id.rad_btn40);
        radioButton41 = findViewById(R.id.rad_btn41);
        radioButton42 = findViewById(R.id.rad_btn42);
        radioButton43 = findViewById(R.id.rad_btn43);
        edtProductQuantity = findViewById(R.id.edt_ProductQuantity);
        btnAdd = findViewById(R.id.btn_Add);
        radioGroup = findViewById(R.id.radiogroup_in);
        btnAddSucess=findViewById(R.id.btn_AddSucess);
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
            }
        });
    }

    public void saveData() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup1, int i) {
//                int id=radioGroup1.getCheckedRadioButtonId();
                radioButtonChecked = findViewById(i);

//                Log.d("Value:",rb.getText().toString());
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtProductQuantity.getText().toString().trim().equals("")){
                    edtProductQuantity.setError("Mời nhập số lượng");
                    return;
                }
                for(int i=0;i<GioHang.arrChiTietDonNhap.size();i++){
                    if(GioHang.arrChiTietDonNhap.get(i).getIdSanPham()==idnhan&&GioHang.arrChiTietDonNhap.get(i).getKichCo().equals(radioButtonChecked.getText())){
//                        Log.d("Check:","true");
                        GioHang.arrChiTietDonNhap.get(i).setSoLuong(GioHang.arrChiTietDonNhap.get(i).getSoLuong()+Integer.parseInt(edtProductQuantity.getText().toString()));
                        return;
                    }
                }
                GioHang.arrChiTietDonNhap.add(new ChiTietDonNhap(idnhan,Integer.parseInt(edtProductQuantity.getText().toString().trim()),radioButtonChecked.getText().toString(),MoKichCoTheoSP.lstKichCo.get(0).getProductName()));
            }
        });

        btnAddSucess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}