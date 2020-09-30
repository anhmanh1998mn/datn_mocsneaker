package com.example.manhvan.datn_mocsneaker.Model;

import com.example.manhvan.datn_mocsneaker.MyService.APIService;
import com.example.manhvan.datn_mocsneaker.MyService.Dataservice;
import com.example.manhvan.datn_mocsneaker.entity.ProductImage;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MogetProductImage {
    private TimKiemNVKQ1 timKiemNVKQ1;

    public MogetProductImage(TimKiemNVKQ1 timKiemNVKQ1) {
        this.timKiemNVKQ1 = timKiemNVKQ1;
    }

    public static List<ProductImage> lstIMG;
    public void XuLy(int id){
        lstIMG=new ArrayList<>();
        Dataservice dataservice= APIService.getService();
        Call<List<ProductImage>> callback=dataservice.GetProductImage(id);
        callback.enqueue(new Callback<List<ProductImage>>() {
            @Override
            public void onResponse(Call<List<ProductImage>> call, Response<List<ProductImage>> response) {
                lstIMG=response.body();
                timKiemNVKQ1.onS();
            }

            @Override
            public void onFailure(Call<List<ProductImage>> call, Throwable t) {
                timKiemNVKQ1.onF();
            }
        });
    }
}
