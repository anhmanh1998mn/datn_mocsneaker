package com.example.manhvan.datn_mocsneaker.Model;

import com.example.manhvan.datn_mocsneaker.MyService.APIService;
import com.example.manhvan.datn_mocsneaker.MyService.Dataservice;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoAnSanPham {
    public AnSPInter1 anSPInter1;

    public MoAnSanPham(AnSPInter1 anSPInter1) {
        this.anSPInter1 = anSPInter1;
    }

    public void xuLy(int idSP){
        Dataservice dataservice= APIService.getService();
        Call<String> callBack=dataservice.anSP(idSP);
        callBack.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.body().toString().trim().equals("thanhcong")){
                    anSPInter1.onS();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
    public interface AnSPInter1{
        public void onS();
    }
}
