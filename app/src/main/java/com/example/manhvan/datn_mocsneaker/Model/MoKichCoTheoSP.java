package com.example.manhvan.datn_mocsneaker.Model;

import com.example.manhvan.datn_mocsneaker.MyService.APIService;
import com.example.manhvan.datn_mocsneaker.MyService.Dataservice;
import com.example.manhvan.datn_mocsneaker.entity.KichCoSP;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoKichCoTheoSP {
    private TimKiemNVKQ1 timKiemNVKQ1;

    public MoKichCoTheoSP(TimKiemNVKQ1 timKiemNVKQ1) {
        this.timKiemNVKQ1 = timKiemNVKQ1;
    }

    public static List<KichCoSP> lstKichCo;
    public void xuLy(int idnhan){
        lstKichCo=new ArrayList<>();
        Dataservice dataservice= APIService.getService();
        Call<List<KichCoSP>> callback=dataservice.kichCoTheoSanPham(idnhan);
        callback.enqueue(new Callback<List<KichCoSP>>() {
            @Override
            public void onResponse(Call<List<KichCoSP>> call, Response<List<KichCoSP>> response) {
                lstKichCo=response.body();
                timKiemNVKQ1.onS();
            }

            @Override
            public void onFailure(Call<List<KichCoSP>> call, Throwable t) {
                timKiemNVKQ1.onF();
            }
        });
    }
}
