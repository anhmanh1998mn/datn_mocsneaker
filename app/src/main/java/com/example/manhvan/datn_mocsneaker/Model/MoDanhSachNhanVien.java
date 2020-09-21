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

public class MoDanhSachNhanVien {
    private LayDanhSachNVKQ1 layDanhSachNVKQ1;

    public MoDanhSachNhanVien(LayDanhSachNVKQ1 layDanhSachNVKQ1) {
        this.layDanhSachNVKQ1 = layDanhSachNVKQ1;
    }

    public static ArrayList<NhanVien> arrayListNV;
    public void XuLyDanhsachNV(){
        arrayListNV=new ArrayList<>();
        Dataservice dataservice= APIService.getService();
        Call<List<NhanVien>> callback=dataservice.GetNhanVien();
        callback.enqueue(new Callback<List<NhanVien>>() {
            @Override
            public void onResponse(Call<List<NhanVien>> call, Response<List<NhanVien>> response) {
                arrayListNV= (ArrayList<NhanVien>) response.body();
                layDanhSachNVKQ1.onS();
                Log.d("sizekq",arrayListNV.size()+"");
            }

            @Override
            public void onFailure(Call<List<NhanVien>> call, Throwable t) {
                layDanhSachNVKQ1.onF();
            }
        });
    }
}
