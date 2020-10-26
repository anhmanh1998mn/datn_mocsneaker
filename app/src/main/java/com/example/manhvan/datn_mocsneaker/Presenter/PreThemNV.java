package com.example.manhvan.datn_mocsneaker.Presenter;

import com.example.manhvan.datn_mocsneaker.Model.MoThemNhanVien;
import com.example.manhvan.datn_mocsneaker.Model.ThemNhanVienKQ1;
import com.example.manhvan.datn_mocsneaker.View.PKInterface.ThemNhanVienKQ2;

import java.security.NoSuchAlgorithmException;


public class PreThemNV implements ThemNhanVienKQ1{
    private ThemNhanVienKQ2 themNhanVienKQ2;

    public PreThemNV(ThemNhanVienKQ2 themNhanVienKQ2) {
        this.themNhanVienKQ2 = themNhanVienKQ2;
    }

    private MoThemNhanVien moThemNhanVien=new MoThemNhanVien(this);
    public void ThemMoiNV(String tenNV,String sdt, String diaChi,String ngaySinh,String soCMT,String taikhoan,String matkhau ) throws NoSuchAlgorithmException {
        if(tenNV.equals("")||sdt.equals("")||diaChi.equals("")||ngaySinh.equals("")||soCMT.equals("")||taikhoan.equals("")||matkhau.equals("")){
            themNhanVienKQ2.onFailed();
        }else {
            moThemNhanVien.XuLyThemNV(tenNV,sdt,diaChi,ngaySinh,soCMT,taikhoan,matkhau);
        }
    }

    @Override
    public void onS() {
        themNhanVienKQ2.onSuccessed();
    }

    @Override
    public void onF() {
        themNhanVienKQ2.onFailed();
    }
}
