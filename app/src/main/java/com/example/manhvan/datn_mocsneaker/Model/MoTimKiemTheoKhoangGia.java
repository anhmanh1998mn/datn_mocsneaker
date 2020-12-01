package com.example.manhvan.datn_mocsneaker.Model;

import com.example.manhvan.datn_mocsneaker.MyService.APIService;
import com.example.manhvan.datn_mocsneaker.MyService.Dataservice;
import com.example.manhvan.datn_mocsneaker.entity.SanPhamMoi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoTimKiemTheoKhoangGia {
    private TimKiemGiaInter timKiemGiaInter;

    public MoTimKiemTheoKhoangGia(TimKiemGiaInter timKiemGiaInter) {
        this.timKiemGiaInter = timKiemGiaInter;
    }

    public static List<SanPhamMoi> lstKhoanGia;
    public void xuLy(int gia1,int gia2){
        lstKhoanGia=new ArrayList<>();
        Dataservice dataservice= APIService.getService();
        Call<List<SanPhamMoi>> callback=dataservice.timKiemKhoangGia(gia1,gia2);
        callback.enqueue(new Callback<List<SanPhamMoi>>() {
            @Override
            public void onResponse(Call<List<SanPhamMoi>> call, Response<List<SanPhamMoi>> response) {
                lstKhoanGia=response.body();
                timKiemGiaInter.onS();
            }

            @Override
            public void onFailure(Call<List<SanPhamMoi>> call, Throwable t) {

            }
        });
    }
    public interface TimKiemGiaInter{
        public void onS();
    }
}

