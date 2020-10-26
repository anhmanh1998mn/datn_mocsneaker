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
    public void themMoiSanPham2(int maNV,String tenSP,String noiDung,int giaBan,int sl39,
                                int sl40,int sl41,int sl42,int sl43,String path){
        moThemMoiSanPham.xuLy2(maNV,tenSP,noiDung,giaBan,sl39,sl40,sl41,sl42,sl43,path);
    }

    @Override
    public void onS(String path) {
        ketQuaThemMoiSPInterface.onSuccessed(path);
    }

    @Override
    public void onF() {
        ketQuaThemMoiSPInterface.onFailed();
    }

    @Override
    public void onS1() {
        ketQuaThemMoiSPInterface.onSuc1();
    }

    public interface KetQuaThemMoiSPInterface{
        public void onSuccessed(String path);
        public void onFailed();
        public void onSuc1();
    }
}
