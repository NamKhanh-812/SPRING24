package com.example.lab7_ph43678;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Bai1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai1);
        // ánh xạ
        ImageView imgHinh = findViewById(R.id.imgHinh);
        TextView txtTT = findViewById(R.id.txtTt);

        Animation anm1 = AnimationUtils.loadAnimation(this,R.anim.hieu_ung_1a);
        txtTT.startAnimation(anm1);
    }
}