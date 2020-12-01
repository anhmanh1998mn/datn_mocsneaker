package com.example.manhvan.datn_mocsneaker.Presenter;

import com.example.manhvan.datn_mocsneaker.Model.MoTimKiemTheoKhoangGia;

public class PreTimKiemTheoKhoangGia implements MoTimKiemTheoKhoangGia.TimKiemGiaInter{
    private TimKiemGiaSuccessInter timKiemGiaSuccessInter;

    public PreTimKiemTheoKhoangGia(TimKiemGiaSuccessInter timKiemGiaSuccessInter) {
        this.timKiemGiaSuccessInter = timKiemGiaSuccessInter;
    }

    private MoTimKiemTheoKhoangGia moTimKiemTheoKhoangGia=new MoTimKiemTheoKhoangGia(this);
    public void timKiemTHeoGia(int gia1,int gia2){
        moTimKiemTheoKhoangGia.xuLy(gia1,gia2);
    }

    @Override
    public void onS() {
        timKiemGiaSuccessInter.onSuccessed();
    }
    public interface TimKiemGiaSuccessInter{
        public void onSuccessed();
    }
}
