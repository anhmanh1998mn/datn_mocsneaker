package com.example.manhvan.datn_mocsneaker.Presenter;

import com.example.manhvan.datn_mocsneaker.Model.DangNhapMo;
import com.example.manhvan.datn_mocsneaker.Model.MoDangNhap;
import com.example.manhvan.datn_mocsneaker.View.DangNhap;

import java.security.NoSuchAlgorithmException;

public class PreDangNhap implements DangNhapMo {
    private MoDangNhap moDangNhap=new MoDangNhap(this);
    private DangNhap dangNhap;

    public PreDangNhap(DangNhap dangNhap) {
        this.dangNhap = dangNhap;
    }

    public void dangNhapTaiKhoan(String taiKhoan, String matKhau) throws NoSuchAlgorithmException {
        if (taiKhoan.equals("")||matKhau.equals("")){
            dangNhap.thatBai();
            return;
        }
        moDangNhap.xuLy(taiKhoan,matKhau);
    }

    @Override
    public void thanhCong(String quyen) {
        dangNhap.thanhCong(quyen);
    }

    @Override
    public void thatBai() {
        dangNhap.thatBai();
    }
}
