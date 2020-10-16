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

import com.example.manhvan.datn_mocsneaker.Model.MoLayDanhSachDonNH;
import com.example.manhvan.datn_mocsneaker.Presenter.PreLayDanhSachDonNH;
import com.example.manhvan.datn_mocsneaker.R;
import com.example.manhvan.datn_mocsneaker.View.LayDanhSachDonNhapInter;
import com.example.manhvan.datn_mocsneaker.adapter.DanhSachDonNhapAdapter;

public class FragmentKhongDuyet extends Fragment implements LayDanhSachDonNhapInter {
    private PreLayDanhSachDonNH preLayDanhSachDonNH;
    private RecyclerView recyclerViewkhongDuyet;
    private DanhSachDonNhapAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_khong_duyet_nh,container,false);
        initView(view);
        getData();
        return view;
    }
    @Override
    public void onStart() {
        getData();
        super.onStart();
    }
    private void getData() {
        preLayDanhSachDonNH=new PreLayDanhSachDonNH(this);
        new Thread(new Runnable() {
            @Override
            public void run() {

                preLayDanhSachDonNH.layDanhSachDOnNhap(2);
            }
        }).start();
    }

    private void initView(View view) {
        recyclerViewkhongDuyet=view.findViewById(R.id.recycleview_donkhongduyet);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(), LinearLayout.VERTICAL,false);
        recyclerViewkhongDuyet.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onResume() {
        danhSachChuaDuyet();
        super.onResume();
    }

    @Override
    public void danhSachChuaDuyet() {
        recyclerViewkhongDuyet.post(new Runnable() {
            @Override
            public void run() {
                adapter=new DanhSachDonNhapAdapter(getActivity(),R.layout.item_don_nhap_hang_ds, MoLayDanhSachDonNH.arrDonKhongNhap);
                recyclerViewkhongDuyet.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onFailed() {

    }
}
