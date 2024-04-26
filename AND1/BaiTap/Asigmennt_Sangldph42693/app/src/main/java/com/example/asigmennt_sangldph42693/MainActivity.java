package com.example.asigmennt_sangldph42693;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView logo;
    private ImageView logo1;
    private ImageView img1;
    private ImageView img2;
    private ImageView img3;
    private ImageView img4;
    private ImageView img5;

    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getView();
        Animation animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.animation);
        img1.startAnimation(animation);
        img5.startAnimation(animation);
        img3.startAnimation(animation);
        Animation animation1 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.anima);
        logo.startAnimation(animation1);
        logo1.startAnimation(animation1);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this, Dangnhap.class));
                finish();
            }
        },3000);
        startAnimation();
    }
    private void startAnimation(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                img2.animate().rotationBy(360).withEndAction(this).setDuration(3000)
                        .setInterpolator(new LinearInterpolator()).start();
                img4.animate().rotationBy(360).withEndAction(this).setDuration(3000)
                        .setInterpolator(new LinearInterpolator()).start();

            }
        };
        img2.animate().rotationBy(360).withEndAction(runnable).setDuration(3000)
                .setInterpolator(new LinearInterpolator()).start();
        img4.animate().rotationBy(360).withEndAction(runnable).setDuration(3000)
                .setInterpolator(new LinearInterpolator()).start();    }
    private void getView(){
        logo = findViewById(R.id.logo);
        logo1 = findViewById(R.id.logo1);
        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        img4 = findViewById(R.id.img4);
        img5 = findViewById(R.id.img5);
    }
}