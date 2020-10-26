package com.example.manhvan.datn_mocsneaker.Presenter;

import com.example.manhvan.datn_mocsneaker.Model.MoLayMaNguoiLapDH;
import com.example.manhvan.datn_mocsneaker.Model.SuaNhanVienKQ1;
import com.example.manhvan.datn_mocsneaker.View.PKInterface.MaNguoiLapDHInterface;

public class PreLayMaNguoiLapDH implements SuaNhanVienKQ1{
    private MaNguoiLapDHInterface maNguoiLapDHInterface;

    public PreLayMaNguoiLapDH(MaNguoiLapDHInterface maNguoiLapDHInterface) {
        this.maNguoiLapDHInterface = maNguoiLapDHInterface;
    }

    private MoLayMaNguoiLapDH moLayMaNguoiLapDH=new MoLayMaNguoiLapDH(this);
    public void layMaNguoiLap(String phone,int role){
        moLayMaNguoiLapDH.xuLy(phone,role);
    }

    public void themDonHang(String diaChi,int maNguoiLap,int quyen){
        moLayMaNguoiLapDH.xuLyThem(diaChi,maNguoiLap,quyen);
    }


    @Override
    public void onS1() {
        maNguoiLapDHInterface.onSuccedID();
    }

    @Override
    public void onS2() {
        maNguoiLapDHInterface.onThemThanhCong();
    }

    @Override
    public void onF() {

    }
}
