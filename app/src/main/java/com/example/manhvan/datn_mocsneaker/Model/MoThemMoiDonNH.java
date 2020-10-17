package com.example.manhvan.datn_mocsneaker.Model;

import android.util.Log;

import com.example.manhvan.datn_mocsneaker.MyService.APIService;
import com.example.manhvan.datn_mocsneaker.MyService.Dataservice;
import com.example.manhvan.datn_mocsneaker.util.GioHang;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoThemMoiDonNH {
    private ThemNhanVienKQ1 themNhanVienKQ1;
    private int idDonNhap;

    public MoThemMoiDonNH(ThemNhanVienKQ1 themNhanVienKQ1) {
        this.themNhanVienKQ1 = themNhanVienKQ1;
    }

    private Dataservice dataservice= APIService.getService();

    public void xuLy(int idNhanVien) {
        //themNhanVienKQ1.onS();
        Call<String> callback = dataservice.maDonNhap(idNhanVien);
        callback.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.body().trim().equals("")) {
                    themNhanVienKQ1.onF();
                    return;
                }
                idDonNhap = Integer.parseInt(response.body().trim());
                Log.d("LoiThem",idDonNhap+"");
                themChiTietDonNhap();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                themNhanVienKQ1.onF();
//                Log.d("LoiThem",t.toString());
            }
        });
    }

    private void themChiTietDonNhap() {

        for (int i = 0; i < GioHang.arrChiTietDonNhap.size(); i++) {
            Call<String> callback1 = dataservice.chiTietDonNhap(idDonNhap, GioHang.arrChiTietDonNhap.get(i).getIdSanPham(),
                    GioHang.arrChiTietDonNhap.get(i).getSoLuong(), GioHang.arrChiTietDonNhap.get(i).getKichCo());
            callback1.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {

                    themNhanVienKQ1.onS();

                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    themNhanVienKQ1.onF();

                }
            });
        }

    }
}
