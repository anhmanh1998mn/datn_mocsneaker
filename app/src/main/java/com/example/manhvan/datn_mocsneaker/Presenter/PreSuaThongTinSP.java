package com.example.manhvan.datn_mocsneaker.Presenter;

import com.example.manhvan.datn_mocsneaker.Model.MoSuaThongTinSP;

public class PreSuaThongTinSP implements MoSuaThongTinSP.OnResultUpdatedProInterface {
    private OnResultSuccessInterface onResultSuccessInterfacel;

    public PreSuaThongTinSP(OnResultSuccessInterface onResultSuccessInterfacel) {
        this.onResultSuccessInterfacel = onResultSuccessInterfacel;
    }

    private MoSuaThongTinSP moSuaThongTinSP=new MoSuaThongTinSP(this);
    public void suaThongTinSP(int proID,int sizeID,String proName,String proContent,int priceOut,int stock){
        if(proName.equals("")||proContent.equals("")||priceOut<0||stock<0){

            return;
        }
        moSuaThongTinSP.xuLy(proID,sizeID,proName,proContent,priceOut,stock);
    }

    @Override
    public void onS() {
        onResultSuccessInterfacel.onSuccessed();
    }

    @Override
    public void onF() {
        onResultSuccessInterfacel.onFailed();
    }
    public interface OnResultSuccessInterface{
        public void onSuccessed();
        public void onFailed();
    }
}
