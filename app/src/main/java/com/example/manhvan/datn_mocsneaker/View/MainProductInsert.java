package com.example.manhvan.datn_mocsneaker.View;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.manhvan.datn_mocsneaker.R;
import com.example.manhvan.datn_mocsneaker.util.AndroidDeviceHelper;

public class MainProductInsert extends AppCompatActivity {
    private ActionBar actionBar;
    private ImageView imageView;
    private TextView txtProductName;
    private RadioButton radioButton39,radioButton40,radioButton41,radioButton42,radioButton43;
    private EditText edtProductQuantity;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_product_insert);
        actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Sản phẩm nhập");
        initView();
//        getData();
    }

    private void initView() {
        imageView=findViewById(R.id.image_in);
        imageView.getLayoutParams().width= AndroidDeviceHelper.getWithScreen(this)-16;
        imageView.requestLayout();
        txtProductName=findViewById(R.id.txt_ProductInName);
        radioButton39=findViewById(R.id.rad_btn39);
        radioButton40=findViewById(R.id.rad_btn40);
        radioButton41=findViewById(R.id.rad_btn41);
        radioButton42=findViewById(R.id.rad_btn42);
        radioButton43=findViewById(R.id.rad_btn43);
        edtProductQuantity=findViewById(R.id.edt_ProductQuantity);
        btnAdd=findViewById(R.id.btn_Add);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}