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
import com.example.manhvan.datn_mocsneaker.View.MainChiTietDonNhap;
import com.example.manhvan.datn_mocsneaker.entity.DonNhapHang;
import com.example.manhvan.datn_mocsneaker.util.AndroidDeviceHelper;

import java.util.List;

public class DanhSachDonNhapAdapter extends RecyclerView.Adapter<DanhSachDonNhapAdapter.ViewHolder> {
    private Activity myContext;
    private int myLayout;
    private List<DonNhapHang> lstDonNhapHang;

    public DanhSachDonNhapAdapter(Activity myContext, int myLayout, List<DonNhapHang> lstDonNhapHang) {
        this.myContext = myContext;
        this.myLayout = myLayout;
        this.lstDonNhapHang = lstDonNhapHang;
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
        viewHolder.txtMaDN.getLayoutParams().width= AndroidDeviceHelper.getWithScreen(myContext)/6;
        viewHolder.txtMaDN.requestLayout();
        viewHolder.txtNgayLap.getLayoutParams().width= AndroidDeviceHelper.getWithScreen(myContext)/6*25/10;
        viewHolder.txtNgayLap.requestLayout();
        viewHolder.txtMaNV.getLayoutParams().width= AndroidDeviceHelper.getWithScreen(myContext)/6*5/10;
        viewHolder.txtMaNV.requestLayout();
        viewHolder.txtTenNV.getLayoutParams().width= AndroidDeviceHelper.getWithScreen(myContext)/6*2;
        viewHolder.txtTenNV.requestLayout();
        viewHolder.txtMaDN.setText(lstDonNhapHang.get(i).getId());
        viewHolder.txtNgayLap.setText(lstDonNhapHang.get(i).getCreatedAt());
        viewHolder.txtMaNV.setText(lstDonNhapHang.get(i).getStaffId());
        viewHolder.txtTenNV.setText(lstDonNhapHang.get(i).getStaffName());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(myContext, MainChiTietDonNhap.class);
                myContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lstDonNhapHang.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtMaDN,txtNgayLap,txtMaNV,txtTenNV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaDN=itemView.findViewById(R.id.txt_maDonNhap);
            txtNgayLap=itemView.findViewById(R.id.txt_thoiGianLap);
            txtMaNV=itemView.findViewById(R.id.txt_maNhanVien);
            txtTenNV=itemView.findViewById(R.id.txt_tenNhanVien);
        }
    }
}
