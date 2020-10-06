package com.example.manhvan.datn_mocsneaker.Model;

import com.example.manhvan.datn_mocsneaker.MyService.APIService;
import com.example.manhvan.datn_mocsneaker.MyService.Dataservice;
import com.example.manhvan.datn_mocsneaker.entity.KhachHang;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoQuanLyKhachHang {
    private TimKiemNVKQ1 timKiemNVKQ1;

    public MoQuanLyKhachHang(TimKiemNVKQ1 timKiemNVKQ1) {
        this.timKiemNVKQ1 = timKiemNVKQ1;
    }

    public static List<KhachHang> lstKH;
    public void xuLy(){
        lstKH=new ArrayList<>();
        Dataservice dataservice= APIService.getService();
        Call<List<KhachHang>> callback=dataservice.danhSachKhachHang();
        callback.enqueue(new Callback<List<KhachHang>>() {
            @Override
            public void onResponse(Call<List<KhachHang>> call, Response<List<KhachHang>> response) {
                lstKH=response.body();
                timKiemNVKQ1.onS();
            }

            @Override
            public void onFailure(Call<List<KhachHang>> call, Throwable t) {
                timKiemNVKQ1.onF();
            }
        });
    }


}
