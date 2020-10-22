package com.example.manhvan.datn_mocsneaker.ui.notifications;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.manhvan.datn_mocsneaker.Model.MoLayMaNguoiLapDH;
import com.example.manhvan.datn_mocsneaker.Presenter.PreLayMaNguoiLapDH;
import com.example.manhvan.datn_mocsneaker.R;
import com.example.manhvan.datn_mocsneaker.View.MaNguoiLapDHInterface;
import com.example.manhvan.datn_mocsneaker.View.MainLogin;
import com.example.manhvan.datn_mocsneaker.View.MainRegister;
import com.example.manhvan.datn_mocsneaker.View.MainShowListOrdersKH;
import com.example.manhvan.datn_mocsneaker.View.MainTHongTinCuaHang;
import com.example.manhvan.datn_mocsneaker.View.MainUserInfo;

public class NotificationsFragment extends Fragment implements View.OnClickListener, MaNguoiLapDHInterface {
    private Button btnNotiLogin,btnNotiRegister,btnNotiSetting,btnShowOrder,btnThongTinCuaHang;
    private ImageView imageView;
    private TextView txtTen;
    private PreLayMaNguoiLapDH preLayMaNguoiLapDH;
    private int idNguoiLap=0;
    private SharedPreferences sharedPreferences;

    private NotificationsViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        notificationsViewModel =
//                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        //final TextView textView = root.findViewById(R.id.text_notifications);
//        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        initView(root);
        getMaNguoiDung();
        EvenClick();
        return root;
    }

    private void EvenClick() {
        btnNotiLogin.setOnClickListener(this);
        btnNotiRegister.setOnClickListener(this);
        btnNotiSetting.setOnClickListener(this);
        imageView.setOnClickListener(this);
        btnShowOrder.setOnClickListener(this);
        btnThongTinCuaHang.setOnClickListener(this);
    }

    private void initView(View root) {
        btnNotiLogin=root.findViewById(R.id.btn_notilogin);
        btnNotiRegister=root.findViewById(R.id.btn_notiRegister);
        btnNotiSetting=root.findViewById(R.id.btn_notiSetting);
        imageView=root.findViewById(R.id.btn_thongtintaikhoan);
        txtTen=root.findViewById(R.id.txt_ten);
        btnShowOrder=root.findViewById(R.id.btn_notifiDonMua);
        btnThongTinCuaHang=root.findViewById(R.id.btn_thongTinCuaHang);

        // hiện button thiết lập tài khoản, ẩn button đăng nhập, button đăng ký
        sharedPreferences=getActivity().getSharedPreferences("QuyenTK", Context.MODE_PRIVATE);
        if (sharedPreferences.getString("quyen","").equals("")||sharedPreferences.getString("quyen","").isEmpty()){
            return;
        }
        txtTen.setVisibility(View.VISIBLE);
        btnNotiSetting.setVisibility(View.VISIBLE);
        btnNotiLogin.setVisibility(View.GONE);
        btnNotiRegister.setVisibility(View.GONE);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_notilogin:{
                startActivity(new Intent(getContext(), MainLogin.class));
                break;
            }
            case R.id.btn_notiRegister:{
                startActivity(new Intent(getContext(), MainRegister.class));
                break;
            }
            case R.id.btn_notiSetting:{
                startActivity(new Intent(getContext(), MainUserInfo.class));
                break;
            }
            case R.id.btn_notifiDonMua:{
                Intent intent=new Intent(getContext(), MainShowListOrdersKH.class);
                SharedPreferences sharedPreferences=this.getActivity().getSharedPreferences("QuyenTK", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("admin","Admin1");
                editor.commit();
                //intent.putExtra("donMua","1");
                startActivity(intent);
                break;
            }
            case R.id.btn_thongTinCuaHang:{
                startActivity(new Intent(getContext(), MainTHongTinCuaHang.class));
                break;
            }
        }
    }
    public void getMaNguoiDung(){
        sharedPreferences= getActivity().getSharedPreferences("QuyenTK", Context.MODE_PRIVATE);
        if(sharedPreferences.getString("quyen","").equals("")){
            //startActivity(new Intent(ViewGioHang.this, MainLogin.class));
            return;
        }
        preLayMaNguoiLapDH=new PreLayMaNguoiLapDH(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                preLayMaNguoiLapDH.layMaNguoiLap(sharedPreferences.getString("phone",""),Integer.parseInt(sharedPreferences.getString("quyen","")));
            }
        }).start();
    }

    @Override
    public void onSuccedID() {
        idNguoiLap=Integer.parseInt(MoLayMaNguoiLapDH.lstMaNguoiLap.get(0).getId());

        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("maNguoiDung",idNguoiLap);
        editor.commit();
//        btnShowOrder.post(new Runnable() {
//            @Override
//            public void run() {
//                Toast.makeText(getActivity(),idNguoiLap+"",Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public void onFailedID() {

    }

    @Override
    public void onThemThanhCong() {

    }
}