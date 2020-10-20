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

    public void huyDonHang(int maDonHang){
        moOrderDetail.xuLyHuyDonHang(maDonHang);
    }

    public void capNhatDonHang(int maDonHang,int trangThai){
        moOrderDetail.capNhatDH(maDonHang,trangThai);
    }

    @Override
    public void onS() {
        getDataOrInterface.onSuccessed();
    }

    @Override
    public void onF() {
        getDataOrInterface.onFailed();
    }

    @Override
    public void huyThanhCong() {
        getDataOrInterface.onHuyThanhCong();
    }

    @Override
    public void capNhatDHThanhCong() {
        getDataOrInterface.onCapNhatDH();
    }

    public interface GetDataOrInterface{
        public void onSuccessed();
        public void onFailed();
        public void onHuyThanhCong();
        public void onCapNhatDH();
    }
}
