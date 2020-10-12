package com.example.manhvan.datn_mocsneaker.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.manhvan.datn_mocsneaker.R;

public class ViewSanPham extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PagerAdapter adapter;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_san_pham);
        actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Sản phẩm");
        initView();
    }

    private void initView() {
        tabLayout=findViewById(R.id.tb_layout);
        viewPager=findViewById(R.id.view_pager);
        FragmentManager fragmentManager=getSupportFragmentManager();
        adapter=new com.example.manhvan.datn_mocsneaker.adapter.PagerAdapter(fragmentManager);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setTabsFromPagerAdapter(adapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

        //tab bắt đầu khi mở activity
        Intent intent=getIntent();
        switch (intent.getIntExtra("idLoaiSanPham",-1)){
            case 1:{
                viewPager.setCurrentItem(1);break;
            }
            case 2:{
                viewPager.setCurrentItem(2);break;
            }
            case 3:{
                viewPager.setCurrentItem(3);break;
            }
            case 4:{
                viewPager.setCurrentItem(4);break;
            }
            case 5:{
                viewPager.setCurrentItem(5);break;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menucart,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                finish();break;
            }
            case R.id.mnucart:{
                startActivity(new Intent(this,ViewGioHang.class));
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
