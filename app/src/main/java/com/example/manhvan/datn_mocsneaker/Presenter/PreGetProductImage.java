package com.example.manhvan.datn_mocsneaker.Presenter;

import com.example.manhvan.datn_mocsneaker.Model.MogetProductImage;
import com.example.manhvan.datn_mocsneaker.Model.TimKiemNVKQ1;
import com.example.manhvan.datn_mocsneaker.View.PKInterface.ProductDetail;

public class PreGetProductImage implements TimKiemNVKQ1 {
    private ProductDetail productDetail;

    public PreGetProductImage(ProductDetail productDetail) {
        this.productDetail = productDetail;
    }

    private MogetProductImage mogetProductImage=new MogetProductImage(this);
    public void GetProductImage(int id){
        mogetProductImage.XuLy(id);
    }

    @Override
    public void onS() {
        productDetail.onSuccess();
    }

    @Override
    public void onF() {
        productDetail.onFailed();
    }
}
