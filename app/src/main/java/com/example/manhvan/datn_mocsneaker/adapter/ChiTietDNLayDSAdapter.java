package com.example.manhvan.datn_mocsneaker.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manhvan.datn_mocsneaker.MyService.APIService;
import com.example.manhvan.datn_mocsneaker.MyService.Dataservice;
import com.example.manhvan.datn_mocsneaker.R;
import com.example.manhvan.datn_mocsneaker.entity.ChiTietDonNhapLay;
import com.example.manhvan.datn_mocsneaker.util.AndroidDeviceHelper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChiTietDNLayDSAdapter extends RecyclerView.Adapter<ChiTietDNLayDSAdapter.ViewHolder> {
    private Activity myContext;
    private int myLayout;
    private List<ChiTietDonNhapLay> lstChiTietDNLay;
    private onDialogCloseListener1 listener;

    public onDialogCloseListener1 getListener() {
        return listener;
    }

    public void setListener(onDialogCloseListener1 listener) {
        this.listener = listener;
    }

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
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
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

        // show dialog when click item
        if(lstChiTietDNLay.get(i).getStatus().equals("1")){
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(myContext,lstChiTietDNLay.get(i).getProductName(),Toast.LENGTH_SHORT).show();
                    final Dialog dialog=new Dialog(myContext);
                    dialog.setContentView(R.layout.dialog_sua_chi_tiet_nhap_hang_lay);
                    dialog.show();
                    ConstraintLayout constraintLayout=dialog.findViewById(R.id.constrain_dialog);
                    constraintLayout.getLayoutParams().width=AndroidDeviceHelper.getWithScreen(myContext)/4*3;
                    constraintLayout.getLayoutParams().height=AndroidDeviceHelper.getHeighScreen(myContext)/3;
                    constraintLayout.requestLayout();

                    dialog.setCanceledOnTouchOutside(false);
                    TextView txtTenSP=dialog.findViewById(R.id.txt_dialogTenSP);
                    TextView txtKichCo=dialog.findViewById(R.id.txt_dialogKichCoSP);
                    final EditText edtSoLuong=dialog.findViewById(R.id.edt_dialogSoLuongSP);
                    final Button btnSua=dialog.findViewById(R.id.btn_dialogSua);
                    Button btnHuy=dialog.findViewById(R.id.btn_dialogHuy);

                    txtTenSP.setText(lstChiTietDNLay.get(i).getProductName());
                    txtKichCo.setText("Kích cỡ: "+lstChiTietDNLay.get(i).getSize());
                    edtSoLuong.setText(lstChiTietDNLay.get(i).getQuantity());

                    Log.d("ChiTietID",lstChiTietDNLay.get(i).getId());

                    //cancel dialog
                    btnHuy.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.cancel();
                        }
                    });

                    //sửa số lượng chi tiết đơn nhập
                    btnSua.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(edtSoLuong.getText().toString().trim().equals("")){
                                edtSoLuong.setError("Nhập số lượng");
                                return;
                            }
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    Dataservice dataservice= APIService.getService();
                                    Call<String> callback=dataservice.capNhatChiTietDNLay(Integer.parseInt(lstChiTietDNLay.get(i).getId()),
                                            Integer.parseInt(edtSoLuong.getText().toString().trim()));
                                    callback.enqueue(new Callback<String>() {
                                        @Override
                                        public void onResponse(Call<String> call, Response<String> response) {
                                            btnSua.post(new Runnable() {
                                                @Override
                                                public void run() {
                                                    Toast.makeText(myContext,"Sửa thành công",Toast.LENGTH_SHORT).show();
                                                    dialog.cancel();
                                                    if(listener!=null){
                                                        listener.onDialogClose();
                                                    }
                                                    notifyDataSetChanged();
                                                }
                                            });


                                        }

                                        @Override
                                        public void onFailure(Call<String> call, Throwable t) {

                                        }
                                    });
                                }
                            }).start();
                        }
                    });
                }

            });
            return;
        }

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

    public interface onDialogCloseListener1{
        void onDialogClose();
    }
}
