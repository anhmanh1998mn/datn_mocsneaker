package com.example.manhvan.datn_mocsneaker.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.manhvan.datn_mocsneaker.R;
import com.example.manhvan.datn_mocsneaker.entity.GioHang1;
import com.example.manhvan.datn_mocsneaker.util.AndroidDeviceHelper;
import com.example.manhvan.datn_mocsneaker.util.GioHang;

import java.text.DecimalFormat;
import java.util.List;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.ViewHolder>{
    private Activity myContext;
    private int myLayout;
    private List<GioHang1> lst;
    private Dialog dialog;
    private TextView txtTenSP,txtKichCo;
    private EditText edtSoLuong;
    private Button btnXong,btnXoa,btnHuy,btn39,btn40,btn41,btn42,btn43;
    private OnDialogCloseListener listener;

    public OnDialogCloseListener getListener() {
        return listener;
    }

    public void setListener(OnDialogCloseListener listener) {
        this.listener = listener;
    }

    public GioHangAdapter(Activity myContext, int myLayout, List<GioHang1> lst) {
        this.myContext = myContext;
        this.myLayout = myLayout;
        this.lst = lst;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater= (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(myLayout,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.img.getLayoutParams().width= AndroidDeviceHelper.getWithScreen(myContext)/3;
        viewHolder.img.requestLayout();
        viewHolder.linearLayout.getLayoutParams().width=AndroidDeviceHelper.getWithScreen(myContext);
        viewHolder.linearLayout.requestLayout();
        viewHolder.txt2.getLayoutParams().width=AndroidDeviceHelper.getWithScreen(myContext)/4;
        viewHolder.txt2.requestLayout();
        viewHolder.txt5.getLayoutParams().width=AndroidDeviceHelper.getWithScreen(myContext)/4*3;
        viewHolder.txt5.requestLayout();
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        viewHolder.txt1.setText(lst.get(i).getTenSP());
        viewHolder.txt2.setText(lst.get(i).getSoLuong()+"");
        viewHolder.txt3.setText(lst.get(i).getKichCo());
        viewHolder.txt4.setText(decimalFormat.format(lst.get(i).getDonGia())+" đ");
        viewHolder.txt5.setText(decimalFormat.format(lst.get(i).getDonGia()*lst.get(i).getSoLuong())+" đ");
//        Glide.with(myContext).load("http://192.168.43.91:8080"+lst.get(i).getDuongDan()).into(viewHolder.img);
        Glide.with(myContext).load("http://192.168.42.44"+lst.get(i).getDuongDan()).into(viewHolder.img);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog=new Dialog(myContext);
                dialog.setContentView(R.layout.dialogsuagiohang);
                ConstraintLayout constraintLayout=dialog.findViewById(R.id.dialogGH);
                constraintLayout.getLayoutParams().width=AndroidDeviceHelper.getWithScreen(myContext)/4*3;
                constraintLayout.getLayoutParams().height=AndroidDeviceHelper.getHeighScreen(myContext)/3;
                constraintLayout.requestLayout();
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();

                txtTenSP=dialog.findViewById(R.id.txt_ghSuaTenSp);
                txtKichCo=dialog.findViewById(R.id.txt_kichCoGH);
                edtSoLuong=dialog.findViewById(R.id.edt_soLuongspGH1);
                btnXong=dialog.findViewById(R.id.btn_suaGHXong);
                btnXoa=dialog.findViewById(R.id.btn_suaGHXoa);
                btnHuy=dialog.findViewById(R.id.btn_suaGHHuy);
                btn39=dialog.findViewById(R.id.b_39);
                btn40=dialog.findViewById(R.id.b_40);
                btn41=dialog.findViewById(R.id.b_41);
                btn42=dialog.findViewById(R.id.b_42);
                btn43=dialog.findViewById(R.id.b_43);
                txtTenSP.setText(lst.get(i).getTenSP());
                txtKichCo.setText(lst.get(i).getKichCo()+"");
                edtSoLuong.setText(lst.get(i).getSoLuong()+"");

                btn39.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        txtKichCo.setText("39");
                    }
                });
                btn40.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        txtKichCo.setText("40");
                    }
                });
                btn41.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        txtKichCo.setText("41");
                    }
                });
                btn42.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        txtKichCo.setText("42");
                    }
                });
                btn43.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        txtKichCo.setText("43");
                    }
                });

                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
                txtKichCo.setTag(txtKichCo.getText().toString().trim());
                btnXong.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(txtKichCo.getTag().equals(txtKichCo.getText().toString())){
                            for(int i1=0;i1<GioHang.arrGioHang.size();i1++){
                                if(lst.get(i1).getKichCo().equals(txtKichCo.getText().toString())){
                                    lst.get(i1).setSoLuong(Integer.parseInt(edtSoLuong.getText().toString().trim()));
                                    dialog.cancel();
                                    if(listener != null){
                                        listener.onDialogClose();
                                    }

                                    //reload actyvity
//                                myContext.recreate();
                                    notifyDataSetChanged();
                                    return;
                                }
                            }
                            return;
                        }
                        for(int i1=0;i1<GioHang.arrGioHang.size();i1++){
                            if(lst.get(i1).getKichCo().equals(txtKichCo.getText().toString())){
                                lst.get(i1).setSoLuong(Integer.parseInt(edtSoLuong.getText().toString().trim())+lst.get(i1).getSoLuong());
                                dialog.cancel();
                                lst.remove(i1);

                                //reload actyvity
//                                myContext.recreate();
                                notifyDataSetChanged();
                                return;
                            }
                        }
                    }

                });

                btnXoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        GioHang.arrGioHang.remove(i);
                        dialog.cancel();
                        if(listener != null){
                            listener.onDialogClose();
                        }
                        //reload activity
                        //myContext.recreate();
                        notifyDataSetChanged();
                    }
                });
            }
        });
    }


    @Override
    public int getItemCount() {
        return lst.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView txt1,txt2,txt3,txt4,txt5;
        private LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img_gioHang);
            txt1=itemView.findViewById(R.id.txt_tenSPGH);
            txt2=itemView.findViewById(R.id.txt_soLuongMuaGH);
            txt3=itemView.findViewById(R.id.txt_kichCoMuaGH);
            txt4=itemView.findViewById(R.id.txt_donGiaMuaGH);
            txt5=itemView.findViewById(R.id.txt_thanhTien);
            linearLayout=itemView.findViewById(R.id.lngohang);
        }
    }


    public interface OnDialogCloseListener {
        void onDialogClose();
    }
}
