package com.example.manhvan.datn_mocsneaker.ui.dashboard;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.manhvan.datn_mocsneaker.Presenter.PreThongTinTaiKhoan;
import com.example.manhvan.datn_mocsneaker.R;
import com.example.manhvan.datn_mocsneaker.View.QuanLyDonNhapHang.MainQLNhapHangDanhSach;
import com.example.manhvan.datn_mocsneaker.View.QuanLyKhachHang.MainQuanLyKhachHang;
import com.example.manhvan.datn_mocsneaker.View.QuanLySanPham.MainQuanLySanPham;
import com.example.manhvan.datn_mocsneaker.View.QuanLyDonHang.MainShowListOrdersKH;
import com.example.manhvan.datn_mocsneaker.View.QLThongKeBaoCao.MainThongKeBaoCao;
import com.example.manhvan.datn_mocsneaker.View.PKInterface.ThongTinKHInterKQ2;
import com.example.manhvan.datn_mocsneaker.View.QuanLyNhanVien.ViewQuanLyNhanVIen;
import com.example.manhvan.datn_mocsneaker.entity.ThongTinKhachHang;
import com.example.manhvan.datn_mocsneaker.entity.ThongTinNV;

import java.util.ArrayList;

public class DashboardFragment extends Fragment implements View.OnClickListener, ThongTinKHInterKQ2 {
    private TextView txtFragment2;
    private SharedPreferences sharedPreferences;
    private PreThongTinTaiKhoan preThongTinTaiKhoan;
    private Button btnQLNhanVien,btnKiemTraDonHang,btnQuanLySP,btnQuanLyKH,btnQuanLyDonHang,btnQuanLyNhapHang;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        initView(root);
        eventCleck();
        quyenHienThi();
        getData();
        return root;
    }

    private void getData() {
        // lấy mã nhân viên dùng cho thêm đơn nhập hàng
        preThongTinTaiKhoan=new PreThongTinTaiKhoan(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                sharedPreferences= getActivity().getSharedPreferences("QuyenTK", Context.MODE_PRIVATE);
                preThongTinTaiKhoan.thongTinTaiKhoan(sharedPreferences.getString("quyen",""),sharedPreferences.getString("phone",""));
            }
        }).start();
    }

    private void quyenHienThi() {
        SharedPreferences sharedPreferences=this.getActivity().getSharedPreferences("QuyenTK", Context.MODE_PRIVATE);
        String quyen=sharedPreferences.getString("quyen","");
        Log.d("idNguoiLap",quyen+"");
        if (quyen.equals("1")){
            btnQLNhanVien.setVisibility(View.VISIBLE);
            btnQuanLySP.setVisibility(View.VISIBLE);
            return;
        }

    }


    private void eventCleck() {
        btnQLNhanVien.setOnClickListener(this);
        btnQuanLyKH.setOnClickListener(this);
        btnQuanLySP.setOnClickListener(this);
        btnQuanLyNhapHang.setOnClickListener(this);
        btnKiemTraDonHang.setOnClickListener(this);
        btnQuanLyDonHang.setOnClickListener(this);
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
            case R.id.btn_quanLyKhachHang:{
                startActivity(new Intent(getContext(), MainQuanLyKhachHang.class));
                break;
            }
            case R.id.btn_quanLySanPham:{
                startActivity(new Intent(getContext(), MainQuanLySanPham.class));
                break;
            }
            case R.id.btn_quanLyNhapHang:{
                startActivity(new Intent(getContext(), MainQLNhapHangDanhSach.class));
                break;
            }
            case R.id.btn_kiemTraDonHang:{
                Intent intent=new Intent(getContext(), MainShowListOrdersKH.class);
                SharedPreferences sharedPreferences=this.getActivity().getSharedPreferences("QuyenTK", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("admin","Admin");
                editor.commit();
                startActivity(intent);
                break;
            }
            case R.id.btn_quanLyDonHang:{
                startActivity(new Intent(getContext(), MainThongKeBaoCao.class));
                break;
            }
        }
    }

    @Override
    public void thongTinNhanVien(ArrayList<ThongTinNV> arrayList) {
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("idNhanVien",arrayList.get(0).getId()+"");
        editor.commit();
    }

    @Override
    public void thongTinKhachHang(ArrayList<ThongTinKhachHang> arrayList) {

    }

    @Override
    public void loi() {

    }
}