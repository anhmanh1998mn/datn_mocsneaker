package com.example.manhvan.datn_mocsneaker.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.manhvan.datn_mocsneaker.R;
import com.example.manhvan.datn_mocsneaker.entity.NhanVien;

import java.util.List;

public class NhanVienAdapter extends BaseAdapter {
    private Activity myContext;
    private int myLayout;
    private List<NhanVien> lstNV;

    public NhanVienAdapter(Activity myContext, int myLayout, List<NhanVien> lstNV) {
        this.myContext = myContext;
        this.myLayout = myLayout;
        this.lstNV = lstNV;
    }

    @Override
    public int getCount() {
        return lstNV.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    public class ViewHolder{
        TextView txt1,txt2,txt3;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null){
            LayoutInflater inflater=(LayoutInflater)myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(myLayout,null);
            holder=new ViewHolder();
            holder.txt1=view.findViewById(R.id.itemnv_stt);
            holder.txt2=view.findViewById(R.id.itemnv_tennv);
            holder.txt3=view.findViewById(R.id.itemnv_sdt);
            view.setTag(holder);
        }else {
            holder=(ViewHolder)view.getTag();
        }
        NhanVien nhanVien=lstNV.get(i);
        holder.txt1.setText(String.valueOf(i+1));
        holder.txt2.setText(nhanVien.getStaffName());
        holder.txt3.setText(nhanVien.getStaffPhone());
        if (Integer.parseInt(nhanVien.getUserStatus())==2){
//            holder.txt1.setPaintFlags(holder.txt1.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
//            holder.txt2.setPaintFlags(holder.txt2.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
//            holder.txt3.setPaintFlags(holder.txt3.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
            holder.txt3.setError("Khóa tài khoản");
        }
        return view;
    }
}
