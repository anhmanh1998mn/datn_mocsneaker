package com.example.manhvan.datn_mocsneaker.Presenter;

import android.util.Log;

import com.example.manhvan.datn_mocsneaker.Model.MoSanPhamMoiNhat;
import com.example.manhvan.datn_mocsneaker.Model.SanPhamMoiKQ1;
import com.example.manhvan.datn_mocsneaker.View.SanPhamMoiKQ2;
import com.example.manhvan.datn_mocsneaker.entity.SanPhamMoi;

import java.util.ArrayList;

public class PreSanPhamMoiNhat implements SanPhamMoiKQ1{
    private SanPhamMoiKQ2 sanPhamMoiKQ2;

    public PreSanPhamMoiNhat(SanPhamMoiKQ2 sanPhamMoiKQ2) {
        this.sanPhamMoiKQ2 = sanPhamMoiKQ2;
    }

    private MoSanPhamMoiNhat moSanPhamMoiNhat=new MoSanPhamMoiNhat(this);
    public void SanPhamMoiNhat(){
        moSanPhamMoiNhat.XuLy();
    }

    @Override
    public void onS(ArrayList<SanPhamMoi> arrayList) {
        sanPhamMoiKQ2.onSuccess(arrayList);

    }

    @Override
    public void onF() {
        sanPhamMoiKQ2.onFailed();
    }
}
