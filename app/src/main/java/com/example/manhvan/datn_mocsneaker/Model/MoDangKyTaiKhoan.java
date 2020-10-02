package com.example.manhvan.datn_mocsneaker.Model;

import com.example.manhvan.datn_mocsneaker.MyService.APIService;
import com.example.manhvan.datn_mocsneaker.MyService.Dataservice;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoDangKyTaiKhoan {
    private TimKiemNVKQ1 timKiemNVKQ1;

    public MoDangKyTaiKhoan(TimKiemNVKQ1 timKiemNVKQ1) {
        this.timKiemNVKQ1 = timKiemNVKQ1;
    }

    public void xuLy(String hoTen, String soDT, String diaChi, String matKhau) throws NoSuchAlgorithmException {
        Dataservice dataservice= APIService.getService();
        Call<String> callback=dataservice.dangKyTaiKhoan(hoTen,soDT,diaChi,maHoaMD5(matKhau));
        callback.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.body().toString().trim().equals("thanhcong")){
                    timKiemNVKQ1.onS();
                    return;
                }
                timKiemNVKQ1.onF();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }
    private String maHoaMD5(String matkhau) throws NoSuchAlgorithmException {
        String result="";
        MessageDigest md=MessageDigest.getInstance("MD5");
        md.update(matkhau.getBytes());
        BigInteger bigInteger=new BigInteger(1,md.digest());
        result=bigInteger.toString(16);
        return result;
    }
}
