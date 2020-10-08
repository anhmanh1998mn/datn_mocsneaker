package com.example.manhvan.datn_mocsneaker.Presenter;

import com.example.manhvan.datn_mocsneaker.Model.MoKichCoTheoSP;
import com.example.manhvan.datn_mocsneaker.Model.TimKiemNVKQ1;
import com.example.manhvan.datn_mocsneaker.View.ProductDetail;

public class PreKichCoTheoSP implements TimKiemNVKQ1{
    private ProductDetail productDetail;

    public PreKichCoTheoSP(ProductDetail productDetail) {
        this.productDetail = productDetail;
    }

    private MoKichCoTheoSP moKichCoTheoSP=new MoKichCoTheoSP(this);
    public void kichCoTheoSanPham(int idNhan){
        moKichCoTheoSP.xuLy(idNhan);
    }

    @Override
    public void onS() {
        productDetail.kichCoSuccess();
    }

    @Override
    public void onF() {
        productDetail.onFailed();
    }
}
