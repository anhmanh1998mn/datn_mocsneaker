package com.example.manhvan.datn_mocsneaker.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.manhvan.datn_mocsneaker.Model.MoSanPhamMoiNhat;
import com.example.manhvan.datn_mocsneaker.MyService.APIService;
import com.example.manhvan.datn_mocsneaker.MyService.Dataservice;
import com.example.manhvan.datn_mocsneaker.Presenter.PreSanPhamMoiNhat;
import com.example.manhvan.datn_mocsneaker.R;
import com.example.manhvan.datn_mocsneaker.View.SanPhamMoiKQ2;
import com.example.manhvan.datn_mocsneaker.View.ViewSanPham;
import com.example.manhvan.datn_mocsneaker.entity.SanPhamMoi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private Activity myContext;
    private int myLayout;
    private List<String> lst;
    public static ArrayList<SanPhamMoi> arrSPM,arrSPNB,arrGiayNam,arrGiayNu,arrGiayDoi;


    public HomeAdapter(Activity myContext, int myLayout, List<String> lst) {
        this.myContext = myContext;
        this.myLayout = myLayout;
        this.lst = lst;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(myLayout, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        viewHolder.txt1.setText(lst.get(i));
        switch (i) {
            case 0: {
                viewHolder.btnXemThem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        myContext.startActivity(new Intent(myContext, ViewSanPham.class));
                    }
                });
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(myContext, LinearLayout.HORIZONTAL, false);
                viewHolder.recyclerView.setLayoutManager(linearLayoutManager);
                arrSPM=new ArrayList<>();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Dataservice dataservice= APIService.getService();
                        Call<List<SanPhamMoi>> callback=dataservice.GetSPMoi();
                        callback.enqueue(new Callback<List<SanPhamMoi>>() {
                            @Override
                            public void onResponse(Call<List<SanPhamMoi>> call, Response<List<SanPhamMoi>> response) {
                                arrSPM= (ArrayList<SanPhamMoi>) response.body();

                                final RecylceViewConAdapter recylceViewConAdapter = new RecylceViewConAdapter(myContext, R.layout.itemmrclviewcon, arrSPM);
                                viewHolder.recyclerView.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        viewHolder.recyclerView.setAdapter(recylceViewConAdapter);
                                    }
                                });
                            }

                            @Override
                            public void onFailure(Call<List<SanPhamMoi>> call, Throwable t) {

                            }
                        });
                    }
                }).start();
//                PreSanPhamMoiNhat preSanPhamMoiNhat = new PreSanPhamMoiNhat(this);
//                preSanPhamMoiNhat.SanPhamMoiNhat();


                break;
            }
            case 1: {
                viewHolder.btnXemThem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        myContext.startActivity(new Intent(myContext, ViewSanPham.class));
                    }
                });
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(myContext, LinearLayout.HORIZONTAL, false);
                viewHolder.recyclerView.setLayoutManager(linearLayoutManager);
                arrSPNB=new ArrayList<>();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Dataservice dataservice= APIService.getService();
                        Call<List<SanPhamMoi>> callback=dataservice.GetSPNB();
                        callback.enqueue(new Callback<List<SanPhamMoi>>() {
                            @Override
                            public void onResponse(Call<List<SanPhamMoi>> call, Response<List<SanPhamMoi>> response) {
                                arrSPNB= (ArrayList<SanPhamMoi>) response.body();

                                final RecylceViewConAdapter recylceViewConAdapter = new RecylceViewConAdapter(myContext, R.layout.itemmrclviewcon, arrSPNB);
                                viewHolder.recyclerView.setHasFixedSize(true);
                                viewHolder.recyclerView.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        viewHolder.recyclerView.setAdapter(recylceViewConAdapter);
                                    }
                                });
                            }

                            @Override
                            public void onFailure(Call<List<SanPhamMoi>> call, Throwable t) {

                            }
                        });
                    }
                }).start();
                break;
            }
            case 2: {
                viewHolder.btnXemThem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        myContext.startActivity(new Intent(myContext, ViewSanPham.class));
                    }
                });
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(myContext, LinearLayout.HORIZONTAL, false);
                viewHolder.recyclerView.setLayoutManager(linearLayoutManager);
                arrGiayNam=new ArrayList<>();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Dataservice dataservice= APIService.getService();
                        Call<List<SanPhamMoi>> callback=dataservice.GetGiayNam(1);
                        callback.enqueue(new Callback<List<SanPhamMoi>>() {
                            @Override
                            public void onResponse(Call<List<SanPhamMoi>> call, Response<List<SanPhamMoi>> response) {
                                arrGiayNam= (ArrayList<SanPhamMoi>) response.body();

                                final RecylceViewConAdapter recylceViewConAdapter = new RecylceViewConAdapter(myContext, R.layout.itemmrclviewcon, arrGiayNam);
                                viewHolder.recyclerView.setHasFixedSize(true);
                                viewHolder.recyclerView.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        viewHolder.recyclerView.setAdapter(recylceViewConAdapter);
                                    }
                                });
                            }

                            @Override
                            public void onFailure(Call<List<SanPhamMoi>> call, Throwable t) {

                            }
                        });
                    }
                }).start();
                break;
            }
            case 3: {
                viewHolder.btnXemThem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        myContext.startActivity(new Intent(myContext, ViewSanPham.class));
                    }
                });
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(myContext, LinearLayout.HORIZONTAL, false);
                viewHolder.recyclerView.setLayoutManager(linearLayoutManager);
                arrGiayNu=new ArrayList<>();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Dataservice dataservice= APIService.getService();
                        Call<List<SanPhamMoi>> callback=dataservice.GetGiayNam(2);
                        callback.enqueue(new Callback<List<SanPhamMoi>>() {
                            @Override
                            public void onResponse(Call<List<SanPhamMoi>> call, Response<List<SanPhamMoi>> response) {
                                arrGiayNu= (ArrayList<SanPhamMoi>) response.body();

                                final RecylceViewConAdapter recylceViewConAdapter = new RecylceViewConAdapter(myContext, R.layout.itemmrclviewcon, arrGiayNu);
                                viewHolder.recyclerView.setHasFixedSize(true);
                                viewHolder.recyclerView.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        viewHolder.recyclerView.setAdapter(recylceViewConAdapter);
                                    }
                                });
                            }

                            @Override
                            public void onFailure(Call<List<SanPhamMoi>> call, Throwable t) {

                            }
                        });
                    }
                }).start();
                break;
            }
            case 4: {
                viewHolder.btnXemThem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        myContext.startActivity(new Intent(myContext, ViewSanPham.class));
                    }
                });
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(myContext, LinearLayout.HORIZONTAL, false);
                viewHolder.recyclerView.setLayoutManager(linearLayoutManager);
                arrGiayDoi=new ArrayList<>();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Dataservice dataservice= APIService.getService();
                        Call<List<SanPhamMoi>> callback=dataservice.GetGiayNam(3);
                        callback.enqueue(new Callback<List<SanPhamMoi>>() {
                            @Override
                            public void onResponse(Call<List<SanPhamMoi>> call, Response<List<SanPhamMoi>> response) {
                                arrGiayDoi= (ArrayList<SanPhamMoi>) response.body();

                                final RecylceViewConAdapter recylceViewConAdapter = new RecylceViewConAdapter(myContext, R.layout.itemmrclviewcon, arrGiayDoi);
                                viewHolder.recyclerView.setHasFixedSize(true);
                                viewHolder.recyclerView.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        viewHolder.recyclerView.setAdapter(recylceViewConAdapter);
                                    }
                                });
                            }

                            @Override
                            public void onFailure(Call<List<SanPhamMoi>> call, Throwable t) {

                            }
                        });
                    }
                }).start();
                break;
            }
        }
    }


    @Override
    public int getItemCount() {
        return lst.size();
    }

//    @Override
//    public void onSuccess(ArrayList<SanPhamMoi> arrayList) {
//        arrSPM = arrayList;
//        Log.d("kqs:", arrSPM.size() + "");
//        kq = 1;
//
//    }
//
//    @Override
//    public void onFailed() {
//
//    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt1;
        TextView btnXemThem;
        RecyclerView recyclerView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt1 = itemView.findViewById(R.id.txt_home1);
            btnXemThem = itemView.findViewById(R.id.btn_home2);
            recyclerView = itemView.findViewById(R.id.rcl_home);
        }
    }
}
