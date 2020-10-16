package com.example.manhvan.datn_mocsneaker.Presenter;

import com.example.manhvan.datn_mocsneaker.Model.DuyetDonNhapInterface;
import com.example.manhvan.datn_mocsneaker.Model.MoDuyetDonNhapHang;
import com.example.manhvan.datn_mocsneaker.View.LayCTDonNhapInterface;

public class PreDuyetDonNhapHang implements DuyetDonNhapInterface {
    private LayCTDonNhapInterface layCTDonNhapInterface;

    public PreDuyetDonNhapHang(LayCTDonNhapInterface layCTDonNhapInterface) {
        this.layCTDonNhapInterface = layCTDonNhapInterface;
    }

    private MoDuyetDonNhapHang moDuyetDonNhapHang=new MoDuyetDonNhapHang(this);
    public void duyetDonNhap(int maDonNhap,int trangThai){
        moDuyetDonNhapHang.xuLy(maDonNhap,trangThai);
    }

    @Override
    public void onS() {
        layCTDonNhapInterface.duyetThanhCong();
    }

    @Override
    public void onF() {
        layCTDonNhapInterface.onFailed();
    }
}
