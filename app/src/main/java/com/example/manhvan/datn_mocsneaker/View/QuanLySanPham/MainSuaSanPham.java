package com.example.manhvan.datn_mocsneaker.View.QuanLySanPham;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.manhvan.datn_mocsneaker.Model.MoKichCoTheoSP;
import com.example.manhvan.datn_mocsneaker.Model.MogetProductImage;
import com.example.manhvan.datn_mocsneaker.Presenter.PreGetProductImage;
import com.example.manhvan.datn_mocsneaker.Presenter.PreKichCoTheoSP;
import com.example.manhvan.datn_mocsneaker.R;
import com.example.manhvan.datn_mocsneaker.View.PKInterface.ProductDetail;
import com.example.manhvan.datn_mocsneaker.adapter.ANhCTSPSuaAdapter;
import com.example.manhvan.datn_mocsneaker.util.AndroidDeviceHelper;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainSuaSanPham extends AppCompatActivity implements ProductDetail {
    private ActionBar actionBar;
    private ImageView imgViewUpdate;
    private EditText edtProductName,edtProductPrice,edtProductContent,edtSize39,edtSize40,edtSize41,edtSize42,edtSize43;
    private Button btnUpdateImage;
    private final int REQUEST_PICK =123;
    private RecyclerView recyclerView;
    private ANhCTSPSuaAdapter adapter;
    private PreGetProductImage preGetProductImage;
    private int idProduct=0;
    private PreKichCoTheoSP preKichCoTheoSP;
    private String realPath="";


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
        getListImageDetail();
    }

    private void initView() {
        imgViewUpdate=findViewById(R.id.img_ChinhsuaSP);
        edtProductName=findViewById(R.id.edt_tenSuaSP);
        edtProductPrice=findViewById(R.id.edt_GiaSuaSP);
        edtProductContent=findViewById(R.id.edt_thongTinSPSua);
        btnUpdateImage=findViewById(R.id.btn_chonAnhSua);
        recyclerView=findViewById(R.id.recycle_CTAnhSP);
        edtSize39=findViewById(R.id.edt_size39Sua);
        edtSize40=findViewById(R.id.edt_size40Sua);
        edtSize41=findViewById(R.id.edt_size41Sua);
        edtSize42=findViewById(R.id.edt_size42Sua);
        edtSize43=findViewById(R.id.edt_size43Sua);

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
        idProduct=Integer.parseInt(bundle.getString("idProduct"));
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
            realPath=getRealPathFromURI(uri);
            Log.d("ImageUdated",realPath);
            try {
                InputStream inputStream=getContentResolver().openInputStream(uri);
                Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                imgViewUpdate.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            //đ
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void getListImageDetail(){
        preGetProductImage=new PreGetProductImage(this);
        preKichCoTheoSP=new PreKichCoTheoSP(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                preGetProductImage.GetProductImage(idProduct);
                preKichCoTheoSP.kichCoTheoSanPham(idProduct);
            }
        }).start();

    }

    private String getRealPathFromURI(Uri uri) {
        String path = "";
        String[] proj = {MediaStore.MediaColumns.DATA};
        Cursor cursor = getContentResolver().query(uri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            path = cursor.getString(column_index);

        }
        cursor.close();
        return path;
    }

    @Override
    public void onSuccess() {
        adapter=new ANhCTSPSuaAdapter(this,R.layout.item_sua_san_pham, MogetProductImage.lstIMG);
        final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this, LinearLayout.HORIZONTAL,false);
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }
        });
    }

    @Override
    public void onFailed() {

    }

    @Override
    public void kichCoSuccess() {
        edtSize43.post(new Runnable() {
            @Override
            public void run() {
                edtSize39.setText(MoKichCoTheoSP.lstKichCo.get(0).getStock());
                edtSize40.setText(MoKichCoTheoSP.lstKichCo.get(1).getStock());
                edtSize41.setText(MoKichCoTheoSP.lstKichCo.get(2).getStock());
                edtSize42.setText(MoKichCoTheoSP.lstKichCo.get(3).getStock());
                edtSize43.setText(MoKichCoTheoSP.lstKichCo.get(4).getStock());
            }
        });
    }
}