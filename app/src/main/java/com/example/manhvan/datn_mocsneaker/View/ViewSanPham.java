package com.example.manhvan.datn_mocsneaker.View;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.manhvan.datn_mocsneaker.R;
import com.example.manhvan.datn_mocsneaker.util.AndroidDeviceHelper;

public class ViewSanPham extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_san_pham);
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
    }
}
