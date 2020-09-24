package com.example.manhvan.datn_mocsneaker.ui.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;

import com.example.manhvan.datn_mocsneaker.R;
import com.example.manhvan.datn_mocsneaker.adapter.HomeAdapter;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RecyclerView recyclerViewHome;
    private ArrayList<String> arrayList;
    private HomeAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        initView(root);
        return root;
    }

    private void initView(View view) {
        arrayList=new ArrayList<>();
        arrayList.add("Sản phẩm mới");
        arrayList.add("Sản phẩm nổi bật");
        arrayList.add("Giày nam");
        arrayList.add("Giày nữ");
        arrayList.add("Giày đôi");
        adapter=new HomeAdapter(getActivity(),R.layout.itemrclhome,arrayList);
        recyclerViewHome=view.findViewById(R.id.home_recycleview);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(), LinearLayout.VERTICAL,false);
        recyclerViewHome.setLayoutManager(linearLayoutManager);
        recyclerViewHome.setAdapter(adapter);
    }
}