package com.example.manhvan.datn_mocsneaker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.manhvan.datn_mocsneaker.Presenter.PreTimKiemSanPham;
import com.example.manhvan.datn_mocsneaker.View.QuanLySanPham.MainTimKiemTenSanPham;
import com.example.manhvan.datn_mocsneaker.View.QuanLyDonHang.ViewGioHang;
import com.example.manhvan.datn_mocsneaker.adapter.TimKiemSanPhamAdapter;
import com.example.manhvan.datn_mocsneaker.ui.dashboard.DashboardFragment;
import com.example.manhvan.datn_mocsneaker.util.GioHang;

public class MainActivity2 extends AppCompatActivity{
    private ActionBar actionBar;
    private LinearLayout frameLayout;
    DashboardFragment dashboardFragment;
    private BottomNavigationView navView;
    private Button btnbackSearch, btnCloseSearch;
    private AppCompatAutoCompleteTextView autoCompleteTextView;
    private PreTimKiemSanPham preTimKiemSanPham;
    private TimKiemSanPhamAdapter timKiemSanPhamAdapter;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        navView = findViewById(R.id.nav_view);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);

        //hide child botton navigationview


        quyenTaiKhoan();


        //changeTextSearch();
    }

    private void quyenTaiKhoan() {

        SharedPreferences sharedPreferences = getSharedPreferences("QuyenTK", Context.MODE_PRIVATE);
        if (sharedPreferences.getString("quyen", "").equals("2") || sharedPreferences.getString("quyen", "").equals("1")) {
            navView.getMenu().getItem(1).setVisible(true);
            return;
        }
        navView.getMenu().getItem(1).setVisible(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menucart, menu);
        FrameLayout badgeLayout = (FrameLayout)    menu.findItem(R.id.mnucart).getActionView();
        tv = badgeLayout.findViewById(R.id.txtcount);
        tv.setText(GioHang.arrGioHang.size()+"");
        badgeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this, ViewGioHang.class));
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onRestart() {
        tv.setText(GioHang.arrGioHang.size()+"");
        super.onRestart();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.mnucart: {
//                startActivity(new Intent(MainActivity2.this, ViewGioHang.class));
//                break;
//            }
            case R.id.mnusearch: {
                startActivity(new Intent(MainActivity2.this, MainTimKiemTenSanPham.class));
                break;
//                actionBar=getSupportActionBar();
//                actionBar.hide();
            }
        }
        return super.onOptionsItemSelected(item);
    }

}