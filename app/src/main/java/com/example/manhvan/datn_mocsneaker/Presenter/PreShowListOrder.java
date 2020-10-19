package com.example.manhvan.datn_mocsneaker.Presenter;

import com.example.manhvan.datn_mocsneaker.Model.MoShowListOrder;
import com.example.manhvan.datn_mocsneaker.Model.TimKiemNVKQ1;

public class PreShowListOrder implements TimKiemNVKQ1 {
    private ShowListOrderInterface showListOrderInterface;

    public PreShowListOrder(ShowListOrderInterface showListOrderInterface) {
        this.showListOrderInterface = showListOrderInterface;
    }

    private MoShowListOrder moShowListOrder=new MoShowListOrder(this);
    public void showListOrder(int maND,int quyenND,int trangThai){
        moShowListOrder.xuLy(maND,quyenND,trangThai);
    }

    @Override
    public void onS() {
        showListOrderInterface.onSuccessed();
    }

    @Override
    public void onF() {

    }
}
