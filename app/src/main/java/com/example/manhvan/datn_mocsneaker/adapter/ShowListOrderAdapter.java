package com.example.manhvan.datn_mocsneaker.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.manhvan.datn_mocsneaker.ui.fragmentKHShowList.FragmentChoXacNhan;
import com.example.manhvan.datn_mocsneaker.ui.fragmentKHShowList.FragmentDaGiao;
import com.example.manhvan.datn_mocsneaker.ui.fragmentKHShowList.FragmentDangGiao;
import com.example.manhvan.datn_mocsneaker.ui.fragmentKHShowList.FragmentXacNhan;

public class ShowListOrderAdapter extends FragmentStatePagerAdapter {
    public ShowListOrderAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment=null;
        switch (i){
            case 0:{
                fragment=new FragmentChoXacNhan();
                break;
            }
            case 1:{
                fragment=new FragmentXacNhan();
                break;
            }
            case 2:{
                fragment=new FragmentDangGiao();
                break;
            }
            case 3:{
                fragment=new FragmentDaGiao();
                break;
            }
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String tittle="";
        switch (position){
            case 0:{
                tittle="Chờ xác nhận";
                break;
            }
            case 1:{
                tittle="Xác nhận";
                break;
            }
            case 2:{
                tittle="Đang giao";
                break;
            }
            case 3:{
                tittle="Đã giao";
                break;
            }
        }
        return tittle;
    }
}
