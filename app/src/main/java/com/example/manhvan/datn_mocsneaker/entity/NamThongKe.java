package com.example.manhvan.datn_mocsneaker.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NamThongKe {
    @SerializedName("thang")
    @Expose
    private String thang;
    @SerializedName("tongtien")
    @Expose
    private String tongtien;

    public NamThongKe(String thang, String tongtien) {
        this.thang = thang;
        this.tongtien = tongtien;
    }

    public String getThang() {
        return thang;
    }

    public void setThang(String thang) {
        this.thang = thang;
    }

    public String getTongtien() {
        return tongtien;
    }

    public void setTongtien(String tongtien) {
        this.tongtien = tongtien;
    }
}
