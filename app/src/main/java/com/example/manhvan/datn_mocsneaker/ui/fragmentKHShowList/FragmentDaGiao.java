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

public class FragmentDaGiao extends Fragment implements ShowListOrderInterface {
    private RecyclerView recycleDaGiao,recyclerViewDaGiao1;
    private SharedPreferences sharedPreferences;
    private PreShowListOrder preShowListOrder;
    private OrderShowListAdapter adapterShowList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_khdagiao,container,false);
        initView(view);
        getData();
        getDataOrder();
        return view;
    }

    private void initView(View view) {
        recycleDaGiao=view.findViewById(R.id.recycle_KHDaGiao);
        recyclerViewDaGiao1=view.findViewById(R.id.recycle_KHDaGiao1);
    }
    public void getData(){
        sharedPreferences=getActivity().getSharedPreferences("QuyenTK", Context.MODE_PRIVATE);
        if(sharedPreferences.getString("admin","").equals("Admin")){
            recycleDaGiao.setVisibility(View.GONE);
            recyclerViewDaGiao1.setVisibility(View.VISIBLE);
            getDataOrder1();
        }
//        Toast.makeText(getContext(),sharedPreferences.getString("quyen","")+"",Toast.LENGTH_SHORT).show();
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
                preShowListOrder.showListOrder(sharedPreferences.getInt("maNguoiDung",-1),Integer.parseInt(sharedPreferences.getString("quyen","")),5);
            }
        }).start();
    }

    private void getDataOrder1(){
        preShowListOrder=new PreShowListOrder(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                preShowListOrder.showlistOrder1(Integer.parseInt(sharedPreferences.getString("quyen","")),5);

            }
        }).start();
    }

    @Override
    public void onSuccessed() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(), LinearLayout.VERTICAL,false);
        recycleDaGiao.setLayoutManager(linearLayoutManager);
        adapterShowList=new OrderShowListAdapter(getActivity(),R.layout.item_show_list_order, MoShowListOrder.lstDonHang);
        recycleDaGiao.post(new Runnable() {
            @Override
            public void run() {
                Log.d("SizeList",MoShowListOrder.lstDonHang.size()+"");
                recycleDaGiao.setAdapter(adapterShowList);
                adapterShowList.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onSuccessed1() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(), LinearLayout.VERTICAL,false);
        recyclerViewDaGiao1.setLayoutManager(linearLayoutManager);
        adapterShowList=new OrderShowListAdapter(getActivity(),R.layout.item_show_list_order,MoShowListOrder.lstDonHangAdmin);
        recyclerViewDaGiao1.post(new Runnable() {
            @Override
            public void run() {
                recyclerViewDaGiao1.setAdapter(adapterShowList);
                adapterShowList.notifyDataSetChanged();
                Log.d("ThemOrder",MoShowListOrder.lstDonHangAdmin.size()+"");
            }
        });
    }

    @Override
    public void onFailed() {

    }


}
