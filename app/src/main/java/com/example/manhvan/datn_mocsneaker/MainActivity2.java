package com.example.manhvan.datn_mocsneaker;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.manhvan.datn_mocsneaker.ui.dashboard.DashboardFragment;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener{
    private ActionBar actionBar;
    private LinearLayout frameLayout;
    DashboardFragment dashboardFragment;
    private BottomNavigationView navView;
    private Button btnbackSearch,btnCloseSearch;
    private EditText autoCompleteTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        navView = findViewById(R.id.nav_view);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);

        //hide child botton navigationview


        quyenTaiKhoan();
        initView();

        changeTextSearch();
    }

    private void changeTextSearch() {
        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().trim().equals("")){
                    btnCloseSearch.setVisibility(View.GONE);
                    return;
                }
                btnCloseSearch.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void initView() {
        btnbackSearch=findViewById(R.id.btn_backSearch);
        autoCompleteTextView=findViewById(R.id.edt_searchHome);
        btnCloseSearch=findViewById(R.id.btn_closeSearch);
        btnbackSearch.setOnClickListener(this);
        btnCloseSearch.setOnClickListener(this);
    }

    private void quyenTaiKhoan() {

        SharedPreferences sharedPreferences=getSharedPreferences("QuyenTK", Context.MODE_PRIVATE);
        if (sharedPreferences.getString("quyen","").equals("2")||sharedPreferences.getString("quyen","").equals("1")){
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
        switch (item.getItemId()){
            case R.id.mnucart:{
                break;
            }
            case R.id.mnusearch:{
                actionBar=getSupportActionBar();
                actionBar.hide();
                frameLayout=findViewById(R.id.framemain2);
                frameLayout.setVisibility(View.VISIBLE);
                autoCompleteTextView.requestFocus();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_backSearch:{
                actionBar.show();
                frameLayout.setVisibility(View.GONE);
                break;
            }
            case R.id.btn_closeSearch:{
                break;
            }
        }
    }
}