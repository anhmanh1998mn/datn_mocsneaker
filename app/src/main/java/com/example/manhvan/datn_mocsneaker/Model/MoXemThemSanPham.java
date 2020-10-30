package com.example.manhvan.datn_mocsneaker.Model;

import com.example.manhvan.datn_mocsneaker.MyService.APIService;
import com.example.manhvan.datn_mocsneaker.MyService.Dataservice;
import com.example.manhvan.datn_mocsneaker.entity.SanPhamMoi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoXemThemSanPham {
    private TimKiemNVKQ1 timKiemNVKQ1;

    public MoXemThemSanPham(TimKiemNVKQ1 timKiemNVKQ1) {
        this.timKiemNVKQ1 = timKiemNVKQ1;
    }

    public static List<SanPhamMoi> lstSanPhamXemThem,lstNu,lstDoi;
    public void xuLy(int sanPham){

        switch (sanPham){
            case 1:{
                lstSanPhamXemThem=new ArrayList<>();
                lstSanPhamXemThem.clear();
                Dataservice dataservice= APIService.getService();
                Call<List<SanPhamMoi>> callback=dataservice.GetGiayNam(sanPham);
                callback.enqueue(new Callback<List<SanPhamMoi>>() {
                    @Override
                    public void onResponse(Call<List<SanPhamMoi>> call, Response<List<SanPhamMoi>> response) {
                        lstSanPhamXemThem=response.body();
                        timKiemNVKQ1.onS();
                    }

                    @Override
                    public void onFailure(Call<List<SanPhamMoi>> call, Throwable t) {
                        timKiemNVKQ1.onF();
                    }
                });
                break;
            }
            case 2:{
                lstNu=new ArrayList<>();
                lstNu.clear();
                Dataservice dataservice= APIService.getService();
                Call<List<SanPhamMoi>> callback=dataservice.GetGiayNam(sanPham);
                callback.enqueue(new Callback<List<SanPhamMoi>>() {
                    @Override
                    public void onResponse(Call<List<SanPhamMoi>> call, Response<List<SanPhamMoi>> response) {
                        lstNu=response.body();
                        timKiemNVKQ1.onS();
                    }

                    @Override
                    public void onFailure(Call<List<SanPhamMoi>> call, Throwable t) {
                        timKiemNVKQ1.onF();
                    }
                });
                break;
            }
            case 3:{
                lstDoi=new ArrayList<>();
                lstDoi.clear();
                Dataservice dataservice= APIService.getService();
                Call<List<SanPhamMoi>> callback=dataservice.GetGiayNam(sanPham);
                callback.enqueue(new Callback<List<SanPhamMoi>>() {
                    @Override
                    public void onResponse(Call<List<SanPhamMoi>> call, Response<List<SanPhamMoi>> response) {
                        lstDoi=response.body();
                        timKiemNVKQ1.onS();
                    }

                    @Override
                    public void onFailure(Call<List<SanPhamMoi>> call, Throwable t) {
                        timKiemNVKQ1.onF();
                    }
                });
                break;
            }
        }


    }
}
