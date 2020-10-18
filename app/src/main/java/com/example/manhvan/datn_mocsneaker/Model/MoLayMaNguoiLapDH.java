package com.example.manhvan.datn_mocsneaker.Model;

import com.example.manhvan.datn_mocsneaker.MyService.APIService;
import com.example.manhvan.datn_mocsneaker.MyService.Dataservice;
import com.example.manhvan.datn_mocsneaker.entity.MaNguoiLapDH;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoLayMaNguoiLapDH {
    private TimKiemNVKQ1 timKiemNVKQ1;

    public MoLayMaNguoiLapDH(TimKiemNVKQ1 timKiemNVKQ1) {
        this.timKiemNVKQ1 = timKiemNVKQ1;
    }

    public static List<MaNguoiLapDH> lstMaNguoiLap;
    public void xuLy(String phone,int role){
        lstMaNguoiLap=new ArrayList<>();
        Dataservice dataservice= APIService.getService();
        Call<List<MaNguoiLapDH>> callback=dataservice.getMaNguoiLapDonHnag(phone,role);
        callback.enqueue(new Callback<List<MaNguoiLapDH>>() {
            @Override
            public void onResponse(Call<List<MaNguoiLapDH>> call, Response<List<MaNguoiLapDH>> response) {
                lstMaNguoiLap=response.body();
                timKiemNVKQ1.onS();
            }

            @Override
            public void onFailure(Call<List<MaNguoiLapDH>> call, Throwable t) {

            }
        });
    }
}
