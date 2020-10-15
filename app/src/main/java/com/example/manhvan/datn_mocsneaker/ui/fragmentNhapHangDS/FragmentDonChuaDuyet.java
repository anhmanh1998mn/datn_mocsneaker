package com.example.manhvan.datn_mocsneaker.ui.fragmentNhapHangDS;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.manhvan.datn_mocsneaker.R;

public class FragmentDonChuaDuyet extends Fragment {
    private RecyclerView recyclerViewChuDuyet;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_don_chua_duyet_nh,container,false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        recyclerViewChuDuyet=view.findViewById(R.id.recycleview_donchuaduyet);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(), LinearLayout.VERTICAL,false);
        recyclerViewChuDuyet.setLayoutManager(linearLayoutManager);
    }
}
