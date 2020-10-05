package com.example.manhvan.datn_mocsneaker.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.manhvan.datn_mocsneaker.R;

public class MainDoiMatKhau extends AppCompatActivity {
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_doi_mat_khau);
        actionBar=getSupportActionBar();
        actionBar.setTitle("Gửi lại mật khẩu");
        actionBar.setDisplayHomeAsUpEnabled(true);
        getData();
    }

    private void getData() {
        Intent intent=getIntent();
        Toast.makeText(this,intent.getStringExtra("MatKhau"),Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                finish();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}