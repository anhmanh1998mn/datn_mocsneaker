package com.example.manhvan.datn_mocsneaker.Model;

import com.example.manhvan.datn_mocsneaker.MyService.APIService;
import com.example.manhvan.datn_mocsneaker.MyService.Dataservice;
import com.example.manhvan.datn_mocsneaker.entity.ThongTinKhachHang;
import com.example.manhvan.datn_mocsneaker.entity.ThongTinNV;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoThongTinTaiKhon {
    public ThongTinKhachHangInterface thongTinKhachHangInterface;

    public MoThongTinTaiKhon(ThongTinKhachHangInterface thongTinKhachHangInterface) {
        this.thongTinKhachHangInterface = thongTinKhachHangInterface;
    }

    public ArrayList<ThongTinNV> arrNV;
    public ArrayList<ThongTinKhachHang> arrKH;
    public void xuLy(String role,String phone){
        Dataservice dataservice= APIService.getService();
        if(role.equals("2")||role.equals("1")){
            arrNV=new ArrayList<>();
            Call<List<ThongTinNV>> callback=dataservice.thongTinNhanVien(role,phone);
            callback.enqueue(new Callback<List<ThongTinNV>>() {
                @Override
                public void onResponse(Call<List<ThongTinNV>> call, Response<List<ThongTinNV>> response) {
                    arrNV= (ArrayList<ThongTinNV>) response.body();
                    thongTinKhachHangInterface.thongTinNhanVien(arrNV);
                }

                @Override
                public void onFailure(Call<List<ThongTinNV>> call, Throwable t) {
                    thongTinKhachHangInterface.loi();
                }
            });

        }else if(role.equals("3")){
            arrKH=new ArrayList<>();
            Call<List<ThongTinKhachHang>> call=dataservice.thongTinKhachHang(role,phone);
            call.enqueue(new Callback<List<ThongTinKhachHang>>() {
                @Override
                public void onResponse(Call<List<ThongTinKhachHang>> call, Response<List<ThongTinKhachHang>> response) {
                    arrKH= (ArrayList<ThongTinKhachHang>) response.body();
                    thongTinKhachHangInterface.thongTinKhachHang(arrKH);
                }

                @Override
                public void onFailure(Call<List<ThongTinKhachHang>> call, Throwable t) {
                    thongTinKhachHangInterface.loi();
                }
            });
        }

    }
}
