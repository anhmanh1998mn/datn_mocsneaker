package com.example.manhvan.datn_mocsneaker.Model;

import com.example.manhvan.datn_mocsneaker.MyService.APIService;
import com.example.manhvan.datn_mocsneaker.MyService.Dataservice;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoSuaNhanVien {
    private SuaNhanVienKQ1 suaNhanVienKQ1;

    public MoSuaNhanVien(SuaNhanVienKQ1 suaNhanVienKQ1) {
        this.suaNhanVienKQ1 = suaNhanVienKQ1;
    }

    public void XuLy(int id, String tenNV, String phone, String ngaySinh, String diaChi, String cmt){
        Dataservice dataservice= APIService.getService();
        Call<String> callback=dataservice.SuaNhanVien(id,tenNV,phone,diaChi,ngaySinh,cmt);
        callback.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.body().toString().trim().equals("thanhcong")){
                    suaNhanVienKQ1.onS1();
                    return;
                }
                suaNhanVienKQ1.onF();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                suaNhanVienKQ1.onF();
            }
        });
    }

    public void XuLyKhoaTk(int id){
        Dataservice dataservice=APIService.getService();
        Call<String> callback=dataservice.KhoaTaiKhoan(id);
        callback.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.body().toString().trim().equals("thanhcong")){
                    suaNhanVienKQ1.onS2();
                    return;
                }
                suaNhanVienKQ1.onF();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                suaNhanVienKQ1.onF();
            }
        });
    }
}
