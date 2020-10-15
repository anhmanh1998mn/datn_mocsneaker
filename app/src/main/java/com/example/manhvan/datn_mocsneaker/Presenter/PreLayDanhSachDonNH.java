package com.example.manhvan.datn_mocsneaker.Presenter;

import com.example.manhvan.datn_mocsneaker.Model.MoLayDanhSachDonNH;
import com.example.manhvan.datn_mocsneaker.Model.TimKiemNVKQ1;
import com.example.manhvan.datn_mocsneaker.View.LayDanhSachDonNhapInter;

public class PreLayDanhSachDonNH implements TimKiemNVKQ1 {
    private LayDanhSachDonNhapInter layDanhSachDonNhapInter;

    public PreLayDanhSachDonNH(LayDanhSachDonNhapInter layDanhSachDonNhapInter) {
        this.layDanhSachDonNhapInter = layDanhSachDonNhapInter;
    }

    private MoLayDanhSachDonNH moLayDanhSachDonNH=new MoLayDanhSachDonNH(this);
    public void layDanhSachDOnNhap(int status){
        moLayDanhSachDonNH.xuLy(status);
    }

    @Override
    public void onS() {
        layDanhSachDonNhapInter.danhSachChuaDuyet();
    }

    @Override
    public void onF() {
        layDanhSachDonNhapInter.onFailed();
    }
}
