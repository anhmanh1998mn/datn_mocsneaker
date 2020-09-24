package com.example.manhvan.datn_mocsneaker.Model;

import com.example.manhvan.datn_mocsneaker.MyService.APIService;
import com.example.manhvan.datn_mocsneaker.MyService.Dataservice;
import com.example.manhvan.datn_mocsneaker.entity.SanPhamMoi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoSanPhamMoiNhat {
    private SanPhamMoiKQ1 sanPhamMoiKQ1;

    public MoSanPhamMoiNhat(SanPhamMoiKQ1 sanPhamMoiKQ1) {
        this.sanPhamMoiKQ1 = sanPhamMoiKQ1;
    }

    public ArrayList<SanPhamMoi> arrayList;
    public void XuLy(){
        arrayList=new ArrayList<>();
        Dataservice dataservice= APIService.getService();
        Call<List<SanPhamMoi>> callback=dataservice.GetSPMoi();
        callback.enqueue(new Callback<List<SanPhamMoi>>() {
            @Override
            public void onResponse(Call<List<SanPhamMoi>> call, Response<List<SanPhamMoi>> response) {
                arrayList= (ArrayList<SanPhamMoi>) response.body();
                sanPhamMoiKQ1.onS(arrayList);
            }

            @Override
            public void onFailure(Call<List<SanPhamMoi>> call, Throwable t) {
                sanPhamMoiKQ1.onF();
            }
        });
    }
}
