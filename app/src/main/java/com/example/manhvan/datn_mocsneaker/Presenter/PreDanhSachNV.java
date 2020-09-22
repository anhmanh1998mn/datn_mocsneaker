package com.example.manhvan.datn_mocsneaker.Presenter;

import com.example.manhvan.datn_mocsneaker.Model.LayDanhSachNVKQ1;
import com.example.manhvan.datn_mocsneaker.Model.MoDanhSachNhanVien;
import com.example.manhvan.datn_mocsneaker.View.LayDanhSachNVKQ2;

public class PreDanhSachNV implements LayDanhSachNVKQ1{
    private LayDanhSachNVKQ2 layDanhSachNVKQ2;

    public PreDanhSachNV(LayDanhSachNVKQ2 layDanhSachNVKQ2) {
        this.layDanhSachNVKQ2 = layDanhSachNVKQ2;
    }

    private MoDanhSachNhanVien moDanhSachNhanVien=new MoDanhSachNhanVien(this);
    public void DanhSachNhanVien(){
        moDanhSachNhanVien.XuLyDanhsachNV();
    }

    @Override
    public void onS() {
        layDanhSachNVKQ2.onSuccessed();
    }

    @Override
    public void onF(String t) {
        layDanhSachNVKQ2.onFailed(t);
    }
}
