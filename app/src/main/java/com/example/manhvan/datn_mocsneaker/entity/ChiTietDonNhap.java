package com.example.manhvan.datn_mocsneaker.entity;

public class ChiTietDonNhap {
    private int idSanPham,soLuong;
    private String kichCo,tenSP;

    public ChiTietDonNhap(int idSanPham, int soLuong, String kichCo, String tenSP) {
        this.idSanPham = idSanPham;
        this.soLuong = soLuong;
        this.kichCo = kichCo;
        this.tenSP = tenSP;
    }

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

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }
}
