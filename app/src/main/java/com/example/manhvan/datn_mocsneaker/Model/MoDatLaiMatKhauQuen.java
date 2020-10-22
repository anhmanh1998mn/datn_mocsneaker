package com.example.manhvan.datn_mocsneaker.Model;

import com.example.manhvan.datn_mocsneaker.MyService.APIService;
import com.example.manhvan.datn_mocsneaker.MyService.Dataservice;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoDatLaiMatKhauQuen {
    private DatLaiMKInterface datLaiMKInterface;

    public MoDatLaiMatKhauQuen(DatLaiMKInterface datLaiMKInterface) {
        this.datLaiMKInterface = datLaiMKInterface;
    }

    public void xuLy(String pass, String phone) throws NoSuchAlgorithmException {
        Dataservice dataservice= APIService.getService();
        Call<String> callback=dataservice.layMatKhau(maHoaMD5(pass),phone);
        callback.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                datLaiMKInterface.onS();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
    public interface DatLaiMKInterface{
        public void onS();
        public void onF();
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
