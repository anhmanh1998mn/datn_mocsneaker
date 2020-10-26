package com.example.manhvan.datn_mocsneaker.Presenter;

import com.example.manhvan.datn_mocsneaker.Model.MoTimKiemNV;
import com.example.manhvan.datn_mocsneaker.Model.TimKiemNVKQ1;
import com.example.manhvan.datn_mocsneaker.View.PKInterface.TimKiemNVKQ2;

public class PreTimKiemNhanVien implements TimKiemNVKQ1{
    private TimKiemNVKQ2 timKiemNVKQ2;

    public PreTimKiemNhanVien(TimKiemNVKQ2 timKiemNVKQ2) {
        this.timKiemNVKQ2 = timKiemNVKQ2;
    }

    private MoTimKiemNV moTimKiemNV=new MoTimKiemNV(this);
    public void TimKiemNhanVien(String tenNV){
        if(tenNV.equals("")){
            timKiemNVKQ2.onFialed2();
        }else {
            moTimKiemNV.XuLy(tenNV);
        }
    }

    @Override
    public void onS() {
        timKiemNVKQ2.onSuccessed1();
    }

    @Override
    public void onF() {
        timKiemNVKQ2.onFailed1();
    }
}
