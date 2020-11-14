package com.example.manhvan.datn_mocsneaker.Presenter;

import com.example.manhvan.datn_mocsneaker.Model.MoAnSanPham;

public class PreAnSanPham implements MoAnSanPham.AnSPInter1{
    private AnSanPhamInterface anSanPhamInterface;

    public PreAnSanPham(AnSanPhamInterface anSanPhamInterface) {
        this.anSanPhamInterface = anSanPhamInterface;
    }

    private MoAnSanPham moAnSanPham=new MoAnSanPham(this);
    public void anSanPham(int idSP){
        moAnSanPham.xuLy(idSP);
    }

    @Override
    public void onS() {
        anSanPhamInterface.anSPThanhCong();
    }
    public interface AnSanPhamInterface{
        public void anSPThanhCong();
    }
}
