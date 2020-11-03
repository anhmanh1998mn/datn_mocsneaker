package com.example.manhvan.datn_mocsneaker.Presenter;

import com.example.manhvan.datn_mocsneaker.Model.MoInsertOrderAddressCustomer;

public class PreInsertOrderAddessCustomer implements MoInsertOrderAddressCustomer.ThemDiaChiInterface1{
    private ThemDiaCHiInterface2 themDiaCHiInterface2;

    public PreInsertOrderAddessCustomer(ThemDiaCHiInterface2 themDiaCHiInterface2) {
        this.themDiaCHiInterface2 = themDiaCHiInterface2;
    }

    private MoInsertOrderAddressCustomer moInsertOrderAddressCustomer=new MoInsertOrderAddressCustomer(this);
    public void ThemDiaChi(int maKH,String diaChi){
        moInsertOrderAddressCustomer.xuLy(maKH,diaChi);
    }

    @Override
    public void onS() {
        themDiaCHiInterface2.themDiaChiThanhCong();
    }
    public interface ThemDiaCHiInterface2{
        public void themDiaChiThanhCong();
    }
}
