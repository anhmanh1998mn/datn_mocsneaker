package com.example.manhvan.datn_mocsneaker.ui.fragmentKHShowList;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.manhvan.datn_mocsneaker.R;

public class FragmentDangGiao extends Fragment {
    private RecyclerView recycleDangGiao;
    private SharedPreferences sharedPreferences;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_khdanggiao,container,false);
        initView(view);
        getData();
        return view;
    }

    private void initView(View view) {
        recycleDangGiao=view.findViewById(R.id.recycle_KHDangGiao);
    }
    public void getData(){
        sharedPreferences=getActivity().getSharedPreferences("QuyenTK", Context.MODE_PRIVATE);
//        Toast.makeText(getContext(),sharedPreferences.getInt("maNguoiDung",-1)+"",Toast.LENGTH_SHORT).show();
    }
}
