package com.example.manhvan.datn_mocsneaker.Presenter;

import com.example.manhvan.datn_mocsneaker.Model.MoThemMoiDonNH;
import com.example.manhvan.datn_mocsneaker.Model.ThemNhanVienKQ1;
import com.example.manhvan.datn_mocsneaker.View.PKInterface.TimKiemSanPhamInterface;
import com.example.manhvan.datn_mocsneaker.util.GioHang;

public class PreThemMoiDonNH implements ThemNhanVienKQ1 {
    private MoThemMoiDonNH moThemMoiDonNH=new MoThemMoiDonNH(this);
    private TimKiemSanPhamInterface timKiemSanPhamInterface;

    public PreThemMoiDonNH(TimKiemSanPhamInterface timKiemSanPhamInterface) {
        this.timKiemSanPhamInterface = timKiemSanPhamInterface;
    }
    public void themMoiDNH(int idNhanVien){
        if(GioHang.arrChiTietDonNhap.size()<1){
            timKiemSanPhamInterface.onFailed();
            return;
        }
        moThemMoiDonNH.xuLy(idNhanVien);
    }

    @Override
    public void onS() {
        timKiemSanPhamInterface.themThanhCong();
    }

    @Override
    public void onF() {
        timKiemSanPhamInterface.onFailed();
    }
}
