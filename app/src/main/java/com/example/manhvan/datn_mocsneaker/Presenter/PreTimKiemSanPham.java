package com.example.manhvan.datn_mocsneaker.Presenter;

import com.example.manhvan.datn_mocsneaker.Model.MoTimKiemSanPham;
import com.example.manhvan.datn_mocsneaker.Model.TimKiemNVKQ1;
import com.example.manhvan.datn_mocsneaker.View.PKInterface.TimKiemSanPhamInterface;

public class PreTimKiemSanPham implements TimKiemNVKQ1 {
    private TimKiemSanPhamInterface timKiemSanPhamInterface;

    public PreTimKiemSanPham(TimKiemSanPhamInterface timKiemSanPhamInterface) {
        this.timKiemSanPhamInterface = timKiemSanPhamInterface;
    }

    private MoTimKiemSanPham moTimKiemSanPham=new MoTimKiemSanPham(this);
    public void timKiemSanPham(String tenSP){
        moTimKiemSanPham.xuLy(tenSP);
    }

    @Override
    public void onS() {
        timKiemSanPhamInterface.onSuceessed();
    }

    @Override
    public void onF() {
        timKiemSanPhamInterface.onFailed();
    }
}
