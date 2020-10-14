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
import com.example.manhvan.datn_mocsneaker.entity.SanPhamMoi;

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

    }

    @Override
    public int getItemCount() {
        return lstTimKiem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txt1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt1=itemView.findViewById(R.id.txt_itemtimkiemSP);
        }
    }
}
