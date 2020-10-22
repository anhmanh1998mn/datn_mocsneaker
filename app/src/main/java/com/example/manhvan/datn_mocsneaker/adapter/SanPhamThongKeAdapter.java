package com.example.manhvan.datn_mocsneaker.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.manhvan.datn_mocsneaker.R;
import com.example.manhvan.datn_mocsneaker.entity.SanPhamThongKe;
import com.example.manhvan.datn_mocsneaker.util.AndroidDeviceHelper;
import com.example.manhvan.datn_mocsneaker.util.DuongDan;

import java.util.List;

public class SanPhamThongKeAdapter extends RecyclerView.Adapter<SanPhamThongKeAdapter.ViewHolder> {
    private Activity myContext;
    private int myLayout;
    private List<SanPhamThongKe> lstThongKe;

    public SanPhamThongKeAdapter(Activity myContext, int myLayout, List<SanPhamThongKe> lstThongKe) {
        this.myContext = myContext;
        this.myLayout = myLayout;
        this.lstThongKe = lstThongKe;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater= (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(myLayout,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.imgAnh.getLayoutParams().width= AndroidDeviceHelper.getWithScreen(myContext)/4;
        viewHolder.imgAnh.requestLayout();
        viewHolder.linearLayout.getLayoutParams().width=AndroidDeviceHelper.getWithScreen(myContext)/4*3;
        viewHolder.linearLayout.requestLayout();

        Glide.with(myContext).load(DuongDan.url+lstThongKe.get(i).getProductUrl()).into(viewHolder.imgAnh);
        viewHolder.txtMa.setText("Mã sản phẩm: "+lstThongKe.get(i).getId());
        viewHolder.txtTen.setText("Tên: "+lstThongKe.get(i).getProductName());
        viewHolder.txtTongBan.setText("Số lượng đã bán: "+lstThongKe.get(i).getTong());
    }

    @Override
    public int getItemCount() {
        return lstThongKe.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgAnh;
        LinearLayout linearLayout;
        TextView txtMa,txtTen,txtTongBan;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAnh=itemView.findViewById(R.id.img_itemThongKe);
            txtMa=itemView.findViewById(R.id.txt_itemMaSP);
            txtTen=itemView.findViewById(R.id.txt_itemTenSP);
            txtTongBan=itemView.findViewById(R.id.txt_itemSoLuongBan);
            linearLayout=itemView.findViewById(R.id.linealt);
        }
    }
}
