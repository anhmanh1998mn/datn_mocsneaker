package com.example.manhvan.datn_mocsneaker.Model;

import com.example.manhvan.datn_mocsneaker.MyService.APIService;
import com.example.manhvan.datn_mocsneaker.MyService.Dataservice;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoThongKeThang {
    private TongTienInterface tongTienInterface;

    public MoThongKeThang(TongTienInterface tongTienInterface) {
        this.tongTienInterface = tongTienInterface;
    }

    private Dataservice dataservice= APIService.getService();
    public void xuLyTongBan(String thang,String nam){
        Call<String> callback=dataservice.tinhTongTienBan(thang,nam);
        callback.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                tongTienInterface.layTienBanThanhCong(response.body().trim());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                tongTienInterface.thatBai();
            }
        });

        Call<String> callbackTienNhap=dataservice.tinhTongTienNhap(thang,nam);
        callbackTienNhap.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                tongTienInterface.layTienNhapThanhCong(response.body().trim());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                tongTienInterface.thatBai();
            }
        });
    }

    public interface TongTienInterface{
        public void layTienBanThanhCong(String tienBan);
        public void layTienNhapThanhCong(String tienBan);
        public void thatBai();
    }
}
