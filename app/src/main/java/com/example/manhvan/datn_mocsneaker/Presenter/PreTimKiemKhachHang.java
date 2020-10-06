package com.example.manhvan.datn_mocsneaker.Presenter;

import com.example.manhvan.datn_mocsneaker.Model.MoTimKiemKhachHang;
import com.example.manhvan.datn_mocsneaker.Model.TimKiemNVKQ1;
import com.example.manhvan.datn_mocsneaker.View.QuanLyKhachHangInterface;

public class PreTimKiemKhachHang implements TimKiemNVKQ1 {
    private QuanLyKhachHangInterface quanLyKhachHangInterface;

    public PreTimKiemKhachHang(QuanLyKhachHangInterface quanLyKhachHangInterface) {
        this.quanLyKhachHangInterface = quanLyKhachHangInterface;
    }

    private MoTimKiemKhachHang moQuanLyKhachHang=new MoTimKiemKhachHang(this);
    public void timKiemKhachHang(String ten){
        moQuanLyKhachHang.xuLyTimKiem(ten);
    }

    @Override
    public void onS() {
        quanLyKhachHangInterface.danhSachTimKiem();
    }

    @Override
    public void onF() {
        quanLyKhachHangInterface.thatBai();
    }
}
