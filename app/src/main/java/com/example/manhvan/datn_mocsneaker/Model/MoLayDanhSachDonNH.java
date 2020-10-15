package com.example.manhvan.datn_mocsneaker.Model;

import com.example.manhvan.datn_mocsneaker.MyService.APIService;
import com.example.manhvan.datn_mocsneaker.MyService.Dataservice;
import com.example.manhvan.datn_mocsneaker.entity.DonNhapHang;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoLayDanhSachDonNH {
    private TimKiemNVKQ1 timKiemNVKQ1;

    public MoLayDanhSachDonNH(TimKiemNVKQ1 timKiemNVKQ1) {
        this.timKiemNVKQ1 = timKiemNVKQ1;
    }

    public static List<DonNhapHang> arrDonNhapHang;
    public void xuLy(int status){
        arrDonNhapHang=new ArrayList<>();
        Dataservice dataservice= APIService.getService();
        Call<List<DonNhapHang>> callback=dataservice.danhSachDonNhap(status);
        callback.enqueue(new Callback<List<DonNhapHang>>() {
            @Override
            public void onResponse(Call<List<DonNhapHang>> call, Response<List<DonNhapHang>> response) {
                arrDonNhapHang=response.body();
                timKiemNVKQ1.onS();
            }

            @Override
            public void onFailure(Call<List<DonNhapHang>> call, Throwable t) {
                timKiemNVKQ1.onF();
            }
        });
    }
}
