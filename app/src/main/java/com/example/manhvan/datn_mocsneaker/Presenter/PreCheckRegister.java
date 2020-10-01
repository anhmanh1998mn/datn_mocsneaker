package com.example.manhvan.datn_mocsneaker.Presenter;

import com.example.manhvan.datn_mocsneaker.Model.MoCheckRegister;
import com.example.manhvan.datn_mocsneaker.Model.TimKiemNVKQ1;
import com.example.manhvan.datn_mocsneaker.View.CheckRegister;

public class PreCheckRegister implements TimKiemNVKQ1 {
    private CheckRegister checkRegister;

    public PreCheckRegister(CheckRegister checkRegister) {
        this.checkRegister = checkRegister;
    }

    private MoCheckRegister moCheckRegister=new MoCheckRegister(this);
    public void checkRegister(String userName,String phone,String fullName,String address,String userPassword){
        if (userName.equals("")||phone.equals("")||fullName.equals("")||address.equals("")||userPassword.equals("")){
            checkRegister.checkNull();
            return;
        }
        moCheckRegister.xuLy(phone);
    }

    @Override
    public void onS() {
        checkRegister.tonTai();
    }

    @Override
    public void onF() {
        checkRegister.phoneNotRegister();
    }
}
