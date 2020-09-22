package com.example.manhvan.datn_mocsneaker.Model;

import android.util.Log;

import com.example.manhvan.datn_mocsneaker.MyService.APIService;
import com.example.manhvan.datn_mocsneaker.MyService.Dataservice;
import com.example.manhvan.datn_mocsneaker.entity.NhanVien;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoTimKiemNV {
    private TimKiemNVKQ1 timKiemNVKQ1;
    public MoTimKiemNV(TimKiemNVKQ1 timKiemNVKQ1) {
        this.timKiemNVKQ1 = timKiemNVKQ1;
    }


    public static ArrayList<NhanVien> arrTimKiem;
    public void XuLy(String tenNV){
        arrTimKiem=new ArrayList<>();
        Dataservice dataservice= APIService.getService();
        Call<List<NhanVien>> callbak=dataservice.GetNVTimKiem(tenNV);
        callbak.enqueue(new Callback<List<NhanVien>>() {
            @Override
            public void onResponse(Call<List<NhanVien>> call, Response<List<NhanVien>> response) {
                arrTimKiem= (ArrayList<NhanVien>) response.body();
                Log.d("timkiem",arrTimKiem.size()+"");
                timKiemNVKQ1.onS();
            }

            @Override
            public void onFailure(Call<List<NhanVien>> call, Throwable t) {
                timKiemNVKQ1.onF();
            }
        });
    }
}
