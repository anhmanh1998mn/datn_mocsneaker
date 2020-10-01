package com.example.manhvan.datn_mocsneaker.Model;

import com.example.manhvan.datn_mocsneaker.MyService.APIService;
import com.example.manhvan.datn_mocsneaker.MyService.Dataservice;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoCheckRegister {
    private TimKiemNVKQ1 timKiemNVKQ1;

    public MoCheckRegister(TimKiemNVKQ1 timKiemNVKQ1) {
        this.timKiemNVKQ1 = timKiemNVKQ1;
    }

    public void xuLy(String phone){
        Dataservice dataservice= APIService.getService();
        Call<String> callback=dataservice.checkRegister(phone);
        callback.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.body().toString().trim().equals("tontai")){
                    timKiemNVKQ1.onS();
                    return;
                }
                timKiemNVKQ1.onF();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });
    }
}
