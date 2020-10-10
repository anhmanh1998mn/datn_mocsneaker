package com.example.manhvan.datn_mocsneaker.Model;

import com.example.manhvan.datn_mocsneaker.MyService.APIService;
import com.example.manhvan.datn_mocsneaker.MyService.Dataservice;
import com.example.manhvan.datn_mocsneaker.entity.SanPhamMoi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoSanPhamXemThemTatCa {
    private TimKiemNVKQ1 timKiemNVKQ1;

    public MoSanPhamXemThemTatCa(TimKiemNVKQ1 timKiemNVKQ1) {
        this.timKiemNVKQ1 = timKiemNVKQ1;
    }

    public static List<SanPhamMoi> lst;
    public void xuLy(int loaiSP){
        lst=new ArrayList<>();
        Dataservice dataservice= APIService.getService();
        Call<List<SanPhamMoi>> callback=dataservice.sanPhamXemThem(loaiSP);
        callback.enqueue(new Callback<List<SanPhamMoi>>() {
            @Override
            public void onResponse(Call<List<SanPhamMoi>> call, Response<List<SanPhamMoi>> response) {
                lst=response.body();
                timKiemNVKQ1.onS();
            }

            @Override
            public void onFailure(Call<List<SanPhamMoi>> call, Throwable t) {
                timKiemNVKQ1.onF();
            }
        });
    }
}
