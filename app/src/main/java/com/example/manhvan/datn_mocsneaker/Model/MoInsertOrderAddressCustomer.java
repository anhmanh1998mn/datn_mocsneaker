package com.example.manhvan.datn_mocsneaker.Model;

import com.example.manhvan.datn_mocsneaker.MyService.APIService;
import com.example.manhvan.datn_mocsneaker.MyService.Dataservice;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoInsertOrderAddressCustomer {
    private ThemDiaChiInterface1 themDiaChiInterface1;

    public MoInsertOrderAddressCustomer(ThemDiaChiInterface1 themDiaChiInterface1) {
        this.themDiaChiInterface1 = themDiaChiInterface1;
    }

    public void xuLy(int maKH, String diaChi){
        Dataservice dataservice= APIService.getService();
        Call<String> callback=dataservice.insertOrderAddress(maKH,diaChi);
        callback.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.body().toString().trim().equals("thanhcong")){
                    themDiaChiInterface1.onS();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }
    public interface ThemDiaChiInterface1{
        public void onS();
    }
}
