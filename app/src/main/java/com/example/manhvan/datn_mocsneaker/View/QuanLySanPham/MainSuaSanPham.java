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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.manhvan.datn_mocsneaker.Model.MoKichCoTheoSP;
import com.example.manhvan.datn_mocsneaker.Model.MogetProductImage;
import com.example.manhvan.datn_mocsneaker.Presenter.PreGetProductImage;
import com.example.manhvan.datn_mocsneaker.Presenter.PreKichCoTheoSP;
import com.example.manhvan.datn_mocsneaker.Presenter.PreSuaThongTinSP;
import com.example.manhvan.datn_mocsneaker.R;
import com.example.manhvan.datn_mocsneaker.View.PKInterface.ProductDetail;
import com.example.manhvan.datn_mocsneaker.adapter.ANhCTSPSuaAdapter;
import com.example.manhvan.datn_mocsneaker.util.AndroidDeviceHelper;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainSuaSanPham extends AppCompatActivity implements ProductDetail,ANhCTSPSuaAdapter.OnClickImageListener, View.OnClickListener, PreSuaThongTinSP.OnResultSuccessInterface {
    private ActionBar actionBar;
    private ImageView imgViewUpdate;
    private EditText edtProductName,edtProductPrice,edtProductContent;
//    private EditText edtSize39,edtSize40,edtSize41,edtSize42,edtSize43;
    private Button btnUpdateImage,btnXacNhanSua,btnHuySua;
    private final int REQUEST_PICK =123;
    private final int REQUEST_PICK_DETAIL=1234;
    private RecyclerView recyclerView;
    private ANhCTSPSuaAdapter adapter;
    private PreGetProductImage preGetProductImage;
    private int idProduct=0,idSize=1;
    private PreKichCoTheoSP preKichCoTheoSP;
    private String realPath="";
    private Spinner spinnerSuaSP;
    private EditText edtSoLuongSPSua;
    private PreSuaThongTinSP preSuaThongTinSP;


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
        onClickButton();
    }

    private void initView() {
        imgViewUpdate=findViewById(R.id.img_ChinhsuaSP);
        edtProductName=findViewById(R.id.edt_tenSuaSP);
        edtProductPrice=findViewById(R.id.edt_GiaSuaSP);
        edtProductContent=findViewById(R.id.edt_thongTinSPSua);
        btnUpdateImage=findViewById(R.id.btn_chonAnhSua);
        recyclerView=findViewById(R.id.recycle_CTAnhSP);
        spinnerSuaSP=findViewById(R.id.spiner_suaSP);
        edtSoLuongSPSua=findViewById(R.id.txt_soLuongSuaSP);
        btnXacNhanSua=findViewById(R.id.btn_xacNhanSuaSP);
        btnHuySua=findViewById(R.id.btn_HuySuaSP);
//        edtSize39=findViewById(R.id.edt_size39Sua);
//        edtSize40=findViewById(R.id.edt_size40Sua);
//        edtSize41=findViewById(R.id.edt_size41Sua);
//        edtSize42=findViewById(R.id.edt_size42Sua);
//        edtSize43=findViewById(R.id.edt_size43Sua);

        imgViewUpdate.getLayoutParams().width= AndroidDeviceHelper.getWithScreen(this);
        imgViewUpdate.getLayoutParams().height=AndroidDeviceHelper.getWithScreen(this);
        imgViewUpdate.requestLayout();

    }
    private void onClickButton(){
        btnXacNhanSua.setOnClickListener(this);
        btnHuySua.setOnClickListener(this);
    }

    public void showProductInfo(){

        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("info");
        Glide.with(this).load(bundle.getString("image")).into(imgViewUpdate);
        edtProductName.setText(bundle.getString("productName"));
        edtProductPrice.setText(bundle.getString("productPrice"));
        edtProductContent.setText(bundle.getString("productContent"));
        idProduct=Integer.parseInt(bundle.getString("idProduct"));

        ArrayList<String> arrSPN=new ArrayList<>();
        arrSPN.add("Size 39");
        arrSPN.add("Size 40");
        arrSPN.add("Size 41");
        arrSPN.add("Size 42");
        arrSPN.add("Size 43");
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,arrSPN);
        spinnerSuaSP.setAdapter(arrayAdapter);
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
//        getMenuInflater().inflate(R.menu.menucheck,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode==RESULT_OK&&data!=null){
            if (requestCode==REQUEST_PICK) {
                Uri uri = data.getData();
                realPath = getRealPathFromURI(uri);
//                Log.d("ImageUdated", realPath);
                try {
                    InputStream inputStream = getContentResolver().openInputStream(uri);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    imgViewUpdate.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }else if(requestCode==REQUEST_PICK_DETAIL){
                Uri uri = data.getData();
                realPath = getRealPathFromURI(uri);
                Log.d("ImageUdated", realPath);
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
        adapter.setListener(this);
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
    public void onSuccessed() {
        edtSoLuongSPSua.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainSuaSanPham.this,"Sửa thông tin sản phẩm thành công",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    @Override
    public void onFailed() {
        edtSoLuongSPSua.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainSuaSanPham.this,"Sửa thông tin sản phẩm thất bại",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void kichCoSuccess() {
        spinnerSuaSP.post(new Runnable() {
            @Override
            public void run() {
                edtSoLuongSPSua.setText(MoKichCoTheoSP.lstKichCo.get(0).getStock());
                spinnerSuaSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        switch (i){
                            case 0:{
                                idSize=1;
                                edtSoLuongSPSua.setText(MoKichCoTheoSP.lstKichCo.get(0).getStock());
                                break;
                            }
                            case 1:{
                                idSize=2;
                                edtSoLuongSPSua.setText(MoKichCoTheoSP.lstKichCo.get(1).getStock());
                                break;
                            }
                            case 2:{
                                idSize=3;
                                edtSoLuongSPSua.setText(MoKichCoTheoSP.lstKichCo.get(2).getStock());
                                break;
                            }
                            case 3:{
                                idSize=4;
                                edtSoLuongSPSua.setText(MoKichCoTheoSP.lstKichCo.get(3).getStock());
                                break;
                            }
                            case 4:{
                                idSize=5;
                                edtSoLuongSPSua.setText(MoKichCoTheoSP.lstKichCo.get(4).getStock());
                                break;
                            }
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
//                edtSize39.setText(MoKichCoTheoSP.lstKichCo.get(0).getStock());
//                edtSize40.setText(MoKichCoTheoSP.lstKichCo.get(1).getStock());
//                edtSize41.setText(MoKichCoTheoSP.lstKichCo.get(2).getStock());
//                edtSize42.setText(MoKichCoTheoSP.lstKichCo.get(3).getStock());
//                edtSize43.setText(MoKichCoTheoSP.lstKichCo.get(4).getStock());
            }
        });
    }

    @Override
    public void onClick(int id) {
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,REQUEST_PICK_DETAIL);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_xacNhanSuaSP:{
                preSuaThongTinSP=new PreSuaThongTinSP(this);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        preSuaThongTinSP.suaThongTinSP(idProduct,idSize,edtProductName.getText().toString().trim(),
                                edtProductContent.getText().toString().toLowerCase(),Integer.parseInt(edtProductPrice.getText().toString().trim()),
                                Integer.parseInt(edtSoLuongSPSua.getText().toString().trim()));
                    }
                }).start();
                break;
            }
            case R.id.btn_HuySuaSP:{
                finish();
                break;
            }
        }
    }
}