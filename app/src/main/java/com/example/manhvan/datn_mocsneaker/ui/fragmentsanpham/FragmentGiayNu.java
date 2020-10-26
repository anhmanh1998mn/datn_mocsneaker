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

import com.example.manhvan.datn_mocsneaker.Model.MoXemThemSanPham;
import com.example.manhvan.datn_mocsneaker.Presenter.PreXemThemSanPham;
import com.example.manhvan.datn_mocsneaker.R;
import com.example.manhvan.datn_mocsneaker.View.PKInterface.SanPhamXemThemInterface;
import com.example.manhvan.datn_mocsneaker.adapter.XemThemSanPhamAdapter;

public class FragmentGiayNu extends Fragment implements SanPhamXemThemInterface{
    private RecyclerView recyclerView;
    private PreXemThemSanPham xemThemSanPham;
    private XemThemSanPhamAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_giay_nu,container,false);
        initView(view);
        getData();
        return view;
    }

    private void getData() {
        xemThemSanPham=new PreXemThemSanPham(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                xemThemSanPham.xemThemSanPham(2);
            }
        }).start();
    }

    private void initView(View view) {
        recyclerView=view.findViewById(R.id.frag_giayNu);
    }

    @Override
    public void onSuccessed() {
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),2);
                recyclerView.setLayoutManager(gridLayoutManager);
                adapter=new XemThemSanPhamAdapter(getActivity(),R.layout.itemxemthemsanpham, MoXemThemSanPham.lstSanPhamXemThem);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onFailed() {

    }
}
