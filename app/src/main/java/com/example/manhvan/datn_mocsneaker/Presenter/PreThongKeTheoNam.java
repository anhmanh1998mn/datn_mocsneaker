package com.example.manhvan.datn_mocsneaker.Presenter;

import com.example.manhvan.datn_mocsneaker.Model.MoThongKeTheoNam;

public class PreThongKeTheoNam implements MoThongKeTheoNam.ThongKeNamInterface {
    private ThongKeKetQuaInterface thongKeKetQuaInterface;

    public PreThongKeTheoNam(ThongKeKetQuaInterface thongKeKetQuaInterface) {
        this.thongKeKetQuaInterface = thongKeKetQuaInterface;
    }

    private MoThongKeTheoNam moThongKeTheoNam=new MoThongKeTheoNam(this);
    public void thongKeNam(String nam){
        moThongKeTheoNam.xuLyBan(nam);
    }

    @Override
    public void thongKeBan() {
        thongKeKetQuaInterface.thongKeBanThanhCong();
    }

    @Override
    public void thongKeNhap() {
        thongKeKetQuaInterface.thongKeNhapThanhCong();
    }

    public interface ThongKeKetQuaInterface{
        public void thongKeBanThanhCong();
        public void thongKeNhapThanhCong();
    }
}
