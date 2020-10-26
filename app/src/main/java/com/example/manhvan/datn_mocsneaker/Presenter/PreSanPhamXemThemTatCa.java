package com.example.manhvan.datn_mocsneaker.Presenter;

import com.example.manhvan.datn_mocsneaker.Model.MoSanPhamXemThemTatCa;
import com.example.manhvan.datn_mocsneaker.Model.TimKiemNVKQ1;
import com.example.manhvan.datn_mocsneaker.View.PKInterface.SanPhamXemThemInterface;

public class PreSanPhamXemThemTatCa implements TimKiemNVKQ1{
    private SanPhamXemThemInterface sanPhamXemThemInterface;

    public PreSanPhamXemThemTatCa(SanPhamXemThemInterface sanPhamXemThemInterface) {
        this.sanPhamXemThemInterface = sanPhamXemThemInterface;
    }

    private MoSanPhamXemThemTatCa moSanPhamXemThemTatCa=new MoSanPhamXemThemTatCa(this);
    public void sanPhamXemThemTatCa(int loaiSp){
        moSanPhamXemThemTatCa.xuLy(loaiSp);
    }

    @Override
    public void onS() {
        sanPhamXemThemInterface.onSuccessed();
    }

    @Override
    public void onF() {
        sanPhamXemThemInterface.onFailed();
    }
}
