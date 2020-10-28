package com.example.manhvan.datn_mocsneaker.View.QuanLySanPham;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.manhvan.datn_mocsneaker.R;
import com.example.manhvan.datn_mocsneaker.util.AndroidDeviceHelper;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainSuaSanPham extends AppCompatActivity {
    private ActionBar actionBar;
    private ImageView imgViewUpdate;
    private EditText edtProductName,edtProductPrice,edtProductContent;
    private Button btnUpdateImage;
    private final int REQUEST_PICK =123;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sua_san_pham);
        actionBar=getSupportActionBar();
        actionBar.setTitle("Sửa thông tin sản phẩm");
        actionBar.setDisplayHomeAsUpEnabled(true);
        initView();
        showProductInfo();
        getImageFromPick();
    }

    private void initView() {
        imgViewUpdate=findViewById(R.id.img_ChinhsuaSP);
        edtProductName=findViewById(R.id.edt_tenSuaSP);
        edtProductPrice=findViewById(R.id.edt_GiaSuaSP);
        edtProductContent=findViewById(R.id.edt_thongTinSPSua);
        btnUpdateImage=findViewById(R.id.btn_chonAnhSua);
        recyclerView=findViewById(R.id.recycle_CTAnhSP);

        imgViewUpdate.getLayoutParams().width= AndroidDeviceHelper.getWithScreen(this);
        imgViewUpdate.getLayoutParams().height=AndroidDeviceHelper.getWithScreen(this);
        imgViewUpdate.requestLayout();

    }

    public void showProductInfo(){
        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("info");
        Glide.with(this).load(bundle.getString("image")).into(imgViewUpdate);
        edtProductName.setText(bundle.getString("productName"));
        edtProductPrice.setText(bundle.getString("productPrice"));
        edtProductContent.setText(bundle.getString("productContent"));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                finish();
                break;
            }
            case R.id.mnuCheck:{
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void getImageFromPick(){
        btnUpdateImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_PICK);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menucheck,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode==REQUEST_PICK&&resultCode==RESULT_OK&&data!=null){
            Uri uri=data.getData();
            try {
                InputStream inputStream=getContentResolver().openInputStream(uri);
                Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                imgViewUpdate.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}