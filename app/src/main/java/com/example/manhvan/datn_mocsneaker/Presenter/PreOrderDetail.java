package com.example.manhvan.datn_mocsneaker.Presenter;

import com.example.manhvan.datn_mocsneaker.Model.MoOrderDetail;

public class PreOrderDetail implements MoOrderDetail.DataOrderDetail{
    private GetDataOrInterface getDataOrInterface;

    public PreOrderDetail(GetDataOrInterface getDataOrInterface) {
        this.getDataOrInterface = getDataOrInterface;
    }

    private MoOrderDetail moOrderDetail=new MoOrderDetail(this);
    public void orderDetail(int maDonHang){
        moOrderDetail.xuLy(maDonHang);
    }

    @Override
    public void onS() {
        getDataOrInterface.onSuccessed();
    }

    @Override
    public void onF() {
        getDataOrInterface.onFailed();
    }

    public interface GetDataOrInterface{
        public void onSuccessed();
        public void onFailed();
    }
}
