package com.example.manhvan.datn_mocsneaker;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.manhvan.datn_mocsneaker.ui.dashboard.DashboardFragment;

public class MainActivity2 extends AppCompatActivity {

    DashboardFragment dashboardFragment;
    private BottomNavigationView navView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        navView = findViewById(R.id.nav_view);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);

        //hide child botton navigationview


        quyenTaiKhoan();
    }

    private void quyenTaiKhoan() {

        SharedPreferences sharedPreferences=getSharedPreferences("QuyenTK", Context.MODE_PRIVATE);
        if (sharedPreferences.getString("quyen","").equals("2")){
            navView.getMenu().getItem(1).setVisible(true);
            return;
        }
        navView.getMenu().getItem(1).setVisible(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menucart,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }
}