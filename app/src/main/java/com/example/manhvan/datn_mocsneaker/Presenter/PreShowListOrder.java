package com.example.manhvan.datn_mocsneaker.Presenter;

import com.example.manhvan.datn_mocsneaker.Model.MoShowListOrder;

public class PreShowListOrder implements MoShowListOrder.KetQuaInterface {
    private ShowListOrderInterface showListOrderInterface;

    public PreShowListOrder(ShowListOrderInterface showListOrderInterface) {
        this.showListOrderInterface = showListOrderInterface;
    }

    private MoShowListOrder moShowListOrder=new MoShowListOrder(this);
    public void showListOrder(int maND,int quyenND,int trangThai){
        moShowListOrder.xuLy(maND,quyenND,trangThai);
    }

    public void showlistOrder1(int maND,int trangThai){
        moShowListOrder.xuLy1(maND,trangThai);
    }


    @Override
    public void onS() {
        showListOrderInterface.onSuccessed();
    }

    @Override
    public void onF() {

    }

    @Override
    public void onS1() {
        showListOrderInterface.onSuccessed1();
    }
}
