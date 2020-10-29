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

    public void themMoiAnhSPChiTiet(String realpath,int maSP){
        moThemMoiSanPham.xuLyUpLoadCTAnh(realpath,maSP);
    }

    public void themMoiSanPham2(int maNV,String tenSP,String noiDung,int giaBan,String path){
        moThemMoiSanPham.xuLy2(maNV,tenSP,noiDung,giaBan,path);
    }
    public void themSoLuongSize(int maSP,int size39,int size40,int size41,int size42,int size43){
        moThemMoiSanPham.themSoLuongSP(maSP,size39,size40,size41,size42,size43);
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
    public void onS1(int maSP) {
        ketQuaThemMoiSPInterface.onSuc1(maSP);
    }

    @Override
    public void themTC() {
        ketQuaThemMoiSPInterface.themThanhCong();
    }

    @Override
    public void themSize() {
        ketQuaThemMoiSPInterface.themSizeThanhCong();
    }

    public interface KetQuaThemMoiSPInterface{
        public void onSuccessed(String path);
        public void onFailed();
        public void onSuc1(int maSP);
        public void themThanhCong();
        public void themSizeThanhCong();
    }
}
