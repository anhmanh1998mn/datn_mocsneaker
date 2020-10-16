package com.example.manhvan.datn_mocsneaker.Presenter;

import com.example.manhvan.datn_mocsneaker.Model.MoLayChiTietDonNhap;
import com.example.manhvan.datn_mocsneaker.Model.TimKiemNVKQ1;
import com.example.manhvan.datn_mocsneaker.View.LayCTDonNhapInterface;

public class PreLayChiTietDonNhap implements TimKiemNVKQ1 {
    private LayCTDonNhapInterface layCTDonNhapInterface;

    public PreLayChiTietDonNhap(LayCTDonNhapInterface layCTDonNhapInterface) {
        this.layCTDonNhapInterface = layCTDonNhapInterface;
    }

    private MoLayChiTietDonNhap moLayChiTietDonNhap=new MoLayChiTietDonNhap(this);
    public void layChiTietDonNhap(int maDonNhap){
        moLayChiTietDonNhap.xuLy(maDonNhap);
    }

    @Override
    public void onS() {
        layCTDonNhapInterface.onSuccessed();
    }

    @Override
    public void onF() {
        layCTDonNhapInterface.onFailed();
    }
}
