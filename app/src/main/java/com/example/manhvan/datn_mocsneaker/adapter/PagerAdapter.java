package com.example.manhvan.datn_mocsneaker.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.manhvan.datn_mocsneaker.ui.fragmentsanpham.FragmentGiayDoi;
import com.example.manhvan.datn_mocsneaker.ui.fragmentsanpham.FragmentGiayNam;
import com.example.manhvan.datn_mocsneaker.ui.fragmentsanpham.FragmentGiayNu;
import com.example.manhvan.datn_mocsneaker.ui.fragmentsanpham.FragmentSanPhamMoi;
import com.example.manhvan.datn_mocsneaker.ui.fragmentsanpham.FragmentSanPhamNoiBat;
import com.example.manhvan.datn_mocsneaker.ui.fragmentsanpham.FragmentTatCa;

public class PagerAdapter extends FragmentStatePagerAdapter {
    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment=null;
        switch (i){
            case 0:{
                fragment=new FragmentTatCa();
                break;
            }
            case 1:{
                fragment=new FragmentSanPhamMoi();
                break;
            }
            case 2:{
                fragment=new FragmentSanPhamNoiBat();
                break;
            }
            case 3:{
                fragment=new FragmentGiayNam();
                break;
            }
            case 4:{
                fragment=new FragmentGiayNu();
                break;
            }
            case 5:{
                fragment=new FragmentGiayDoi();
                break;
            }
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String titile="";
        switch (position){
            case 0:{
                titile="Tất cả";break;
            }
            case 1:{
                titile="Sản phẩm mới";break;
            }
            case 2:{
                titile="Sản phẩm nổi bật";break;
            }
            case 3:{
                titile="Giày nam";break;
            }
            case 4:{
                titile="Giày nữ";break;
            }
            case 5:{
                titile="Giày đôi";break;
            }
        }
        return titile;
    }
}
