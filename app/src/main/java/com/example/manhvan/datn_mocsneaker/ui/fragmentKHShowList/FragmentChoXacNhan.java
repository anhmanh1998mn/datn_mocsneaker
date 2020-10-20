package com.example.manhvan.datn_mocsneaker.ui.fragmentKHShowList;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.manhvan.datn_mocsneaker.Model.MoShowListOrder;
import com.example.manhvan.datn_mocsneaker.Presenter.PreShowListOrder;
import com.example.manhvan.datn_mocsneaker.Presenter.ShowListOrderInterface;
import com.example.manhvan.datn_mocsneaker.R;
import com.example.manhvan.datn_mocsneaker.adapter.OrderShowListAdapter;

public class FragmentChoXacNhan extends Fragment implements ShowListOrderInterface {
    private RecyclerView recycleChoXacNhan,recycleChoXacNhan1;
    private SharedPreferences sharedPreferences;
    private PreShowListOrder preShowListOrder;
    private OrderShowListAdapter adapterShowList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_kh_cho_xac_nhan,container,false);
        initView(view);
        getData();
        getDataOrder();
        return view;
    }

    private void initView(View view) {
        recycleChoXacNhan=view.findViewById(R.id.recycle_KHChoXacNhan);
        recycleChoXacNhan1=view.findViewById(R.id.recycle_KHChoXacNhan1);
    }
    public void getData(){
        sharedPreferences=getActivity().getSharedPreferences("QuyenTK", Context.MODE_PRIVATE);
        //Nhận từ kiểm tra đơn hàng
        if(sharedPreferences.getString("admin","").equals("Admin")){
            recycleChoXacNhan.setVisibility(View.GONE);
            recycleChoXacNhan1.setVisibility(View.VISIBLE);
        }
//        Toast.makeText(getContext(),sharedPreferences.getInt("maNguoiDung",-1)+"",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart() {
        getDataOrder();
        super.onStart();
    }

    private void getDataOrder(){
        preShowListOrder=new PreShowListOrder(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                preShowListOrder.showListOrder(sharedPreferences.getInt("maNguoiDung",-1),Integer.parseInt(sharedPreferences.getString("quyen","")),1);
            }
        }).start();
    }

    @Override
    public void onSuccessed() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(), LinearLayout.VERTICAL,false);
        recycleChoXacNhan.setLayoutManager(linearLayoutManager);
        adapterShowList=new OrderShowListAdapter(getActivity(),R.layout.item_show_list_order, MoShowListOrder.lstDonHang);
        recycleChoXacNhan.post(new Runnable() {
            @Override
            public void run() {
                Log.d("SizeList",MoShowListOrder.lstDonHang.size()+"");
                recycleChoXacNhan.setAdapter(adapterShowList);
                adapterShowList.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onFailed() {

    }
}
