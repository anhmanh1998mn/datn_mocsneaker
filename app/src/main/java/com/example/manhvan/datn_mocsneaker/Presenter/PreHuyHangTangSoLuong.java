package com.example.manhvan.datn_mocsneaker.Presenter;

import com.example.manhvan.datn_mocsneaker.Model.MohuyHangTangSoLuong;

public class PreHuyHangTangSoLuong implements MohuyHangTangSoLuong.HuyTagSLInter {
    private HuyTangSLInterface huyTangSLInterface;

    public PreHuyHangTangSoLuong(HuyTangSLInterface huyTangSLInterface) {
        this.huyTangSLInterface = huyTangSLInterface;
    }

    private MohuyHangTangSoLuong mohuyHangTangSoLuong=new MohuyHangTangSoLuong(this);
    public void HuyHangTangSoLuong(){
        mohuyHangTangSoLuong.xuLy();
    }

    @Override
    public void onS() {
        huyTangSLInterface.HuyTangSlThanhCong();
    }
    public interface HuyTangSLInterface{
        public void HuyTangSlThanhCong();
    }
}
