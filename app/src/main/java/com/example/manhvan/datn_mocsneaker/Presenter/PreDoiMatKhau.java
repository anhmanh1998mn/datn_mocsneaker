package com.example.manhvan.datn_mocsneaker.Presenter;

import com.example.manhvan.datn_mocsneaker.Model.MoDoiMatKhau;
import com.example.manhvan.datn_mocsneaker.Model.TimKiemNVKQ1;
import com.example.manhvan.datn_mocsneaker.View.DoiMatKhauInterface;

import java.security.NoSuchAlgorithmException;

public class PreDoiMatKhau implements TimKiemNVKQ1 {
    private DoiMatKhauInterface doiMatKhauInterface;

    public PreDoiMatKhau(DoiMatKhauInterface doiMatKhauInterface) {
        this.doiMatKhauInterface = doiMatKhauInterface;
    }

    private MoDoiMatKhau moDoiMatKhau=new MoDoiMatKhau(this);
    public void doiMatKhau(String id,String matKhau) throws NoSuchAlgorithmException {
        moDoiMatKhau.xuLy(id,matKhau);
    }

    @Override
    public void onS() {
        doiMatKhauInterface.onSuccessed();
    }

    @Override
    public void onF() {
        doiMatKhauInterface.onFailed();
    }
}
