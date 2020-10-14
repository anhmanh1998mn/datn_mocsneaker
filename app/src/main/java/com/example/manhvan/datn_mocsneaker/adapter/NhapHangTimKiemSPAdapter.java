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
import com.example.manhvan.datn_mocsneaker.View.MainProductInsert;
import com.example.manhvan.datn_mocsneaker.entity.SanPhamMoi;

import java.util.List;

public class NhapHangTimKiemSPAdapter extends RecyclerView.Adapter<NhapHangTimKiemSPAdapter.ViewHolder> {
    private Activity myContext;
    private int myLayout;
    List<SanPhamMoi> lstTimKiem;

    public NhapHangTimKiemSPAdapter(Activity myContext, int myLayout, List<SanPhamMoi> lstTimKiem) {
        this.myContext = myContext;
        this.myLayout = myLayout;
        this.lstTimKiem = lstTimKiem;
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
        viewHolder.txt1.setText(lstTimKiem.get(i).getProductName());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(myContext, MainProductInsert.class);
                Bundle bundle=new Bundle();
                bundle.putString("productID",lstTimKiem.get(i).getId());
                bundle.putString("productName",lstTimKiem.get(i).getProductName());
                bundle.putString("productURL", "http://192.168.42.44"+lstTimKiem.get(i).getProductUrl());
                intent.putExtra("ProductInfo",bundle);
                myContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lstTimKiem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txt1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt1=itemView.findViewById(R.id.txt_itemtimkiemSP);
        }
    }
}
