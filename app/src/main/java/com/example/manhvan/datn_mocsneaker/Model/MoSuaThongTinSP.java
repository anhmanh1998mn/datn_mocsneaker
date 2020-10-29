package com.example.manhvan.datn_mocsneaker.Model;

import android.util.Log;

import com.example.manhvan.datn_mocsneaker.MyService.APIService;
import com.example.manhvan.datn_mocsneaker.MyService.Dataservice;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoSuaThongTinSP {
    private OnResultUpdatedProInterface onResultUpdatedProInterface;

    public MoSuaThongTinSP(OnResultUpdatedProInterface onResultUpdatedProInterface) {
        this.onResultUpdatedProInterface = onResultUpdatedProInterface;
    }

    public void xuLy(int proID, int sizeID, String proName, String proContent, int priceOut, int stock){
        Dataservice dataservice= APIService.getService();
        Call<String> callback=dataservice.updateProductInfo(proID,sizeID,proName,proContent,priceOut,stock);
        callback.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d("ketQua",response.body());
                if(response.body().toString().equals("thanhcong")){
                    onResultUpdatedProInterface.onS();
                    return;
                }
                onResultUpdatedProInterface.onF();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
    public interface OnResultUpdatedProInterface{
        public void onS();
        public void onF();
    }
}
