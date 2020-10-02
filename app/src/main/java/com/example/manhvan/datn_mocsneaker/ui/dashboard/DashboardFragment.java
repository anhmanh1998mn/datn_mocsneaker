package com.example.manhvan.datn_mocsneaker.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.manhvan.datn_mocsneaker.R;
import com.example.manhvan.datn_mocsneaker.View.ViewQuanLyNhanVIen;

public class DashboardFragment extends Fragment implements View.OnClickListener {

    private DashboardViewModel dashboardViewModel ;
    private Button btnQLNhanVien;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        //quyenHienThi();
        initView(root);
        eventCleck();

        return root;
    }

    private void quyenHienThi() {

        Bundle bundle=getArguments();
        String quyen=bundle.getString("Quyen");
        if (quyen!=null){
            Toast.makeText(getContext(),quyen,Toast.LENGTH_SHORT).show();
        }

    }


    private void eventCleck() {
        btnQLNhanVien.setOnClickListener(this);
    }

    private void initView(View root) {
        btnQLNhanVien=root.findViewById(R.id.btn_qlnhanvien);
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