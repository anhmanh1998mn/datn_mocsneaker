package com.example.manhvan.datn_mocsneaker.ui.dashboard;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.manhvan.datn_mocsneaker.R;
import com.example.manhvan.datn_mocsneaker.View.ViewQuanLyNhanVIen;

public class DashboardFragment extends Fragment implements View.OnClickListener {
    private TextView txtFragment2;
    private Button btnQLNhanVien,btnKiemTraDonHang,btnQuanLySP,btnQuanLyKH,btnQuanLyDonHang,btnQuanLyNhapHang;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        quyenHienThi();
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        initView(root);
        eventCleck();

        return root;
    }

    private void quyenHienThi() {
        SharedPreferences sharedPreferences=this.getActivity().getSharedPreferences("QuyenTK", Context.MODE_PRIVATE);
        String quyen=sharedPreferences.getString("quyen","");

    }


    private void eventCleck() {
        btnQLNhanVien.setOnClickListener(this);
    }

    private void initView(View root) {
        btnQLNhanVien=root.findViewById(R.id.btn_qlnhanvien);
        txtFragment2=root.findViewById(R.id.txt_tenFrag);
        btnKiemTraDonHang=root.findViewById(R.id.btn_kiemTraDonHang);
        btnQuanLySP=root.findViewById(R.id.btn_quanLySanPham);
        btnQuanLyKH=root.findViewById(R.id.btn_quanLyKhachHang);
        btnQuanLyDonHang=root.findViewById(R.id.btn_quanLyDonHang);
        btnQuanLyNhapHang=root.findViewById(R.id.btn_quanLyNhapHang);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_qlnhanvien:{
                startActivity(new Intent(getContext(), ViewQuanLyNhanVIen.class));
                break;
            }
        }
    }

}