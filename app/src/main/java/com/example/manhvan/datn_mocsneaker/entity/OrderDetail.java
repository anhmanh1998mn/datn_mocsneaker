package com.example.manhvan.datn_mocsneaker.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderDetail {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("quantity")
    @Expose
    private String quantity;
    @SerializedName("size")
    @Expose
    private String size;
    @SerializedName("product_name")
    @Expose
    private String productName;
    @SerializedName("product_url")
    @Expose
    private String productUrl;
    @SerializedName("price_out")
    @Expose
    private String priceOut;
    @SerializedName("maSP")
    @Expose
    private String maSP;

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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
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

    public String getPriceOut() {
        return priceOut;
    }

    public void setPriceOut(String priceOut) {
        this.priceOut = priceOut;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

}
