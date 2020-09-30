package com.example.manhvan.datn_mocsneaker.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.manhvan.datn_mocsneaker.R;
import com.example.manhvan.datn_mocsneaker.entity.ProductImage;
import com.example.manhvan.datn_mocsneaker.util.AndroidDeviceHelper;

import java.util.List;

public class ProductImageAdapter extends RecyclerView.Adapter<ProductImageAdapter.ViewHolder> {
    private Activity myContext;
    private int myLayout;
    private List<ProductImage> lstImage;

    public ProductImageAdapter(Activity myContext, int myLayout, List<ProductImage> lstImage) {
        this.myContext = myContext;
        this.myLayout = myLayout;
        this.lstImage = lstImage;
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
        viewHolder.imageView.getLayoutParams().width= AndroidDeviceHelper.getWithScreen(myContext);
        viewHolder.imageView.requestLayout();
        Glide.with(myContext).load("http://192.168.42.44"+lstImage.get(i).getImageUrl()).into(viewHolder.imageView);
        viewHolder.textView.setText(i+1+"/5");
    }

    @Override
    public int getItemCount() {
        return lstImage.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.img_itemproductdetail);
            textView=itemView.findViewById(R.id.txt_itemproDetail);
        }
    }
}
