package com.example.manhvan.datn_mocsneaker.ui.fragmentNhapHangDS;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.manhvan.datn_mocsneaker.R;

public class FragmentKhongDuyet extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_khong_duyet_nh,container,false);
        return view;
    }
}