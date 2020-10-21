package com.example.manhvan.datn_mocsneaker.View;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.manhvan.datn_mocsneaker.R;
import com.example.manhvan.datn_mocsneaker.adapter.ThongKePagerAdapter;

public class MainThongKeBaoCao extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_thong_ke_bao_cao);
        actionBar=getSupportActionBar();
        actionBar.setTitle("Thống kê - Báo cáo");
        actionBar.setDisplayHomeAsUpEnabled(true);
        initViewpager();
    }

    private void initViewpager() {
        tabLayout=findViewById(R.id.tb_layoutTKBC);
        viewPager=findViewById(R.id.view_pagerTKBC);
        FragmentManager manager=getSupportFragmentManager();
        ThongKePagerAdapter pagerAdapter=new ThongKePagerAdapter(manager);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabsFromPagerAdapter(pagerAdapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}