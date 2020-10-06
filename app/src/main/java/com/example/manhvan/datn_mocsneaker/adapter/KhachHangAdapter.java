package com.example.manhvan.datn_mocsneaker.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.manhvan.datn_mocsneaker.R;
import com.example.manhvan.datn_mocsneaker.View.MainChiTietKhachHang;
import com.example.manhvan.datn_mocsneaker.entity.KhachHang;
import com.example.manhvan.datn_mocsneaker.util.AndroidDeviceHelper;

import java.util.List;

public class KhachHangAdapter extends RecyclerView.Adapter<KhachHangAdapter.ViewHolder> {
    private Activity myContext;
    private int myLayout;
    private List<KhachHang> khachHangList;

    public KhachHangAdapter(Activity myContext, int myLayout, List<KhachHang> khachHangList) {
        this.myContext = myContext;
        this.myLayout = myLayout;
        this.khachHangList = khachHangList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=(LayoutInflater)myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(myLayout,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.txt1.getLayoutParams().width= AndroidDeviceHelper.getWithScreen(myContext)/5;
        viewHolder.txt2.getLayoutParams().width= AndroidDeviceHelper.getWithScreen(myContext)/5*2;
        viewHolder.txt3.getLayoutParams().width= AndroidDeviceHelper.getWithScreen(myContext)/5*2;
        viewHolder.txt1.setText(khachHangList.get(i).getId());
        viewHolder.txt2.setText(khachHangList.get(i).getCustomerName());
        viewHolder.txt3.setText(khachHangList.get(i).getCustomerPhone());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(myContext, MainChiTietKhachHang.class);
                Bundle bundle=new Bundle();
                bundle.putString("hoTen",khachHangList.get(i).getCustomerName());
                bundle.putString("soDT",khachHangList.get(i).getCustomerPhone());
                bundle.putString("diaChi",khachHangList.get(i).getCustomerAddress());
                intent.putExtra("thongTin",bundle);
                myContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return khachHangList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txt1,txt2,txt3;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt1=itemView.findViewById(R.id.item_qlkhID);
            txt2=itemView.findViewById(R.id.item_qlkhHT);
            txt3=itemView.findViewById(R.id.item_qlkhTK);
        }
    }
}
