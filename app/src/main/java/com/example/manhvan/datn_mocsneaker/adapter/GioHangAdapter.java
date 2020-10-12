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
import com.example.manhvan.datn_mocsneaker.entity.GioHang1;
import com.example.manhvan.datn_mocsneaker.util.AndroidDeviceHelper;

import java.text.DecimalFormat;
import java.util.List;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.ViewHolder> {
    private Activity myContext;
    private int myLayout;
    private List<GioHang1> lst;

    public GioHangAdapter(Activity myContext, int myLayout, List<GioHang1> lst) {
        this.myContext = myContext;
        this.myLayout = myLayout;
        this.lst = lst;
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
        viewHolder.img.getLayoutParams().width= AndroidDeviceHelper.getWithScreen(myContext)/3;
        viewHolder.img.requestLayout();
        viewHolder.linearLayout.getLayoutParams().width=AndroidDeviceHelper.getWithScreen(myContext);
        viewHolder.linearLayout.requestLayout();
        viewHolder.txt2.getLayoutParams().width=AndroidDeviceHelper.getWithScreen(myContext)/4;
        viewHolder.txt2.requestLayout();
        viewHolder.txt5.getLayoutParams().width=AndroidDeviceHelper.getWithScreen(myContext)/4*3;
        viewHolder.txt5.requestLayout();
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        viewHolder.txt1.setText(lst.get(i).getTenSP());
        viewHolder.txt2.setText(lst.get(i).getSoLuong()+"");
        viewHolder.txt3.setText(lst.get(i).getKichCo());
        viewHolder.txt4.setText(decimalFormat.format(lst.get(i).getDonGia())+" đ");
        viewHolder.txt5.setText(decimalFormat.format(lst.get(i).getDonGia()*lst.get(i).getSoLuong())+" đ");
//        Glide.with(myContext).load("http://192.168.89.1:8080"+lst.get(i).getDuongDan()).into(viewHolder.img);
        Glide.with(myContext).load("http://192.168.42.44"+lst.get(i).getDuongDan()).into(viewHolder.img);
    }

    @Override
    public int getItemCount() {
        return lst.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView txt1,txt2,txt3,txt4,txt5;
        private LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img_gioHang);
            txt1=itemView.findViewById(R.id.txt_tenSPGH);
            txt2=itemView.findViewById(R.id.txt_soLuongMuaGH);
            txt3=itemView.findViewById(R.id.txt_kichCoMuaGH);
            txt4=itemView.findViewById(R.id.txt_donGiaMuaGH);
            txt5=itemView.findViewById(R.id.txt_thanhTien);
            linearLayout=itemView.findViewById(R.id.lngohang);
        }
    }
}
