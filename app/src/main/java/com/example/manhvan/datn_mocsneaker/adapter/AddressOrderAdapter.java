package com.example.manhvan.datn_mocsneaker.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.manhvan.datn_mocsneaker.R;
import com.example.manhvan.datn_mocsneaker.entity.AddressOrder;

import java.util.List;

public class AddressOrderAdapter extends BaseAdapter {
    private Activity myContext;
    private int myLayout;
    private List<AddressOrder> lstAdressOrder;

    public AddressOrderAdapter(Activity myContext, int myLayout, List<AddressOrder> lstAdressOrder) {
        this.myContext = myContext;
        this.myLayout = myLayout;
        this.lstAdressOrder = lstAdressOrder;
    }


    @Override
    public int getCount() {
        return lstAdressOrder.size();
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
        TextView txt1;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null){
            LayoutInflater inflater=(LayoutInflater)myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(myLayout,null);
            holder=new ViewHolder();
            holder.txt1=view.findViewById(R.id.txt_itemAddress);
            view.setTag(holder);
        }else {
            holder=(ViewHolder)view.getTag();
        }
        holder.txt1.setText(lstAdressOrder.get(i).getAOrderAddress());

        return view;
    }
}
