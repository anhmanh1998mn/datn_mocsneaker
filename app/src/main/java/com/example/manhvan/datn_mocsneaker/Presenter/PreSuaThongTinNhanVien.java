package com.example.manhvan.datn_mocsneaker.Presenter;

import com.example.manhvan.datn_mocsneaker.Model.MoSuaNhanVien;
import com.example.manhvan.datn_mocsneaker.Model.TimKiemNVKQ1;
import com.example.manhvan.datn_mocsneaker.View.SuaNhanVienKQ2;

public class PreSuaThongTinNhanVien implements TimKiemNVKQ1 {
    private MoSuaNhanVien moSuaNhanVien=new MoSuaNhanVien(this);
    private SuaNhanVienKQ2 suaNhanVienKQ2;

    public PreSuaThongTinNhanVien(SuaNhanVienKQ2 suaNhanVienKQ2) {
        this.suaNhanVienKQ2 = suaNhanVienKQ2;
    }

    public void SuaThongTin(int id, String tenNV, String phone, String ngaySinh, String diaChi, String cmt){
        if(tenNV.equals("")||phone.equals("")||ngaySinh.equals("")||cmt.equals("")||diaChi.equals("")){
            suaNhanVienKQ2.onFailed();
            return;
        }
        moSuaNhanVien.XuLy(id,tenNV,phone,ngaySinh,diaChi,cmt);
    }

    @Override
    public void onS() {
        suaNhanVienKQ2.onSuccess();
    }

    @Override
    public void onF() {
        suaNhanVienKQ2.onFailed();
    }
}
