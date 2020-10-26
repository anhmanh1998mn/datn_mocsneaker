package com.example.manhvan.datn_mocsneaker.Presenter;

import com.example.manhvan.datn_mocsneaker.Model.MoThongTinTaiKhon;
import com.example.manhvan.datn_mocsneaker.Model.ThongTinKhachHangInterface;
import com.example.manhvan.datn_mocsneaker.View.PKInterface.ThongTinKHInterKQ2;
import com.example.manhvan.datn_mocsneaker.entity.ThongTinKhachHang;
import com.example.manhvan.datn_mocsneaker.entity.ThongTinNV;

import java.util.ArrayList;

public class PreThongTinTaiKhoan implements ThongTinKhachHangInterface {
    private ThongTinKHInterKQ2 thongTinKHInterKQ2;

    public PreThongTinTaiKhoan(ThongTinKHInterKQ2 thongTinKHInterKQ2) {
        this.thongTinKHInterKQ2 = thongTinKHInterKQ2;
    }

    private MoThongTinTaiKhon moThongTinTaiKhon=new MoThongTinTaiKhon(this);
    public void thongTinTaiKhoan(String role,String phone){
        moThongTinTaiKhon.xuLy(role,phone);
    }

    @Override
    public void thongTinNhanVien(ArrayList<ThongTinNV> arrayList) {
        thongTinKHInterKQ2.thongTinNhanVien(arrayList);
    }

    @Override
    public void thongTinKhachHang(ArrayList<ThongTinKhachHang> arrayList) {
        thongTinKHInterKQ2.thongTinKhachHang(arrayList);
    }

    @Override
    public void loi() {
        thongTinKHInterKQ2.loi();
    }
}
