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
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.manhvan.datn_mocsneaker.R;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainQLThemSanPham extends AppCompatActivity implements View.OnClickListener {
    private ActionBar actionBar;
    private ImageView imgAnhChinh;
    private Button btnChonAnh,btnThemSP,btnHuyThem;
    private EditText edtTenSP,edtGiaSP,edtSoLuong39,edtSoLuong40,edtSoLuong41,edtSoLuong42,edtSoLuong43,edtNoiDung;
    private final int REQUEST_PICK=123;
    private boolean chonAnh=false;
    private String realPath="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_q_l_them_san_pham);
        actionBar=getSupportActionBar();
        actionBar.setTitle("Thêm mới sản phẩm");
        actionBar.setDisplayHomeAsUpEnabled(true);
        initView();
        eventClick();
    }

    private void initView() {
        imgAnhChinh=findViewById(R.id.img_themSP);
        btnChonAnh=findViewById(R.id.btn_chonAnh);
        btnThemSP=findViewById(R.id.btn_themSPXacNhan);
        btnHuyThem=findViewById(R.id.btn_huyThemXacNhan);
        edtTenSP=findViewById(R.id.edt_tenSPQL);
        edtGiaSP=findViewById(R.id.edt_giaSPQL);
        edtSoLuong39=findViewById(R.id.edt_size39);
        edtSoLuong40=findViewById(R.id.edt_size40);
        edtSoLuong41=findViewById(R.id.edt_size41);
        edtSoLuong42=findViewById(R.id.edt_size42);
        edtSoLuong43=findViewById(R.id.edt_size43);
        edtNoiDung=findViewById(R.id.txt_thongTinSPQL);
    }
    public void eventClick(){
        btnChonAnh.setOnClickListener(this);
        btnThemSP.setOnClickListener(this);
        btnHuyThem.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_chonAnh:{
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_PICK);
                break;
            }
            case R.id.btn_themSPXacNhan:{
                themMoiSP();
                break;
            }
            case R.id.btn_huyThemXacNhan:{
                break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==REQUEST_PICK&&resultCode==RESULT_OK&&data!=null){
            Uri uri=data.getData();
            realPath=getRealPathFromURI(uri);
            Log.d("path",realPath);
            try {
                InputStream inputStream=getContentResolver().openInputStream(uri);
                Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                imgAnhChinh.setImageBitmap(bitmap);
                chonAnh=true;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    private void themMoiSP(){
        if(chonAnh==false||edtTenSP.equals("")||edtGiaSP.equals("")||edtSoLuong39.equals("")
        ||edtSoLuong40.equals("")||edtSoLuong41.equals("")||edtSoLuong42.equals("")||edtSoLuong43.equals("")
        ||edtNoiDung.equals("")){
            Toast.makeText(MainQLThemSanPham.this,"Kiểm tra lại dữ liệu",Toast.LENGTH_SHORT).show();
            return;
        }

    }
    private String getRealPathFromURI(Uri uri){
        String path="";
        String[] proj={MediaStore.MediaColumns.DATA};
        Cursor cursor=getContentResolver().query(uri,proj,null,null,null);
        if(cursor.moveToFirst()){
            int column_index=cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            path=cursor.getString(column_index);

        }
        cursor.close();
        return path;
    }
}