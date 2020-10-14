package com.example.manhvan.datn_mocsneaker.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.manhvan.datn_mocsneaker.ui.fragmentNhapHangDS.FragmentDaDuyet;
import com.example.manhvan.datn_mocsneaker.ui.fragmentNhapHangDS.FragmentDonChuaDuyet;
import com.example.manhvan.datn_mocsneaker.ui.fragmentNhapHangDS.FragmentKhongDuyet;

public class QLNhapHangAdapter extends FragmentStatePagerAdapter {
    public QLNhapHangAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment=null;
        switch (i){
            case 0:{
                fragment=new FragmentDonChuaDuyet();
                break;
            }
            case 1:{
                fragment=new FragmentKhongDuyet();
                break;
            }
            case 2:{
                fragment=new FragmentDaDuyet();
                break;
            }
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title="";
        switch (position){
            case 0:{
                title="Đơn chưa duyệt";break;

            }
            case 1:{
                title="Không duyệt";break;
            }
            case 2:{
                title="Đã duyệt";break;
            }
        }
        return title;
    }
}
