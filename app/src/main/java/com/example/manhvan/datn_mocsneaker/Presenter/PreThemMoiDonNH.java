package com.example.manhvan.datn_mocsneaker.Presenter;

import com.example.manhvan.datn_mocsneaker.View.TimKiemSanPhamInterface;

public class PreThemMoiDonNH {
    private TimKiemSanPhamInterface timKiemSanPhamInterface;

    public PreThemMoiDonNH(TimKiemSanPhamInterface timKiemSanPhamInterface) {
        this.timKiemSanPhamInterface = timKiemSanPhamInterface;
    }
    public void themMoiDNH(int idNhanVien){
        timKiemSanPhamInterface.themThanhCong();
    }
}
