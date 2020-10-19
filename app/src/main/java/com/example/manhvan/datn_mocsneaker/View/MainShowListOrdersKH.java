package com.example.manhvan.datn_mocsneaker.View;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.manhvan.datn_mocsneaker.R;
import com.example.manhvan.datn_mocsneaker.adapter.ShowListOrderAdapter;

public class MainShowListOrdersKH extends AppCompatActivity {
    private TabLayout tabLayoutShowList;
    private ViewPager viewPagerList;
    private ShowListOrderAdapter adapter;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_show_list_orders_k_h);
        actionBar=getSupportActionBar();
        actionBar.setTitle("Đơn mua");
        actionBar.setDisplayHomeAsUpEnabled(true);
        initView();
        initViewpager();
    }

    private void initView() {
        tabLayoutShowList=findViewById(R.id.tbLayout_showList);
        viewPagerList=findViewById(R.id.viewpager_showlist);
    }
    public void initViewpager(){
        FragmentManager fragmentManager=getSupportFragmentManager();
        tabLayoutShowList.setupWithViewPager(viewPagerList);
        adapter=new ShowListOrderAdapter(fragmentManager);
        viewPagerList.setAdapter(adapter);
        tabLayoutShowList.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPagerList));
        tabLayoutShowList.setTabsFromPagerAdapter(adapter);
        viewPagerList.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutShowList));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}