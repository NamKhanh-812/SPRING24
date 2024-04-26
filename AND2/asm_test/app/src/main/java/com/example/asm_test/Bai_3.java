package com.example.asm_test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Bai_3 extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai3);

        bottomNavigationView = findViewById(R.id.bottomnav);

        //click item
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.chat) {
                    frg_trangchu frg_trangchu = new frg_trangchu();
                    relaceFrg(frg_trangchu);
                } else if (item.getItemId() == R.id.map) {
                    frg_caidat frg_caidat = new frg_caidat();
                    relaceFrg(frg_caidat);
                }

                return true;
            }
        });
    }

    public void relaceFrg(Fragment frg) {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.frmbottom, frg);
    }
}