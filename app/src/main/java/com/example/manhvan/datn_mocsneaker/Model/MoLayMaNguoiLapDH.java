package com.example.manhvan.datn_mocsneaker.Model;

import com.example.manhvan.datn_mocsneaker.MyService.APIService;
import com.example.manhvan.datn_mocsneaker.MyService.Dataservice;
import com.example.manhvan.datn_mocsneaker.entity.MaNguoiLapDH;
import com.example.manhvan.datn_mocsneaker.util.GioHang;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoLayMaNguoiLapDH {
    private SuaNhanVienKQ1 suaNhanVienKQ1;

    public MoLayMaNguoiLapDH(SuaNhanVienKQ1 suaNhanVienKQ1) {
        this.suaNhanVienKQ1 = suaNhanVienKQ1;
    }
    private Dataservice dataservice= APIService.getService();

    public static List<MaNguoiLapDH> lstMaNguoiLap;
    private int maDonHang;
    public void xuLy(String phone,int role){
        lstMaNguoiLap=new ArrayList<>();

        Call<List<MaNguoiLapDH>> callback=dataservice.getMaNguoiLapDonHnag(phone,role);
        callback.enqueue(new Callback<List<MaNguoiLapDH>>() {
            @Override
            public void onResponse(Call<List<MaNguoiLapDH>> call, Response<List<MaNguoiLapDH>> response) {
                lstMaNguoiLap=response.body();
                suaNhanVienKQ1.onS1();
            }

            @Override
            public void onFailure(Call<List<MaNguoiLapDH>> call, Throwable t) {

            }
        });
    }

    public void xuLyThem(String diaCHi,int maNL,int quyen){

        Call<String> callback1=dataservice.themDonHang(diaCHi,maNL,quyen);
        callback1.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.body().trim().equals("")){
                    return;
                }
                maDonHang=Integer.parseInt(response.body().trim());
                themchiTietDOnHang();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    private void themchiTietDOnHang() {
        for(int i=0;i< GioHang.arrGioHang.size();i++){
            Call<String> callback2=dataservice.themChiTietDonHang(maDonHang,
                    GioHang.arrGioHang.get(i).getIdSP(),GioHang.arrGioHang.get(i).getSoLuong(),
                    GioHang.arrGioHang.get(i).getKichCo());
            callback2.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if(response.body().trim().equals("thanhcong")){
                        suaNhanVienKQ1.onS2();
                        return;
                    }
                    suaNhanVienKQ1.onF();
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                }
            });
        }
    }
}
