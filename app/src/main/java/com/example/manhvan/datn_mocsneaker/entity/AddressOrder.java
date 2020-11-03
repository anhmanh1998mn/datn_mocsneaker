package com.example.manhvan.datn_mocsneaker.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddressOrder {
    @SerializedName("customer_id")
    @Expose
    private String customerId;
    @SerializedName("a_order_address")
    @Expose
    private String aOrderAddress;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getAOrderAddress() {
        return aOrderAddress;
    }

    public void setAOrderAddress(String aOrderAddress) {
        this.aOrderAddress = aOrderAddress;
    }

}
