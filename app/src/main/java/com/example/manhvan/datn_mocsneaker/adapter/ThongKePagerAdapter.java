package com.example.manhvan.datn_mocsneaker.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.manhvan.datn_mocsneaker.ui.fragmentThongKe.FragmentThongKeNam;
import com.example.manhvan.datn_mocsneaker.ui.fragmentThongKe.FragmentThongKeThang;

public class ThongKePagerAdapter extends FragmentStatePagerAdapter {
    public ThongKePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment=null;
        switch (i){
            case 0:{
                fragment=new FragmentThongKeThang();
                break;
            }
            case 1:{
                fragment=new FragmentThongKeNam();
                break;
            }
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String tittle="";
        switch (position){
            case 0:{
                tittle="Tháng";
                break;
            }
            case 1:{
                tittle="Năm";break;
            }
        }
        return tittle;
    }

}
