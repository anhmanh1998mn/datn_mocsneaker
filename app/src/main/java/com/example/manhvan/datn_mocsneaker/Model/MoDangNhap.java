package com.example.manhvan.datn_mocsneaker.Model;

import com.example.manhvan.datn_mocsneaker.MyService.APIService;
import com.example.manhvan.datn_mocsneaker.MyService.Dataservice;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoDangNhap {
    private DangNhapMo dangNhapMo;

    public MoDangNhap(DangNhapMo dangNhapMo) {
        this.dangNhapMo = dangNhapMo;
    }

    public void xuLy(String taiKhoan, String matKhau) throws NoSuchAlgorithmException {
        Dataservice dataservice= APIService.getService();
        Call<String> callback=dataservice.loGin(taiKhoan,maHoaMD5(matKhau));
        callback.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.body().toString().equals("thatbai")){
                    dangNhapMo.thatBai();
                    return;
                }
                dangNhapMo.thanhCong(response.body().toString().trim());
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
