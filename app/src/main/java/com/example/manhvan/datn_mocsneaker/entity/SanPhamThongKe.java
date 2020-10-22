package com.example.manhvan.datn_mocsneaker.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SanPhamThongKe {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("quantity")
    @Expose
    private String quantity;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("product_name")
    @Expose
    private String productName;
    @SerializedName("product_url")
    @Expose
    private String productUrl;
    @SerializedName("tong")
    @Expose
    private String tong;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getTong() {
        return tong;
    }

    public void setTong(String tong) {
        this.tong = tong;
    }
}
