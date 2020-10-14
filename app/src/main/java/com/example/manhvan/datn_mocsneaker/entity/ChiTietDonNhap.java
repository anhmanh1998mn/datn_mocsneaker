package com.example.manhvan.datn_mocsneaker.entity;

public class ChiTietDonNhap {
    private int idSanPham,soLuong;
    private String kichCo;

    public int getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(int idSanPham) {
        this.idSanPham = idSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getKichCo() {
        return kichCo;
    }

    public void setKichCo(String kichCo) {
        this.kichCo = kichCo;
    }

    public ChiTietDonNhap(int idSanPham, int soLuong, String kichCo) {
        this.idSanPham = idSanPham;
        this.soLuong = soLuong;
        this.kichCo = kichCo;
    }
}
