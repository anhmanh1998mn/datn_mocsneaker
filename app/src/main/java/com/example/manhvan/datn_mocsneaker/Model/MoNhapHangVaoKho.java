package com.example.manhvan.datn_mocsneaker.Model;

import com.example.manhvan.datn_mocsneaker.MyService.APIService;
import com.example.manhvan.datn_mocsneaker.MyService.Dataservice;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoNhapHangVaoKho {
    private MoNhapHangVaoKhoInterface moNhapHangVaoKhoInterface;

    public MoNhapHangVaoKho(MoNhapHangVaoKhoInterface moNhapHangVaoKhoInterface) {
        this.moNhapHangVaoKhoInterface = moNhapHangVaoKhoInterface;
    }

    public void xuLy(){
        Dataservice dataservice= APIService.getService();
        for (int i=0;i<MoLayChiTietDonNhap.arrListChiTiet.size();i++){
            Call<String> callback=dataservice.nhapHangHopSL(Integer.parseInt(MoLayChiTietDonNhap.arrListChiTiet.get(i).getQuantity()),
                    MoLayChiTietDonNhap.arrListChiTiet.get(i).getSize().equals("39")?1:MoLayChiTietDonNhap.arrListChiTiet.get(i).getSize().equals("40")?
                    2:MoLayChiTietDonNhap.arrListChiTiet.get(i).getSize().equals("41")?3:MoLayChiTietDonNhap.arrListChiTiet.get(i).getSize().equals("42")?4:5,
                    Integer.parseInt(MoLayChiTietDonNhap.arrListChiTiet.get(i).getProductId()));
            callback.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if(response.body().toString().trim().equals("thanhcong")){
                        moNhapHangVaoKhoInterface.onKhoS();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                }
            });
        }
    }
    public interface MoNhapHangVaoKhoInterface{
        public void onKhoS();
    }
}
