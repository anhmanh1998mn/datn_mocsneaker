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
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.manhvan.datn_mocsneaker.R;
import com.example.manhvan.datn_mocsneaker.View.QuanLySanPham.MainSuaSanPham;
import com.example.manhvan.datn_mocsneaker.entity.GioHang1;
import com.example.manhvan.datn_mocsneaker.entity.SanPhamMoi;
import com.example.manhvan.datn_mocsneaker.util.DuongDan;
import com.example.manhvan.datn_mocsneaker.util.GioHang;

import java.text.DecimalFormat;
import java.util.List;

public class QLTimKiemSanPhamAdapter extends RecyclerView.Adapter<QLTimKiemSanPhamAdapter.ViewHolder> {
    private Activity myContext;
    private int myLayout;
    List<SanPhamMoi> lstTimKiem;

    public QLTimKiemSanPhamAdapter(Activity myContext, int myLayout, List<SanPhamMoi> lstTimKiem) {
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
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        viewHolder.txt2.setText(decimalFormat.format(Integer.parseInt(lstTimKiem.get(i).getPriceOut()))+"đ");
        Glide.with(myContext).load(DuongDan.url+lstTimKiem.get(i).getProductUrl()).into(viewHolder.img1);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(myContext, MainSuaSanPham.class);
                Bundle bundle=new Bundle();
                bundle.putString("idProduct",lstTimKiem.get(i).getId());
                bundle.putString("image", DuongDan.url+lstTimKiem.get(i).getProductUrl());
                bundle.putString("productName",lstTimKiem.get(i).getProductName());
                bundle.putString("productPrice",lstTimKiem.get(i).getPriceOut());
                bundle.putString("productContent",lstTimKiem.get(i).getProductContent());
                intent.putExtra("info",bundle);
                myContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lstTimKiem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txt1,txt2;
        private ImageView img1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt1=itemView.findViewById(R.id.txt_itemtimkiemSP);
            txt2=itemView.findViewById(R.id.txt_donGiaMuaGH1);
            img1=itemView.findViewById(R.id.img_qlsanpham);
        }
    }
}
