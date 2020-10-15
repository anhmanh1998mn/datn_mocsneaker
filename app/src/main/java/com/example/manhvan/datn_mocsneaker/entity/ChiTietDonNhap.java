package com.example.manhvan.datn_mocsneaker.entity;

public class ChiTietDonNhap {
    private int idSanPham,soLuong,giaSP;
    private String kichCo,tenSP;

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

    public int getGiaSP() {
        return giaSP;
    }

    public void setGiaSP(int giaSP) {
        this.giaSP = giaSP;
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

    public ChiTietDonNhap(int idSanPham, int soLuong, int giaSP, String kichCo, String tenSP) {
        this.idSanPham = idSanPham;
        this.soLuong = soLuong;
        this.giaSP = giaSP;
        this.kichCo = kichCo;
        this.tenSP = tenSP;
    }
}
