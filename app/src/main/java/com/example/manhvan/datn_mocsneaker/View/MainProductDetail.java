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
import android.widget.Toast;

import com.example.manhvan.datn_mocsneaker.Model.MogetProductImage;
import com.example.manhvan.datn_mocsneaker.Presenter.PreGetProductImage;
import com.example.manhvan.datn_mocsneaker.R;
import com.example.manhvan.datn_mocsneaker.adapter.ProductImageAdapter;

import java.text.DecimalFormat;

public class MainProductDetail extends AppCompatActivity implements ProductDetail{
    private ActionBar actionBar;
    private RecyclerView recyclerViewProductDetail;
    private PreGetProductImage preGetProductImage;
    private ProductImageAdapter adapter;
    private TextView txtProductName,txtProductPrice,txtProductContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_product_detail);
        actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Chi tiết sản phẩm");
        initView();
        dulieunhan();
    }

    private void initView() {
        recyclerViewProductDetail=findViewById(R.id.recycle_prdetail);
        txtProductName=findViewById(R.id.txt_tenSPDetail);
        txtProductPrice=findViewById(R.id.txt_giaDetail);

    }

    private void dulieunhan() {
        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("ProductInfo");
        txtProductName.setText(bundle.getString("name"));
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        txtProductPrice.setText(decimalFormat.format(Integer.parseInt(bundle.getString("price")))+" Vnđ");

        final String id=bundle.getString("idAnh");
//        Toast.makeText(this,intent.getStringExtra("price"), Toast.LENGTH_SHORT).show();
        preGetProductImage=new PreGetProductImage(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                preGetProductImage.GetProductImage(Integer.parseInt(id));
            }
        }).start();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                finish();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSuccess() {
        adapter=new ProductImageAdapter(this,R.layout.itemproductimage, MogetProductImage.lstIMG);
        final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this, LinearLayout.HORIZONTAL,false);
        recyclerViewProductDetail.post(new Runnable() {
            @Override
            public void run() {
                recyclerViewProductDetail.setLayoutManager(linearLayoutManager);
                recyclerViewProductDetail.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public void onFailed() {
        Toast.makeText(this,"Lỗi",Toast.LENGTH_SHORT).show();
    }
}