package com.example.manhvan.datn_mocsneaker.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChiTietDonNhapLay {
    @SerializedName("quantity")
    @Expose
    private String quantity;
    @SerializedName("size")
    @Expose
    private String size;
    @SerializedName("product_name")
    @Expose
    private String productName;
    @SerializedName("price_in")
    @Expose
    private String priceIn;

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

    public String getPriceIn() {
        return priceIn;
    }

    public void setPriceIn(String priceIn) {
        this.priceIn = priceIn;
    }
}
