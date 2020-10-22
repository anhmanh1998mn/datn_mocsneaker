package com.example.manhvan.datn_mocsneaker.Model;

import com.example.manhvan.datn_mocsneaker.MyService.APIService;
import com.example.manhvan.datn_mocsneaker.MyService.Dataservice;
import com.example.manhvan.datn_mocsneaker.entity.SanPhamThongKe;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoThongKeThang {
    public static List<SanPhamThongKe> arrSPBanNhieu,arrSPBanIt;
    private TongTienInterface tongTienInterface;

    public MoThongKeThang(TongTienInterface tongTienInterface) {
        this.tongTienInterface = tongTienInterface;
    }

    private Dataservice dataservice= APIService.getService();
    public void xuLyTongBan(String thang,String nam){
        Call<String> callback=dataservice.tinhTongTienBan(thang,nam);
        callback.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                tongTienInterface.layTienBanThanhCong(response.body().trim());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                tongTienInterface.thatBai();
            }
        });

        Call<String> callbackTienNhap=dataservice.tinhTongTienNhap(thang,nam);
        callbackTienNhap.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                tongTienInterface.layTienNhapThanhCong(response.body().trim());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                tongTienInterface.thatBai();
            }
        });
    }

    public void xuLyBanNhieu(String thang,String nam){
        arrSPBanNhieu=new ArrayList<>();
        Call<List<SanPhamThongKe>> callback=dataservice.sanPhamBanNhieu(thang,nam);
        callback.enqueue(new Callback<List<SanPhamThongKe>>() {
            @Override
            public void onResponse(Call<List<SanPhamThongKe>> call, Response<List<SanPhamThongKe>> response) {
                arrSPBanNhieu=response.body();
                tongTienInterface.layDSBanNhieu();
            }

            @Override
            public void onFailure(Call<List<SanPhamThongKe>> call, Throwable t) {
                tongTienInterface.thatBai();
            }
        });


        arrSPBanIt=new ArrayList<>();
        Call<List<SanPhamThongKe>> callback2=dataservice.sanPhamBanIt(thang,nam);
        callback2.enqueue(new Callback<List<SanPhamThongKe>>() {
            @Override
            public void onResponse(Call<List<SanPhamThongKe>> call, Response<List<SanPhamThongKe>> response) {
                arrSPBanIt=response.body();
                tongTienInterface.layDSBanIt();
            }

            @Override
            public void onFailure(Call<List<SanPhamThongKe>> call, Throwable t) {
                tongTienInterface.thatBai();
            }
        });
    }

    public interface TongTienInterface{
        public void layTienBanThanhCong(String tienBan);
        public void layTienNhapThanhCong(String tienBan);
        public void layDSBanNhieu();
        public void layDSBanIt();
        public void thatBai();
    }
}
