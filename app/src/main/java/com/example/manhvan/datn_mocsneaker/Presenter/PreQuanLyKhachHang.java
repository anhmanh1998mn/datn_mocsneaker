package com.example.manhvan.datn_mocsneaker.Presenter;

import com.example.manhvan.datn_mocsneaker.Model.MoQuanLyKhachHang;
import com.example.manhvan.datn_mocsneaker.Model.TimKiemNVKQ1;
import com.example.manhvan.datn_mocsneaker.View.PKInterface.QuanLyKhachHangInterface;

public class PreQuanLyKhachHang implements TimKiemNVKQ1 {
    private QuanLyKhachHangInterface quanLyKhachHangInterface;

    public PreQuanLyKhachHang(QuanLyKhachHangInterface quanLyKhachHangInterface) {
        this.quanLyKhachHangInterface = quanLyKhachHangInterface;
    }

    private MoQuanLyKhachHang moQuanLyKhachHang=new MoQuanLyKhachHang(this);
    public void quanLyKhachHang(){
        moQuanLyKhachHang.xuLy();
    }

    @Override
    public void onS() {
        quanLyKhachHangInterface.danhSachKHThanhCong();
    }

    @Override
    public void onF() {
        quanLyKhachHangInterface.thatBai();
    }
}
