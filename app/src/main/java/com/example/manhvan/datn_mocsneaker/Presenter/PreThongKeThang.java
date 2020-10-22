package com.example.manhvan.datn_mocsneaker.Presenter;

import com.example.manhvan.datn_mocsneaker.Model.MoThongKeThang;

public class PreThongKeThang implements MoThongKeThang.TongTienInterface {
    private KetQuaTinhTienInterface ketQuaTinhTienInterface;

    public PreThongKeThang(KetQuaTinhTienInterface ketQuaTinhTienInterface) {
        this.ketQuaTinhTienInterface = ketQuaTinhTienInterface;
    }

    private MoThongKeThang moThongKeThang=new MoThongKeThang(this);
    public void thongKeThang(String thang,String nam){
        moThongKeThang.xuLyTongBan(thang,nam);
    }

    public void LaySanPhamBanNhieu(String thang,String nam){
        moThongKeThang.xuLyBanNhieu(thang,nam);
    }

    public interface KetQuaTinhTienInterface{
        public void tienBan(String tienBan);
        public void tienNhap(String tienBan);
        public void thatbai();
        public void dsSanPhamBanNhieu();
        public void dsSanPhamBanIt();
    }

    @Override
    public void layTienBanThanhCong(String tienBan) {
        ketQuaTinhTienInterface.tienBan(tienBan);
    }

    @Override
    public void layTienNhapThanhCong(String tienBan) {
        ketQuaTinhTienInterface.tienNhap(tienBan);
    }

    @Override
    public void layDSBanNhieu() {
        ketQuaTinhTienInterface.dsSanPhamBanNhieu();
    }

    @Override
    public void layDSBanIt() {
        ketQuaTinhTienInterface.dsSanPhamBanIt();
    }

    @Override
    public void thatBai() {
        ketQuaTinhTienInterface.thatbai();
    }
}
