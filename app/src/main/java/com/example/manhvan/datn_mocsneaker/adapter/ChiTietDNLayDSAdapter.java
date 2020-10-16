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
import com.example.manhvan.datn_mocsneaker.entity.ChiTietDonNhapLay;
import com.example.manhvan.datn_mocsneaker.util.AndroidDeviceHelper;

import java.util.List;

public class ChiTietDNLayDSAdapter extends RecyclerView.Adapter<ChiTietDNLayDSAdapter.ViewHolder> {
    private Activity myContext;
    private int myLayout;
    private List<ChiTietDonNhapLay> lstChiTietDNLay;

    public ChiTietDNLayDSAdapter(Activity myContext, int myLayout, List<ChiTietDonNhapLay> lstChiTietDNLay) {
        this.myContext = myContext;
        this.myLayout = myLayout;
        this.lstChiTietDNLay = lstChiTietDNLay;
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
        viewHolder.txtTenSP.getLayoutParams().width= AndroidDeviceHelper.getWithScreen(myContext)/9*4;
        viewHolder.txtKichCoSP.getLayoutParams().width=AndroidDeviceHelper.getWithScreen(myContext)/9*2;
        viewHolder.txtSoLuongSP.getLayoutParams().width=AndroidDeviceHelper.getWithScreen(myContext)/9*2;
        viewHolder.txtSTT.getLayoutParams().width=AndroidDeviceHelper.getWithScreen(myContext)/9*2;
        viewHolder.txtTenSP.requestLayout();
        viewHolder.txtSoLuongSP.requestLayout();
        viewHolder.txtKichCoSP.requestLayout();
        viewHolder.txtSTT.requestLayout();

        viewHolder.txtTenSP.setText(lstChiTietDNLay.get(i).getProductName());
        viewHolder.txtKichCoSP.setText(lstChiTietDNLay.get(i).getSize());
        viewHolder.txtSoLuongSP.setText(lstChiTietDNLay.get(i).getQuantity());
        viewHolder.txtSTT.setText(i+1+"");
    }

    @Override
    public int getItemCount() {
        return lstChiTietDNLay.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenSP,txtKichCoSP,txtSoLuongSP,txtSTT;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenSP=itemView.findViewById(R.id.txt_layTenDN);
            txtKichCoSP=itemView.findViewById(R.id.txt_laySizeDN);
            txtSoLuongSP=itemView.findViewById(R.id.txt_laySoLuongDN);
            txtSTT=itemView.findViewById(R.id.txt_sttDN);
        }
    }
}
