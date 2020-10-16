package com.example.manhvan.datn_mocsneaker.Model;

import com.example.manhvan.datn_mocsneaker.MyService.APIService;
import com.example.manhvan.datn_mocsneaker.MyService.Dataservice;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoDuyetDonNhapHang {
    private DuyetDonNhapInterface duyetDonNhapInterface;

    public MoDuyetDonNhapHang(DuyetDonNhapInterface duyetDonNhapInterface) {
        this.duyetDonNhapInterface = duyetDonNhapInterface;
    }

    public void xuLy(int maDonNhap, int trangThai){
        Dataservice dataservice= APIService.getService();
        Call<String> callback=dataservice.duyetDonNhapHang(maDonNhap,trangThai);
        callback.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.body().trim().equals("thanhcong")){
                    duyetDonNhapInterface.onS();
                    return;
                }
                duyetDonNhapInterface.onF();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                duyetDonNhapInterface.onF();
            }
        });
    }
}
