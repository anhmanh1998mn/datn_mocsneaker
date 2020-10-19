package com.example.manhvan.datn_mocsneaker.Model;

import com.example.manhvan.datn_mocsneaker.MyService.APIService;
import com.example.manhvan.datn_mocsneaker.MyService.Dataservice;
import com.example.manhvan.datn_mocsneaker.entity.OrderDetail;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoOrderDetail {
    public static List<OrderDetail> lstOrderDetail;
    private DataOrderDetail dataOrderDetail;

    public MoOrderDetail(DataOrderDetail dataOrderDetail) {
        this.dataOrderDetail = dataOrderDetail;
    }

    public void xuLy(int maDH){
        lstOrderDetail=new ArrayList<>();
        Dataservice dataservice= APIService.getService();
        Call<List<OrderDetail>> callback=dataservice.getDataOrderDetail(maDH);
        callback.enqueue(new Callback<List<OrderDetail>>() {
            @Override
            public void onResponse(Call<List<OrderDetail>> call, Response<List<OrderDetail>> response) {
                lstOrderDetail=response.body();
                dataOrderDetail.onS();
            }

            @Override
            public void onFailure(Call<List<OrderDetail>> call, Throwable t) {
                dataOrderDetail.onF();
            }
        });
    }
    public interface DataOrderDetail{
        public void onS();
        public void onF();
    }
}
