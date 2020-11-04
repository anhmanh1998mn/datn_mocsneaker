package com.example.manhvan.datn_mocsneaker.Model;

import android.util.Log;

import com.example.manhvan.datn_mocsneaker.MyService.APIService;
import com.example.manhvan.datn_mocsneaker.MyService.Dataservice;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MohuyHangTangSoLuong {
    private HuyTagSLInter huyTagSLInter;

    public MohuyHangTangSoLuong(HuyTagSLInter huyTagSLInter) {
        this.huyTagSLInter = huyTagSLInter;
    }

    public void xuLy(){
        Dataservice dataservice= APIService.getService();
        for (int i=0;i<MoOrderDetail.lstOrderDetail.size();i++){
            Call<String> callback=dataservice.nhapHangHopSL(Integer.parseInt(MoOrderDetail.lstOrderDetail.get(i).getQuantity()),
                    MoOrderDetail.lstOrderDetail.get(i).getSize().equals("39")?1:MoOrderDetail.lstOrderDetail.get(i).getSize().equals("40")?
                            2:MoOrderDetail.lstOrderDetail.get(i).getSize().equals("41")?3:MoOrderDetail.lstOrderDetail.get(i).getSize().equals("42")?4:5,
                    Integer.parseInt(MoOrderDetail.lstOrderDetail.get(i).getMaSP()));
            callback.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if(response.body().toString().trim().equals("thanhcong")){
                        huyTagSLInter.onS();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Log.d("Fail",t.toString());
                }
            });
        }

    }
    public interface HuyTagSLInter{
        public void onS();
    }
}
