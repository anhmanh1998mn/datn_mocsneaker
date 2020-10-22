package com.example.manhvan.datn_mocsneaker.Presenter;

import com.example.manhvan.datn_mocsneaker.Model.MoDatLaiMatKhauQuen;

import java.security.NoSuchAlgorithmException;

public class PreDatLaiMatKhauQuen implements MoDatLaiMatKhauQuen.DatLaiMKInterface{
    private KetQuaDatLaiMKInterface ketQuaDatLaiMKInterface;

    public PreDatLaiMatKhauQuen(KetQuaDatLaiMKInterface ketQuaDatLaiMKInterface) {
        this.ketQuaDatLaiMKInterface = ketQuaDatLaiMKInterface;
    }

    private MoDatLaiMatKhauQuen moDatLaiMatKhauQuen=new MoDatLaiMatKhauQuen(this);
    public void datLaiMatKhau(String pass,String phone) throws NoSuchAlgorithmException {
        moDatLaiMatKhauQuen.xuLy(pass,phone);
    }

    @Override
    public void onS() {
        ketQuaDatLaiMKInterface.onSuccessed();
    }

    @Override
    public void onF() {
        ketQuaDatLaiMKInterface.onFailed();
    }

    public interface KetQuaDatLaiMKInterface{
        public void onSuccessed();
        public void onFailed();
    }
}
