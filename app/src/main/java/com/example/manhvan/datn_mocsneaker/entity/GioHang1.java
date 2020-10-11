package com.example.manhvan.datn_mocsneaker.entity;

public class GioHang1 {
    private int idSP,soLuong,DonGia;
    private String tenSP,kichCo,duongDan;

    public int getIdSP() {
        return idSP;
    }

    public void setIdSP(int idSP) {
        this.idSP = idSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getDonGia() {
        return DonGia;
    }

    public void setDonGia(int donGia) {
        DonGia = donGia;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getKichCo() {
        return kichCo;
    }

    public void setKichCo(String kichCo) {
        this.kichCo = kichCo;
    }

    public String getDuongDan() {
        return duongDan;
    }

    public void setDuongDan(String duongDan) {
        this.duongDan = duongDan;
    }

    public GioHang1(int idSP, int soLuong, int donGia, String tenSP, String kichCo, String duongDan) {
        this.idSP = idSP;
        this.soLuong = soLuong;
        DonGia = donGia;
        this.tenSP = tenSP;
        this.kichCo = kichCo;
        this.duongDan = duongDan;
    }
}
