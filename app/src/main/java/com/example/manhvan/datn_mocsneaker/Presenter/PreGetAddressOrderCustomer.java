package com.example.manhvan.datn_mocsneaker.Presenter;

import com.example.manhvan.datn_mocsneaker.Model.MoGetAddressOrderCustomer;

public class PreGetAddressOrderCustomer implements MoGetAddressOrderCustomer.AddressOrder1Interface{
    private AddressOrder2Interface addressOrder2Interface;

    public PreGetAddressOrderCustomer(AddressOrder2Interface addressOrder2Interface) {
        this.addressOrder2Interface = addressOrder2Interface;
    }

    private MoGetAddressOrderCustomer moGetAddressOrderCustomer=new MoGetAddressOrderCustomer(this);
    public void GetAddressOrder(String phone){
        moGetAddressOrderCustomer.xuLy(phone);
    }

    @Override
    public void onS() {
        addressOrder2Interface.GetAddressSuccess();

    }
    public interface AddressOrder2Interface{
        public void GetAddressSuccess();
    }
}
