package com.example.manhvan.datn_mocsneaker.Model;

import com.example.manhvan.datn_mocsneaker.MyService.APIService;
import com.example.manhvan.datn_mocsneaker.MyService.Dataservice;
import com.example.manhvan.datn_mocsneaker.entity.NamThongKe;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoThongKeTheoNam {
    private ThongKeNamInterface thongKeNamInterface;

    public MoThongKeTheoNam(ThongKeNamInterface thongKeNamInterface) {
        this.thongKeNamInterface = thongKeNamInterface;
    }

    public static List<NamThongKe> arrBan,arrNhap;
    private Dataservice dataservice= APIService.getService();
    public void xuLyBan(String nam){
        arrBan=new ArrayList<>();
        Call<List<NamThongKe>> callback1=dataservice.thongKeBan(nam);
        callback1.enqueue(new Callback<List<NamThongKe>>() {
            @Override
            public void onResponse(Call<List<NamThongKe>> call, Response<List<NamThongKe>> response) {
                arrBan=response.body();
                thongKeNamInterface.thongKeBan();
            }

            @Override
            public void onFailure(Call<List<NamThongKe>> call, Throwable t) {

            }
        });

        arrNhap=new ArrayList<>();
        Call<List<NamThongKe>> callback2=dataservice.thongKeNhap(nam);
        callback2.enqueue(new Callback<List<NamThongKe>>() {
            @Override
            public void onResponse(Call<List<NamThongKe>> call, Response<List<NamThongKe>> response) {
                arrNhap=response.body();
                thongKeNamInterface.thongKeNhap();
            }

            @Override
            public void onFailure(Call<List<NamThongKe>> call, Throwable t) {

            }
        });
    }

    public interface ThongKeNamInterface{
        public void thongKeBan();
        public void thongKeNhap();
    }
}
