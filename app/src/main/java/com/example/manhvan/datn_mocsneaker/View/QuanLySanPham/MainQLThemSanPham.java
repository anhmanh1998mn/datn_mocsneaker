package com.example.manhvan.datn_mocsneaker.View.QuanLySanPham;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.manhvan.datn_mocsneaker.Presenter.PreThemMoiSanPham;
import com.example.manhvan.datn_mocsneaker.R;
import com.example.manhvan.datn_mocsneaker.util.GioHang;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainQLThemSanPham extends AppCompatActivity implements View.OnClickListener, PreThemMoiSanPham.KetQuaThemMoiSPInterface {
    private ActionBar actionBar;
    private LinearLayout li1, li2, li3, li4, li5;
    private ImageView imgAnh1, imgAnh2, imgAnh3, imgAnh4, imgAnh5;
    private Button btnChon1, btnChon2, btnChon3, btnChon4, btnChon5, btnTiepTuc1, btnTiepTuc2, btnTiepTuc3, btnTiepTuc4, btnTiepTuc5;
    private Button btnXacNhan1, btnXacNhan2, btnXacNhan3, btnXacNhan4, btnXacNhan5;
    private ImageView imgAnhChinh;
    private Button btnChonAnh, btnThemSP, btnHuyThem;
    private EditText edtTenSP, edtGiaSP, edtSoLuong39, edtSoLuong40, edtSoLuong41, edtSoLuong42, edtSoLuong43, edtNoiDung;
    private final int REQUEST_PICK = 123, REQUEST_PICK1 = 1231, REQUEST_PICK2 = 1232, REQUEST_PICK3 = 1233, REQUEST_PICK4 = 1234, REQUEST_PICK5 = 1235;
    private boolean chonAnh = false;
    private String realPath = "", realPath1 = "", realPath2 = "", realPath3 = "", realPath4 = "", realPath5 = "";
    private PreThemMoiSanPham preThemMoiSanPham;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_q_l_them_san_pham);
        actionBar = getSupportActionBar();
        actionBar.setTitle("Thêm mới sản phẩm");
        actionBar.setDisplayHomeAsUpEnabled(true);
        initView();
        eventClick();
    }

    private void initView() {
        imgAnhChinh = findViewById(R.id.img_themSP);
        btnChonAnh = findViewById(R.id.btn_chonAnh);
        btnThemSP = findViewById(R.id.btn_themSPXacNhan);
        btnHuyThem = findViewById(R.id.btn_huyThemXacNhan);
        edtTenSP = findViewById(R.id.edt_tenSPQL);
        edtGiaSP = findViewById(R.id.edt_giaSPQL);
        edtSoLuong39 = findViewById(R.id.edt_size39);
        edtSoLuong40 = findViewById(R.id.edt_size40);
        edtSoLuong41 = findViewById(R.id.edt_size41);
        edtSoLuong42 = findViewById(R.id.edt_size42);
        edtSoLuong43 = findViewById(R.id.edt_size43);
        edtNoiDung = findViewById(R.id.txt_thongTinSPQL);

        imgAnh1 = findViewById(R.id.img_anh1);
        imgAnh2 = findViewById(R.id.img_anh2);
        imgAnh3 = findViewById(R.id.img_anh3);
        imgAnh4 = findViewById(R.id.img_anh4);
        imgAnh5 = findViewById(R.id.img_anh5);
        btnChon1 = findViewById(R.id.btn_chonmota1);
        btnChon2 = findViewById(R.id.btn_chonmota2);
        btnChon3 = findViewById(R.id.btn_chonmota3);
        btnChon4 = findViewById(R.id.btn_chonmota4);
        btnChon5 = findViewById(R.id.btn_chonmota5);
        btnXacNhan1 = findViewById(R.id.btn_xacNhan1);
        btnXacNhan2 = findViewById(R.id.btn_xacNhan2);
        btnXacNhan3 = findViewById(R.id.btn_xacNhan3);
        btnXacNhan4 = findViewById(R.id.btn_xacNhan4);
        btnXacNhan5 = findViewById(R.id.btn_xacNhan5);

        btnTiepTuc1 = findViewById(R.id.btn_themmota1);
        btnTiepTuc2 = findViewById(R.id.btn_themmota2);
        btnTiepTuc3 = findViewById(R.id.btn_themmota3);
        btnTiepTuc4 = findViewById(R.id.btn_themmota4);
        btnTiepTuc5 = findViewById(R.id.btn_themmota5);

        li1 = findViewById(R.id.li_themanh);
        li2 = findViewById(R.id.li_themanh1);
        li3 = findViewById(R.id.li_themanh2);
        li4 = findViewById(R.id.li_themanh3);
        li5 = findViewById(R.id.li_themanh4);
    }

    public void eventClick() {
        btnChonAnh.setOnClickListener(this);
        btnThemSP.setOnClickListener(this);
        btnHuyThem.setOnClickListener(this);
        btnChon1.setOnClickListener(this);
        btnTiepTuc1.setOnClickListener(this);
        btnXacNhan1.setOnClickListener(this);
        btnChon2.setOnClickListener(this);
        btnTiepTuc2.setOnClickListener(this);
        btnXacNhan2.setOnClickListener(this);
        btnChon3.setOnClickListener(this);
        btnTiepTuc3.setOnClickListener(this);
        btnXacNhan3.setOnClickListener(this);
        btnChon4.setOnClickListener(this);
        btnTiepTuc4.setOnClickListener(this);
        btnXacNhan4.setOnClickListener(this);
        btnChon5.setOnClickListener(this);
        btnTiepTuc5.setOnClickListener(this);
        btnXacNhan5.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_chonAnh: {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_PICK);
                break;
            }
            case R.id.btn_themSPXacNhan: {
                themMoiSP();

//                for(int i=0;i<GioHang.arrSanPhamThem.size();i++){
//                    preThemMoiSanPham.themMoiAnhSPChiTiet(GioHang.arrSanPhamThem.get(i));
//
//                }
                break;
            }
            case R.id.btn_huyThemXacNhan: {
                finish();
                break;
            }
            case R.id.btn_chonmota1: {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_PICK1);
                btnXacNhan1.setEnabled(true);
                btnTiepTuc1.setEnabled(true);
                break;
            }
            case R.id.btn_themmota1: {
                GioHang.arrSanPhamThem.add(realPath1);
                li2.setVisibility(View.VISIBLE);
                btnTiepTuc1.setVisibility(View.GONE);
                btnXacNhan1.setVisibility(View.GONE);
                break;
            }
            case R.id.btn_xacNhan1: {
                break;
            }
            case R.id.btn_chonmota2: {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_PICK2);
                btnTiepTuc2.setEnabled(true);
                btnXacNhan2.setEnabled(true);
                break;
            }
            case R.id.btn_themmota2: {
                GioHang.arrSanPhamThem.add(realPath2);
                li3.setVisibility(View.VISIBLE);
                btnTiepTuc2.setVisibility(View.GONE);
                btnXacNhan2.setVisibility(View.GONE);
                break;
            }
            case R.id.btn_xacNhan2: {
                break;
            }

            case R.id.btn_chonmota3: {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_PICK3);
                btnTiepTuc3.setEnabled(true);
                btnXacNhan3.setEnabled(true);
                break;
            }
            case R.id.btn_themmota3: {
                GioHang.arrSanPhamThem.add(realPath3);
                li4.setVisibility(View.VISIBLE);
                btnTiepTuc3.setVisibility(View.GONE);
                btnXacNhan3.setVisibility(View.GONE);
                break;
            }
            case R.id.btn_xacNhan3: {
                break;
            }

            case R.id.btn_chonmota4: {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_PICK4);
                btnTiepTuc4.setEnabled(true);
                btnXacNhan4.setEnabled(true);
                break;
            }
            case R.id.btn_themmota4: {
                GioHang.arrSanPhamThem.add(realPath4);
                li5.setVisibility(View.VISIBLE);
                btnTiepTuc4.setVisibility(View.GONE);
                btnXacNhan4.setVisibility(View.GONE);
                break;
            }
            case R.id.btn_xacNhan4: {
                break;
            }

            case R.id.btn_chonmota5: {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_PICK5);
                btnTiepTuc5.setEnabled(true);
                btnXacNhan5.setEnabled(true);
                break;
            }
            case R.id.btn_themmota5: {
                break;
            }
            case R.id.btn_xacNhan5: {
                GioHang.arrSanPhamThem.add(realPath5);
                btnXacNhan5.setVisibility(View.GONE);
//                Log.d("SPThem",GioHang.arrSanPhamThem.size()+"");

                break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK && data != null) {
            switch (requestCode) {
                case REQUEST_PICK: {
                    Uri uri = data.getData();
                    realPath = getRealPathFromURI(uri);
//                    Log.d("path111", realPath);
                    try {
                        InputStream inputStream = getContentResolver().openInputStream(uri);
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        imgAnhChinh.setImageBitmap(bitmap);
                        chonAnh = true;
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case REQUEST_PICK1: {
                    Uri uri = data.getData();
                    realPath1 = getRealPathFromURI(uri);
//                    Log.d("path111", realPath1);
                    try {
                        InputStream inputStream = getContentResolver().openInputStream(uri);
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        imgAnh1.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case REQUEST_PICK2: {
                    Uri uri = data.getData();
                    realPath2 = getRealPathFromURI(uri);
//                    Log.d("path111", realPath2);
                    try {
                        InputStream inputStream = getContentResolver().openInputStream(uri);
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        imgAnh2.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case REQUEST_PICK3: {
                    Uri uri = data.getData();
                    realPath3 = getRealPathFromURI(uri);
//                    Log.d("path111", realPath3);
                    try {
                        InputStream inputStream = getContentResolver().openInputStream(uri);
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        imgAnh3.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case REQUEST_PICK4: {
                    Uri uri = data.getData();
                    realPath4 = getRealPathFromURI(uri);
//                    Log.d("path111", realPath4);
                    try {
                        InputStream inputStream = getContentResolver().openInputStream(uri);
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        imgAnh4.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case REQUEST_PICK5: {
                    Uri uri = data.getData();
                    realPath5 = getRealPathFromURI(uri);
//                    Log.d("path111", realPath5);
                    try {
                        InputStream inputStream = getContentResolver().openInputStream(uri);
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        imgAnh5.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void themMoiSP() {
        if (chonAnh == false || edtTenSP.getText().toString().trim().equals("") || edtGiaSP.getText().toString().trim().equals("") || edtSoLuong39.getText().toString().trim().equals("")
                || edtSoLuong40.getText().toString().trim().equals("") || edtSoLuong41.getText().toString().trim().equals("") || edtSoLuong42.getText().toString().trim().equals("") || edtSoLuong43.getText().toString().trim().equals("")
                || edtNoiDung.equals("")) {
            Toast.makeText(MainQLThemSanPham.this, "Kiểm tra lại dữ liệu", Toast.LENGTH_SHORT).show();
            return;
        }

        int permission = ActivityCompat.checkSelfPermission(MainQLThemSanPham.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    MainQLThemSanPham.this,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
            return;
        }
        preThemMoiSanPham = new PreThemMoiSanPham(this);

        preThemMoiSanPham.themMoiSP(realPath);


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
    public void onSuccessed(final String path) {
        preThemMoiSanPham = new PreThemMoiSanPham(this);
        SharedPreferences sharedPreferences = getSharedPreferences("QuyenTK", Context.MODE_PRIVATE);

        final int id = Integer.parseInt(sharedPreferences.getString("idNhanVien", ""));

        new Thread(new Runnable() {
            @Override
            public void run() {
                preThemMoiSanPham.themMoiSanPham2(id, edtTenSP.getText().toString(), edtNoiDung.getText().toString(),
                        Integer.parseInt(edtGiaSP.getText().toString()), path);
            }
        }).start();
    }

    @Override
    public void onFailed() {
        edtNoiDung.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainQLThemSanPham.this, "That bai", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onSuc1(final int maSP) {

        preThemMoiSanPham = new PreThemMoiSanPham(this);

        edtNoiDung.post(new Runnable() {
            @Override
            public void run() {
                Log.d("MaSPThem",maSP+"");
            }
        });
            new Thread(new Runnable() {
                @Override
                public void run() {
                    preThemMoiSanPham.themSoLuongSize(maSP,Integer.parseInt(edtSoLuong39.getText().toString().trim()),
                            Integer.parseInt(edtSoLuong40.getText().toString().trim()),Integer.parseInt(edtSoLuong41.getText().toString().trim()),
                            Integer.parseInt(edtSoLuong42.getText().toString().trim()),Integer.parseInt(edtSoLuong43.getText().toString().trim()));

                    for (int i = 0; i < GioHang.arrSanPhamThem.size(); i++) {
                        preThemMoiSanPham.themMoiAnhSPChiTiet(GioHang.arrSanPhamThem.get(i), maSP);
                    }
                }
            }).start();


    }

    @Override
    public void themThanhCong() {
//        new
    }

    @Override
    public void themSizeThanhCong() {
        edtNoiDung.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainQLThemSanPham.this,"Thêm mới sản phẩm thành công",Toast.LENGTH_SHORT).show();
            }
        });
    }
}