package com.example.manhvan.datn_mocsneaker.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.manhvan.datn_mocsneaker.R;
import com.example.manhvan.datn_mocsneaker.entity.ChiTietDonNhap;

import java.util.List;

public class ChiTietDonNhapAdapter extends RecyclerView.Adapter<ChiTietDonNhapAdapter.ViewHolder> {
    private Activity myContext;
    private int myLayout;
    private List<ChiTietDonNhap> lstDonNhap;

    public ChiTietDonNhapAdapter(Activity myContext, int myLayout, List<ChiTietDonNhap> lstDonNhap) {
        this.myContext = myContext;
        this.myLayout = myLayout;
        this.lstDonNhap = lstDonNhap;
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
        viewHolder.txtTenSP.setText(lstDonNhap.get(i).getTenSP());
        viewHolder.txtKichCo.setText(lstDonNhap.get(i).getKichCo());
        viewHolder.txtSoLuong.setText(lstDonNhap.get(i).getSoLuong()+"");
    }

    @Override
    public int getItemCount() {
        return lstDonNhap.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenSP,txtKichCo,txtSoLuong;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenSP=itemView.findViewById(R.id.txt_itemTenSP);
            txtKichCo=itemView.findViewById(R.id.txt_itemKichCoSP);
            txtSoLuong=itemView.findViewById(R.id.txt_itemSoLuongSP);
        }
    }
}
