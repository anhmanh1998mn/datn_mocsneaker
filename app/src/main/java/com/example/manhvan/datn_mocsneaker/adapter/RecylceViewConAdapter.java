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
import com.example.manhvan.datn_mocsneaker.View.MainProductDetail;
import com.example.manhvan.datn_mocsneaker.entity.SanPhamMoi;

import java.text.DecimalFormat;
import java.util.List;

public class RecylceViewConAdapter extends RecyclerView.Adapter<RecylceViewConAdapter.ViewHolder> {
    private Activity myContext;
    private int myLayout;
    private List<SanPhamMoi> lst;

    public RecylceViewConAdapter(Activity myContext, int myLayout, List<SanPhamMoi> lst) {
        this.myContext = myContext;
        this.myLayout = myLayout;
        this.lst = lst;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=(LayoutInflater)myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(myLayout,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i1) {

        Glide.with(myContext).load("http://192.168.42.44"+lst.get(i1).getProductUrl()).into(viewHolder.imageView);
//        Glide.with(myContext).load("http://192.168.1.63:8080"+lst.get(i1).getProductUrl()).into(viewHolder.imageView);
        viewHolder.txtTen.setText(lst.get(i1).getProductName());
        DecimalFormat formater=new DecimalFormat("###,###,###");
        viewHolder.txtGia.setText(formater.format(Integer.parseInt(lst.get(i1).getPriceOut()))+" Vnđ");
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(myContext,MainProductDetail.class);
                Bundle bundle=new Bundle();
                bundle.putString("idAnh",(lst.get(i1).getId()));
                bundle.putString("content",lst.get(i1).getProductName());
                bundle.putString("name",lst.get(i1).getProductName());
                bundle.putString("price",lst.get(i1).getPriceOut());
                bundle.putString("content1",lst.get(i1).getProductContent());
                intent.putExtra("ProductInfo",bundle);
                //intent.putExtra("name",lst.get(i1).getProductName());
                myContext.startActivity(intent);
                //Toast.makeText(myContext,lst.get(i1).getProductName(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return lst.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTen,txtGia;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTen=itemView.findViewById(R.id.txt_tenrclviewcon);
            txtGia=itemView.findViewById(R.id.txt_giarclviewcon);
            imageView=itemView.findViewById(R.id.img_rclcon);
        }
    }
}
