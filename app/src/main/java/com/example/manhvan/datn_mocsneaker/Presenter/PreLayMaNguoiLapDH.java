package com.example.manhvan.datn_mocsneaker.Presenter;

import com.example.manhvan.datn_mocsneaker.Model.MoLayMaNguoiLapDH;
import com.example.manhvan.datn_mocsneaker.Model.TimKiemNVKQ1;
import com.example.manhvan.datn_mocsneaker.View.MaNguoiLapDHInterface;

public class PreLayMaNguoiLapDH implements TimKiemNVKQ1{
    private MaNguoiLapDHInterface maNguoiLapDHInterface;

    public PreLayMaNguoiLapDH(MaNguoiLapDHInterface maNguoiLapDHInterface) {
        this.maNguoiLapDHInterface = maNguoiLapDHInterface;
    }

    private MoLayMaNguoiLapDH moLayMaNguoiLapDH=new MoLayMaNguoiLapDH(this);
    public void layMaNguoiLap(String phone,int role){
        moLayMaNguoiLapDH.xuLy(phone,role);
    }

    @Override
    public void onS() {
        maNguoiLapDHInterface.onSuccedID();
    }

    @Override
    public void onF() {
        maNguoiLapDHInterface.onFailedID();
    }
}
