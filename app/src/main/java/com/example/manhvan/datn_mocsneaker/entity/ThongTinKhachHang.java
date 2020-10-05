package com.example.manhvan.datn_mocsneaker.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ThongTinKhachHang {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("user_password")
    @Expose
    private String userPassword;
    @SerializedName("customer_name")
    @Expose
    private String customerName;
    @SerializedName("customer_address")
    @Expose
    private String customerAddress;

    public String getUserPassword() {
        return userPassword;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }
}
