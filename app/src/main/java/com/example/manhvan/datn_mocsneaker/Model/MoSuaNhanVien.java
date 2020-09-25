package com.example.manhvan.datn_mocsneaker.Model;

import com.example.manhvan.datn_mocsneaker.MyService.APIService;
import com.example.manhvan.datn_mocsneaker.MyService.Dataservice;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoSuaNhanVien {
    private TimKiemNVKQ1 timKiemNVKQ1;

    public MoSuaNhanVien(TimKiemNVKQ1 timKiemNVKQ1) {
        this.timKiemNVKQ1 = timKiemNVKQ1;
    }

    public void XuLy(int id, String tenNV, String phone, String ngaySinh, String diaChi, String cmt){
        Dataservice dataservice= APIService.getService();
        Call<String> callback=dataservice.SuaNhanVien(id,tenNV,phone,diaChi,ngaySinh,cmt);
        callback.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.body().toString().trim().equals("thanhcong")){
                    timKiemNVKQ1.onS();
                    return;
                }
                timKiemNVKQ1.onF();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                timKiemNVKQ1.onF();
            }
        });
    }
}
