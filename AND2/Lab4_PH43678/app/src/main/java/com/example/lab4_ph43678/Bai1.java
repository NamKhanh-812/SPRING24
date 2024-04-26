package com.example.lab4_ph43678;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Bai1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai1);
        // ánh xạ
        Button btnfrg1 = findViewById(R.id.btnFrg1);
        Button btnfrg2 = findViewById(R.id.btnFrg2);
        // xử lý sk btn1
        btnfrg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                frgBai1a frgBai1a = new frgBai1a();
                fragmentManager.beginTransaction().replace(R.id.frmBai1,frgBai1a).commit();
            }
        });
        // xử lý sk btn2
        btnfrg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                frgBai1b frgBai1b = new frgBai1b();
                fragmentManager.beginTransaction().replace(R.id.frmBai1,frgBai1b).commit();
            }
        });

    }
}