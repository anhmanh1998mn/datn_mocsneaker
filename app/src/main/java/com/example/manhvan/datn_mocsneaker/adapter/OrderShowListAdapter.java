package com.example.manhvan.datn_mocsneaker.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.manhvan.datn_mocsneaker.R;
import com.example.manhvan.datn_mocsneaker.View.QuanLyDonHang.MainOrderDetail;
import com.example.manhvan.datn_mocsneaker.entity.DonHang;
import com.example.manhvan.datn_mocsneaker.util.AndroidDeviceHelper;

import java.util.List;

public class OrderShowListAdapter extends RecyclerView.Adapter<OrderShowListAdapter.ViewHolder> {
    private Activity myContext;
    private int myLayout;
    private List<DonHang> lstDonHang;

    public OrderShowListAdapter(Activity myContext, int myLayout, List<DonHang> lstDonHang) {
        this.myContext = myContext;
        this.myLayout = myLayout;
        this.lstDonHang = lstDonHang;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=(LayoutInflater)myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(myLayout,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.txtMaDH.getLayoutParams().width= AndroidDeviceHelper.getWithScreen(myContext)/4;
        viewHolder.txtDiaChi.getLayoutParams().width=AndroidDeviceHelper.getWithScreen(myContext)/4*2;
        viewHolder.txtNgayLap.getLayoutParams().width=AndroidDeviceHelper.getWithScreen(myContext)/4;
        viewHolder.txtMaDH.requestLayout();
        viewHolder.txtNgayLap.requestLayout();
        viewHolder.txtDiaChi.requestLayout();

        viewHolder.txtMaDH.setText(lstDonHang.get(i).getId());
        viewHolder.txtDiaChi.setText(lstDonHang.get(i).getAddress());
        viewHolder.txtNgayLap.setText(lstDonHang.get(i).getCreatedAt());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(myContext, MainOrderDetail.class);
                intent.putExtra("ngayLap",lstDonHang.get(i).getCreatedAt());
                intent.putExtra("diaChi",lstDonHang.get(i).getAddress());
                intent.putExtra("maDH",lstDonHang.get(i).getId());
                myContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lstDonHang.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtMaDH,txtDiaChi,txtNgayLap;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaDH=itemView.findViewById(R.id.txt_itemMaDonHang);
            txtDiaChi=itemView.findViewById(R.id.txt_itemDiaChiNhan);
            txtNgayLap=itemView.findViewById(R.id.txt_itemNgayLap);
        }
    }
}
