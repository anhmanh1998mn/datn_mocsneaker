package com.example.manhvan.datn_mocsneaker.Presenter;

import com.example.manhvan.datn_mocsneaker.Model.MoDangKyTaiKhoan;
import com.example.manhvan.datn_mocsneaker.Model.TimKiemNVKQ1;
import com.example.manhvan.datn_mocsneaker.View.PKInterface.DangKyTaiKhoanKH;

import java.security.NoSuchAlgorithmException;

public class PreDangKyTaiKhoanKH implements TimKiemNVKQ1 {
    private DangKyTaiKhoanKH dangKyTaiKhoanKH;

    public PreDangKyTaiKhoanKH(DangKyTaiKhoanKH dangKyTaiKhoanKH) {
        this.dangKyTaiKhoanKH = dangKyTaiKhoanKH;
    }

    private MoDangKyTaiKhoan moDangKyTaiKhoan=new MoDangKyTaiKhoan(this);
    public void dangKyTaiKhoanKH(String hoTen,String soDT,String diaChi,String matKhau) throws NoSuchAlgorithmException {
        moDangKyTaiKhoan.xuLy(hoTen,soDT,diaChi,matKhau);
    }

    @Override
    public void onS() {
        dangKyTaiKhoanKH.dangKyThanhCong();
    }

    @Override
    public void onF() {
        dangKyTaiKhoanKH.thatBai();
    }
}
