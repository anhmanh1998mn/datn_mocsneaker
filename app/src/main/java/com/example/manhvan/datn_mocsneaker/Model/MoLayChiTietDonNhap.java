package com.example.manhvan.datn_mocsneaker.Model;

import com.example.manhvan.datn_mocsneaker.MyService.APIService;
import com.example.manhvan.datn_mocsneaker.MyService.Dataservice;
import com.example.manhvan.datn_mocsneaker.entity.ChiTietDonNhapLay;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoLayChiTietDonNhap {
    private TimKiemNVKQ1 timKiemNVKQ1;

    public MoLayChiTietDonNhap(TimKiemNVKQ1 timKiemNVKQ1) {
        this.timKiemNVKQ1 = timKiemNVKQ1;
    }

    public static List<ChiTietDonNhapLay> arrListChiTiet;
    public void xuLy(int maDonNhap){
        arrListChiTiet=new ArrayList<>();
        Dataservice dataservice= APIService.getService();
        Call<List<ChiTietDonNhapLay>> callback=dataservice.layChiTietDonNhap(maDonNhap);
        callback.enqueue(new Callback<List<ChiTietDonNhapLay>>() {
            @Override
            public void onResponse(Call<List<ChiTietDonNhapLay>> call, Response<List<ChiTietDonNhapLay>> response) {
                arrListChiTiet=response.body();
                timKiemNVKQ1.onS();
            }

            @Override
            public void onFailure(Call<List<ChiTietDonNhapLay>> call, Throwable t) {
                timKiemNVKQ1.onF();
            }
        });
    }
}
