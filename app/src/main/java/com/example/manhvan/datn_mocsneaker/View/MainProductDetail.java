package com.example.manhvan.datn_mocsneaker.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manhvan.datn_mocsneaker.Model.MoKichCoTheoSP;
import com.example.manhvan.datn_mocsneaker.Model.MogetProductImage;
import com.example.manhvan.datn_mocsneaker.Presenter.PreGetProductImage;
import com.example.manhvan.datn_mocsneaker.Presenter.PreKichCoTheoSP;
import com.example.manhvan.datn_mocsneaker.R;
import com.example.manhvan.datn_mocsneaker.adapter.ProductImageAdapter;

import java.text.DecimalFormat;

public class MainProductDetail extends AppCompatActivity implements ProductDetail, View.OnClickListener {
    private ActionBar actionBar;
    private RecyclerView recyclerViewProductDetail;
    private PreGetProductImage preGetProductImage;
    private ProductImageAdapter adapter;
    private TextView txtProductName, txtProductPrice, txtProductContent, btnThemSLSP, btnGiamSLSP;
    private PreKichCoTheoSP preKichCoTheoSP;
    private RadioButton ra39, ra40, ra41, ra42, ra43;
    private RadioGroup radioGroup;
    private EditText edtSoLuongSP;
    private Button btnThemVaoGioHang,btnMuaSP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_product_detail);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Chi tiết sản phẩm");
        initView();
        eventClick();
        dulieunhan();
        kiemTraSoLuongSPMua();
    }



    private void eventClick() {
        btnGiamSLSP.setOnClickListener(this);
        btnThemSLSP.setOnClickListener(this);
        btnThemVaoGioHang.setOnClickListener(this);
        btnMuaSP.setOnClickListener(this);
    }

    private void initView() {
        recyclerViewProductDetail = findViewById(R.id.recycle_prdetail);
        txtProductName = findViewById(R.id.txt_tenSPDetail);
        txtProductPrice = findViewById(R.id.txt_giaDetail);
        txtProductContent = findViewById(R.id.txt_ttsanpham);
        ra39 = findViewById(R.id.radiobtn_detail1);
        ra40 = findViewById(R.id.radiobtn_detail2);
        ra41 = findViewById(R.id.radiobtn_detail3);
        ra42 = findViewById(R.id.radiobtn_detail4);
        ra43 = findViewById(R.id.radiobtn_detail5);
        radioGroup = findViewById(R.id.radiogroup);
        btnThemSLSP = findViewById(R.id.btn_themSLSP);
        btnGiamSLSP = findViewById(R.id.btn_giamSLSP);
        edtSoLuongSP = findViewById(R.id.edt_soLuongsp);
        btnThemVaoGioHang=findViewById(R.id.btn_prdetailMua);
        btnMuaSP=findViewById(R.id.btn_prdetailMua2);
        ra39.setChecked(true);
    }

    private void dulieunhan() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("ProductInfo");
        txtProductName.setText(bundle.getString("name"));
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtProductPrice.setText(decimalFormat.format(Integer.parseInt(bundle.getString("price"))) + " đ");
        txtProductContent.setText(bundle.getString("content1"));

        final String id = bundle.getString("idAnh");
        Log.d("Idsanpham", id);
        //Hiện chi tiết ảnh sản phẩm
//        Toast.makeText(this,intent.getStringExtra("price"), Toast.LENGTH_SHORT).show();
        preGetProductImage = new PreGetProductImage(this);
        preKichCoTheoSP = new PreKichCoTheoSP(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                preGetProductImage.GetProductImage(Integer.parseInt(id));
                preKichCoTheoSP.kichCoTheoSanPham(Integer.parseInt(id));
            }
        }).start();

        //lấy số lượng sản phẩm theo kích cỡ


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSuccess() {
        adapter = new ProductImageAdapter(this, R.layout.itemproductimage, MogetProductImage.lstIMG);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayout.HORIZONTAL, false);
        recyclerViewProductDetail.post(new Runnable() {
            @Override
            public void run() {
                recyclerViewProductDetail.setLayoutManager(linearLayoutManager);
                recyclerViewProductDetail.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                //Vuốt ảnh về chính giữa trong recycleView
                SnapHelper snapHelper = new LinearSnapHelper();
                snapHelper.attachToRecyclerView(recyclerViewProductDetail);
                recyclerViewProductDetail.setNestedScrollingEnabled(true);
            }
        });

    }

    @Override
    public void onFailed() {
        Toast.makeText(this, "Lỗi", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void kichCoSuccess() {

        radioGroup.post(new Runnable() {
            @Override
            public void run() {

                if (Integer.parseInt(MoKichCoTheoSP.lstKichCo.get(0).getStock()) < 1) {
                    ra39.setVisibility(View.GONE);
                } else if (Integer.parseInt(MoKichCoTheoSP.lstKichCo.get(1).getStock()) < 1) {
                    ra40.setVisibility(View.GONE);
                } else if (Integer.parseInt(MoKichCoTheoSP.lstKichCo.get(2).getStock()) < 1) {
                    ra41.setVisibility(View.GONE);
                } else if (Integer.parseInt(MoKichCoTheoSP.lstKichCo.get(3).getStock()) < 1) {
                    ra42.setVisibility(View.GONE);
                } else if (Integer.parseInt(MoKichCoTheoSP.lstKichCo.get(4).getStock()) < 1) {
                    ra43.setVisibility(View.GONE);
                }

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_giamSLSP:{
                if(edtSoLuongSP.getText().toString().trim().equals("")||Integer.parseInt(edtSoLuongSP.getText().toString().trim())<=1){
                    edtSoLuongSP.setText("1");
                    return;
                }
                edtSoLuongSP.setText(String.valueOf(Integer.parseInt(edtSoLuongSP.getText().toString().trim())-1));
                break;
            }
            case R.id.btn_themSLSP:{
                edtSoLuongSP.setText(String.valueOf(Integer.parseInt(edtSoLuongSP.getText().toString().trim())+1));
                break;
            }
            case R.id.btn_prdetailMua:{

                break;
            }
            case R.id.btn_prdetailMua2:{
                break;
            }
        }
    }
    private void kiemTraSoLuongSPMua() {
        edtSoLuongSP.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
               if (editable.toString().equals("")){
                   return;
               }
                int soLuong=Integer.parseInt(editable.toString().trim());
                if(ra39.isChecked()){
                    if (soLuong>Integer.parseInt(MoKichCoTheoSP.lstKichCo.get(0).getStock())){
                        edtSoLuongSP.setText(MoKichCoTheoSP.lstKichCo.get(0).getStock());
                        Toast.makeText(MainProductDetail.this,"Quý khách chỉ được mua tối đa "+MoKichCoTheoSP.lstKichCo.get(0).getStock()+" sản phẩm",Toast.LENGTH_SHORT).show();
                        return;
                    }
                }else if(ra40.isChecked()){
                    if (soLuong>Integer.parseInt(MoKichCoTheoSP.lstKichCo.get(1).getStock())){
                        edtSoLuongSP.setText(MoKichCoTheoSP.lstKichCo.get(1).getStock());
                        Toast.makeText(MainProductDetail.this,"Quý khách chỉ được mua tối đa "+MoKichCoTheoSP.lstKichCo.get(1).getStock()+" sản phẩm",Toast.LENGTH_SHORT).show();
                        return;
                    }
                }else if(ra41.isChecked()){
                    if(soLuong>Integer.parseInt(MoKichCoTheoSP.lstKichCo.get(2).getStock())){
                        edtSoLuongSP.setText(MoKichCoTheoSP.lstKichCo.get(2).getStock());
                        Toast.makeText(MainProductDetail.this,"Quý khách chỉ được mua tối đa "+MoKichCoTheoSP.lstKichCo.get(2).getStock()+" sản phẩm",Toast.LENGTH_SHORT).show();
                        return;
                    }
                }else if(ra42.isChecked()){
                    if(soLuong>Integer.parseInt(MoKichCoTheoSP.lstKichCo.get(3).getStock())){
                        edtSoLuongSP.setText(MoKichCoTheoSP.lstKichCo.get(3).getStock());
                        Toast.makeText(MainProductDetail.this,"Quý khách chỉ được mua tối đa "+MoKichCoTheoSP.lstKichCo.get(3).getStock()+" sản phẩm",Toast.LENGTH_SHORT).show();
                        return;
                    }
                }else if(ra43.isChecked()){
                    if(soLuong>Integer.parseInt(MoKichCoTheoSP.lstKichCo.get(4).getStock())){
                        edtSoLuongSP.setText(MoKichCoTheoSP.lstKichCo.get(4).getStock());
                        Toast.makeText(MainProductDetail.this,"Quý khách chỉ được mua tối đa "+MoKichCoTheoSP.lstKichCo.get(4).getStock()+" sản phẩm",Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            }
        });
    }
}