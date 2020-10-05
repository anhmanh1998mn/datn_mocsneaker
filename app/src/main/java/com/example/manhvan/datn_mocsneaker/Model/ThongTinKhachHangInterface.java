package com.example.manhvan.datn_mocsneaker.Model;

import com.example.manhvan.datn_mocsneaker.entity.ThongTinKhachHang;
import com.example.manhvan.datn_mocsneaker.entity.ThongTinNV;

import java.util.ArrayList;

public interface ThongTinKhachHangInterface {
    public void thongTinNhanVien(ArrayList<ThongTinNV> arrayList);
    public void thongTinKhachHang(ArrayList<ThongTinKhachHang> arrayList);
    public void loi();
}
