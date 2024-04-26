package com.example.lab5_ph43678;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ImageView imgHinh = findViewById(R.id.imgHinh);
        TextView txtTT = findViewById(R.id.txtTt);
        Animation anm1 = AnimationUtils.loadAnimation(this,R.anim.hieu_ung_welcome);
        txtTT.startAnimation(anm1);
        Animation anm2 = AnimationUtils.loadAnimation(this,R.anim.logo);
        imgHinh.startAnimation(anm2);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Welcome.this,DangNhap.class);
                startActivity(intent);
                finish();
            }
        },3000);
    }
}