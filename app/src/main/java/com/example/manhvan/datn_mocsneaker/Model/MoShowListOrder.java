package com.example.manhvan.datn_mocsneaker.Model;

import com.example.manhvan.datn_mocsneaker.MyService.APIService;
import com.example.manhvan.datn_mocsneaker.MyService.Dataservice;
import com.example.manhvan.datn_mocsneaker.entity.DonHang;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoShowListOrder {
    private KetQuaInterface timKiemNVKQ1;

    public MoShowListOrder(KetQuaInterface timKiemNVKQ1) {
        this.timKiemNVKQ1 = timKiemNVKQ1;
    }

    public static List<DonHang> lstDonHang,lstDonHangAdmin;

    public void xuLy(int maND,int quyenND,int trangThai){
        lstDonHang=new ArrayList<>();
        Dataservice dataservice= APIService.getService();
        Call<List<DonHang>> callback=dataservice.showListOrder(maND,quyenND,trangThai);
        callback.enqueue(new Callback<List<DonHang>>() {
            @Override
            public void onResponse(Call<List<DonHang>> call, Response<List<DonHang>> response) {
                lstDonHang=response.body();
                timKiemNVKQ1.onS();
            }

            @Override
            public void onFailure(Call<List<DonHang>> call, Throwable t) {

            }
        });
    }
    public void xuLy1(int quyenND,int trangThai){
        lstDonHangAdmin=new ArrayList<>();
        Dataservice dataservice= APIService.getService();
        Call<List<DonHang>> callback=dataservice.showListOrder1(quyenND,trangThai);
        callback.enqueue(new Callback<List<DonHang>>() {
            @Override
            public void onResponse(Call<List<DonHang>> call, Response<List<DonHang>> response) {
                lstDonHangAdmin=response.body();
                timKiemNVKQ1.onS1();
            }

            @Override
            public void onFailure(Call<List<DonHang>> call, Throwable t) {

            }
        });
    }

    public interface KetQuaInterface{
        public void onS();
        public void onF();
        public void onS1();
    }

}
