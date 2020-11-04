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
import com.example.manhvan.datn_mocsneaker.entity.OrderDetail;
import com.example.manhvan.datn_mocsneaker.util.AndroidDeviceHelper;
import com.example.manhvan.datn_mocsneaker.util.DuongDan;

import java.text.DecimalFormat;
import java.util.List;

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.ViewHolder> {
    private Activity myContext;
    private int myLayout;
    private List<OrderDetail> lstOrderDetail;

    public OrderDetailAdapter(Activity myContext, int myLayout, List<OrderDetail> lstOrderDetail) {
        this.myContext = myContext;
        this.myLayout = myLayout;
        this.lstOrderDetail = lstOrderDetail;
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
        viewHolder.img.getLayoutParams().width = AndroidDeviceHelper.getWithScreen(myContext) / 3;
        viewHolder.img.getLayoutParams().height = AndroidDeviceHelper.getWithScreen(myContext) / 3;
        viewHolder.img.requestLayout();

        viewHolder.txt2.getLayoutParams().width = AndroidDeviceHelper.getWithScreen(myContext) / 4;
        viewHolder.txt2.requestLayout();
//        viewHolder.txt5.getLayoutParams().width = AndroidDeviceHelper.getWithScreen(myContext) / 4 * 3;
//        viewHolder.txt5.requestLayout();
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txt1.setText(lstOrderDetail.get(i).getProductName());
        viewHolder.txt2.setText(lstOrderDetail.get(i).getQuantity() + "");
        viewHolder.txt3.setText(lstOrderDetail.get(i).getSize());
        viewHolder.txt4.setText(decimalFormat.format(Integer.parseInt(lstOrderDetail.get(i).getPriceOut()))+"đ");
//        viewHolder.txt5.setText(Integer.parseInt(lstOrderDetail.get(i).getPriceOut())*Integer.parseInt(lstOrderDetail.get(i).getQuantity())+"đ");
        Glide.with(myContext).load(DuongDan.url+lstOrderDetail.get(i).getProductUrl()).into(viewHolder.img);
    }

    @Override
    public int getItemCount() {
        return lstOrderDetail.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView txt1, txt2, txt3, txt4, txt5;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_gioHang);
            txt1 = itemView.findViewById(R.id.txt_tenSPGH);
            txt2 = itemView.findViewById(R.id.txt_soLuongMuaGH);
            txt3 = itemView.findViewById(R.id.txt_kichCoMuaGH);
            txt4 = itemView.findViewById(R.id.txt_donGiaMuaGH);
//            txt5 = itemView.findViewById(R.id.txt_thanhTien);
        }
    }
}
