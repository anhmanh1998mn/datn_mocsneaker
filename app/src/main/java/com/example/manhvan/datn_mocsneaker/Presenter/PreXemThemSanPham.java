package com.example.manhvan.datn_mocsneaker.Presenter;

import com.example.manhvan.datn_mocsneaker.Model.MoXemThemSanPham;
import com.example.manhvan.datn_mocsneaker.Model.TimKiemNVKQ1;
import com.example.manhvan.datn_mocsneaker.View.SanPhamXemThemInterface;

public class PreXemThemSanPham implements TimKiemNVKQ1{
    private SanPhamXemThemInterface sanPhamXemThemInterface;

    public PreXemThemSanPham(SanPhamXemThemInterface sanPhamXemThemInterface) {
        this.sanPhamXemThemInterface = sanPhamXemThemInterface;
    }

    private MoXemThemSanPham moXemThemSanPham=new MoXemThemSanPham(this);
    public void xemThemSanPham(int sanPham){
        moXemThemSanPham.xuLy(sanPham);
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
