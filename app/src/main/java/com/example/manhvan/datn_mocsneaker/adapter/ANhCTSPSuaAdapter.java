package com.example.manhvan.datn_mocsneaker.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.manhvan.datn_mocsneaker.R;
import com.example.manhvan.datn_mocsneaker.entity.ProductImage;
import com.example.manhvan.datn_mocsneaker.util.DuongDan;

import java.util.List;

public class ANhCTSPSuaAdapter extends RecyclerView.Adapter<ANhCTSPSuaAdapter.ViewHolder> {
    private Activity myContext;
    private int myLayout;
    private List<ProductImage> lstSPSua;

    public ANhCTSPSuaAdapter(Activity myContext, int myLayout, List<ProductImage> lstSPSua) {
        this.myContext = myContext;
        this.myLayout = myLayout;
        this.lstSPSua = lstSPSua;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=(LayoutInflater)myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(myLayout,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Glide.with(myContext).load(DuongDan.url+lstSPSua.get(i).getImageUrl()).into(viewHolder.imgView);
    }

    @Override
    public int getItemCount() {
        return lstSPSua.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgView=itemView.findViewById(R.id.img_itemSua);
        }
    }
}
