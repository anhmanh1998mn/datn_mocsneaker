package com.example.manhvan.datn_mocsneaker.ui.fragmentsanpham;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.manhvan.datn_mocsneaker.Model.MoSanPhamXemThemTatCa;
import com.example.manhvan.datn_mocsneaker.Presenter.PreSanPhamXemThemTatCa;
import com.example.manhvan.datn_mocsneaker.R;
import com.example.manhvan.datn_mocsneaker.View.SanPhamXemThemInterface;
import com.example.manhvan.datn_mocsneaker.adapter.XemThemSanPhamAdapter;

public class FragmentSanPhamNoiBat extends Fragment implements SanPhamXemThemInterface{
    private RecyclerView recyclerView;
    private XemThemSanPhamAdapter adapter;
    private PreSanPhamXemThemTatCa preSanPhamXemThemTatCa;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_san_pham_noi_bat,container,false);
        initView(view);
        getData();
        return view;
    }
    private void getData() {
        preSanPhamXemThemTatCa=new PreSanPhamXemThemTatCa(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                preSanPhamXemThemTatCa.sanPhamXemThemTatCa(3);
            }
        }).start();
    }

    private void initView(View view) {
        recyclerView=view.findViewById(R.id.frag_giayNoiBat);

    }

    @Override
    public void onSuccessed() {
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),2);
                recyclerView.setLayoutManager(gridLayoutManager);
                adapter=new XemThemSanPhamAdapter(getActivity(),R.layout.itemxemthemsanpham, MoSanPhamXemThemTatCa.lst);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public void onFailed() {

    }
}
