package com.example.manhvan.datn_mocsneaker.Presenter;

import com.example.manhvan.datn_mocsneaker.Model.MoNhapHangVaoKho;

public class PreNhapHangVaoKho implements MoNhapHangVaoKho.MoNhapHangVaoKhoInterface {
    private NhapKhoInterfaeSucc nhapKhoInterfaeSucc;

    public PreNhapHangVaoKho(NhapKhoInterfaeSucc nhapKhoInterfaeSucc) {
        this.nhapKhoInterfaeSucc = nhapKhoInterfaeSucc;
    }

    private MoNhapHangVaoKho moNhapHangVaoKho=new MoNhapHangVaoKho(this);
    public void nhapHangVaoKho(){
        moNhapHangVaoKho.xuLy();
    }

    @Override
    public void onKhoS() {
        nhapKhoInterfaeSucc.onThanhCOngKho();
    }
    public interface NhapKhoInterfaeSucc{
        public void onThanhCOngKho();
    }
}
