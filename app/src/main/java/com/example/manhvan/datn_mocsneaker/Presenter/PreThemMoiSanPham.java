package com.example.manhvan.datn_mocsneaker.Presenter;

import com.example.manhvan.datn_mocsneaker.Model.MoThemMoiSanPham;

public class PreThemMoiSanPham implements MoThemMoiSanPham.ThemMoiSPInterface {
    private KetQuaThemMoiSPInterface ketQuaThemMoiSPInterface;

    public PreThemMoiSanPham(KetQuaThemMoiSPInterface ketQuaThemMoiSPInterface) {
        this.ketQuaThemMoiSPInterface = ketQuaThemMoiSPInterface;
    }

    private MoThemMoiSanPham moThemMoiSanPham=new MoThemMoiSanPham(this);
    public void themMoiSP(String realpath){
        moThemMoiSanPham.xuLyUpLoad(realpath);
    }

    @Override
    public void onS() {
        ketQuaThemMoiSPInterface.onSuccessed();
    }

    @Override
    public void onF() {
        ketQuaThemMoiSPInterface.onFailed();
    }

    public interface KetQuaThemMoiSPInterface{
        public void onSuccessed();
        public void onFailed();
    }
}
