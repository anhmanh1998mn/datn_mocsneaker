package com.example.manhvan.datn_mocsneaker.Model;

import android.telecom.Call;

import com.example.manhvan.datn_mocsneaker.MyService.APIService;
import com.example.manhvan.datn_mocsneaker.MyService.Dataservice;
import com.example.manhvan.datn_mocsneaker.entity.AddressOrder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class MoGetAddressOrderCustomer {
    private AddressOrder1Interface addressOrder1Interface;

    public MoGetAddressOrderCustomer(AddressOrder1Interface addressOrder1Interface) {
        this.addressOrder1Interface = addressOrder1Interface;
    }

    public static List<AddressOrder> arrAdressOrder;
    public void xuLy(String phone){
        arrAdressOrder=new ArrayList<>();
        Dataservice dataservice= APIService.getService();
        retrofit2.Call<List<AddressOrder>> callback=dataservice.getListAddress(phone);
        callback.enqueue(new Callback<List<AddressOrder>>() {
            @Override
            public void onResponse(retrofit2.Call<List<AddressOrder>> call, Response<List<AddressOrder>> response) {
                arrAdressOrder=response.body();
                addressOrder1Interface.onS();
            }

            @Override
            public void onFailure(retrofit2.Call<List<AddressOrder>> call, Throwable t) {

            }
        });
    }

    public interface AddressOrder1Interface{
        public void onS();
    }
}
