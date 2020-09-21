package com.example.manhvan.datn_mocsneaker.Model;

import android.telecom.Call;

import com.example.manhvan.datn_mocsneaker.MyService.APIService;
import com.example.manhvan.datn_mocsneaker.MyService.Dataservice;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import retrofit2.Callback;
import retrofit2.Response;

public class MoThemNhanVien {
    private ThemNhanVienKQ1 themNhanVienKQ1;

    public MoThemNhanVien(ThemNhanVienKQ1 themNhanVienKQ1) {
        this.themNhanVienKQ1 = themNhanVienKQ1;
    }

    public void XuLyThemNV(String tenNV, String sdt, String diaChi, String ngaySinh, String soCMT, String taikhoan, String matkhau) throws NoSuchAlgorithmException {
        Dataservice dataservice= APIService.getService();
        retrofit2.Call<String> callback=dataservice.ThemNhanVien(tenNV,sdt,diaChi,ngaySinh,soCMT,taikhoan,maHoaMD5(matkhau));
        callback.enqueue(new Callback<String>() {
            @Override
            public void onResponse(retrofit2.Call<String> call, Response<String> response) {
                String kq=response.body().toString().trim();
                if(kq.equals("thanhcong")){
                    themNhanVienKQ1.onS();
                }else {
                    themNhanVienKQ1.onF();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<String> call, Throwable t) {
                themNhanVienKQ1.onF();
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
