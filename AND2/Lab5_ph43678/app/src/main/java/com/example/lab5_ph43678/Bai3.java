package com.example.lab5_ph43678;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Bai3 extends AppCompatActivity {
    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai3);
        navigationView = findViewById(R.id.bottom_nav);
        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.chat){
                    frmTrangChu trangChu = new frmTrangChu();
                    relaceFrg(trangChu);
                }
                else if(item.getItemId()==R.id.map){
                    frmCaiDat caiDat = new frmCaiDat();
                    relaceFrg(caiDat);
                } else if (item.getItemId()==R.id.collection) {
                    frmTrangChu trangChu = new frmTrangChu();
                    relaceFrg(trangChu);
                }
                return true;
            }
        });
    }

    public void relaceFrg(Fragment frg) {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.frmBottom, frg);
    }
}