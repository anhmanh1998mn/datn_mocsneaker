package com.example.manhvan.datn_mocsneaker.Model;

import android.util.Log;

import com.example.manhvan.datn_mocsneaker.MyService.APIService;
import com.example.manhvan.datn_mocsneaker.MyService.Dataservice;
import com.example.manhvan.datn_mocsneaker.util.GioHang;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoInsertOrderAddressCustomer {
    private ThemDiaChiInterface1 themDiaChiInterface1;

    public MoInsertOrderAddressCustomer(ThemDiaChiInterface1 themDiaChiInterface1) {
        this.themDiaChiInterface1 = themDiaChiInterface1;
    }

    public void xuLy(int maKH, String diaChi){
        Dataservice dataservice= APIService.getService();
        Call<String> callback=dataservice.insertOrderAddress(maKH,diaChi);
        callback.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.body().toString().trim().equals("thanhcong")){
                    themDiaChiInterface1.onS();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }

    public void xuLyTruSoLuongTronKho(){
        Dataservice dataservice= APIService.getService();
        for (int i = 0; i< GioHang.arrGioHang.size(); i++){
            Call<String> callback=dataservice.truSoLuongDatHang((GioHang.arrGioHang.get(i).getSoLuong()),
                    GioHang.arrGioHang.get(i).getKichCo().equals("39")?1:GioHang.arrGioHang.get(i).getKichCo().equals("40")?
                            2:GioHang.arrGioHang.get(i).getKichCo().equals("41")?3:GioHang.arrGioHang.get(i).getKichCo().equals("42")?4:5,
                    (GioHang.arrGioHang.get(i).getIdSP()));
            callback.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if(response.body().toString().trim().equals("thanhcong")){
                        themDiaChiInterface1.truSoLuong();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Log.d("Fail",t.toString());
                }
            });
        }
    }

    public interface ThemDiaChiInterface1{
        public void onS();
        public void truSoLuong();
    }
}
