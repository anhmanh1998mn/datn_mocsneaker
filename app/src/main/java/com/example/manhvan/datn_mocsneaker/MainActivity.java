package com.example.manhvan.datn_mocsneaker;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.manhvan.datn_mocsneaker.View.ViewQuanLyNhanVIen;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private DrawerLayout mDrawerLayout;
    private Toolbar toolbar;
    private Button btnClose;
    private TextView txtQLNV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.tb);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.dmuc);

        initView();
        ClickMenu();

    }

    private void ClickMenu() {
        btnClose.setOnClickListener(this);
        txtQLNV.setOnClickListener(this);
    }


    private void initView() {
        mDrawerLayout = findViewById(R.id.dr);
//      navigationView = findViewById(R.id.nav_view);
        btnClose=findViewById(R.id.btn_close);
        txtQLNV=findViewById(R.id.mnu_qlnv);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                mDrawerLayout.openDrawer(GravityCompat.START);break;
            }
            case R.id.mnucart:{
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_close:{
                mDrawerLayout.closeDrawers();
                break;
            }
            case R.id.mnu_qlnv:{
                mDrawerLayout.closeDrawers();
                startActivity(new Intent(MainActivity.this, ViewQuanLyNhanVIen.class));
                break;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menucart,menu);
        return super.onCreateOptionsMenu(menu);
    }

}
