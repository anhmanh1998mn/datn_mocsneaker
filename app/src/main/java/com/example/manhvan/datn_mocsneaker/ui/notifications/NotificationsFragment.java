package com.example.manhvan.datn_mocsneaker.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.manhvan.datn_mocsneaker.R;
import com.example.manhvan.datn_mocsneaker.View.MainLogin;
import com.example.manhvan.datn_mocsneaker.View.MainRegister;
import com.example.manhvan.datn_mocsneaker.View.MainUserInfo;

public class NotificationsFragment extends Fragment implements View.OnClickListener {
    private Button btnNotiLogin,btnNotiRegister,btnNotiSetting;

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
        EvenClick();
        return root;
    }

    private void EvenClick() {
        btnNotiLogin.setOnClickListener(this);
        btnNotiRegister.setOnClickListener(this);
        btnNotiSetting.setOnClickListener(this);
    }

    private void initView(View root) {
        btnNotiLogin=root.findViewById(R.id.btn_notilogin);
        btnNotiRegister=root.findViewById(R.id.btn_notiRegister);
        btnNotiSetting=root.findViewById(R.id.btn_notiSetting);
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
            }
        }
    }
}