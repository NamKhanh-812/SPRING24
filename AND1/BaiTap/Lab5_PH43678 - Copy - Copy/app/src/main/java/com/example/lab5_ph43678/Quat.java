package com.example.lab5_ph43678;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;

public class Quat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quat);
        Button btnFast = findViewById(R.id.btnFast);
        Button btnMedium = findViewById(R.id.btnMedium);
        Button btnSlow = findViewById(R.id.btnSlow);
        Button btnOff = findViewById(R.id.btnOff);
        ImageView imageView = findViewById(R.id.imgQuat);
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.quat);
        imageView.startAnimation(animation);
        btnFast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                starFast(300);
            }
        });
        btnMedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                starMedium(1000);
            }
        });
        btnSlow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                starSlow(3000);
            }
        });
        btnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopFan();
            }
        });
    }
    private  void starFast(long tinme){
        Runnable runnable = new Runnable() {
            ImageView imageView = findViewById(R.id.imgQuat);
            @Override
            public void run() {
                imageView.animate()
                        .rotationBy(360)
                        .withEndAction(this)
                        .setDuration(tinme)
                        .setInterpolator(new LinearInterpolator())
                        .start();
            }
        };
        ImageView imageView = findViewById(R.id.imgQuat);
        imageView.animate()
                .rotationBy(360)
                .withEndAction(runnable)
                .setDuration(3000)
                .setInterpolator(new LinearInterpolator())
                .start();
    }
    private  void starMedium(long tinme){
        Runnable runnable = new Runnable() {
            ImageView imageView = findViewById(R.id.imgQuat);
            @Override
            public void run() {
                imageView.animate()
                        .rotationBy(360)
                        .withEndAction(this)
                        .setDuration(tinme)
                        .setInterpolator(new LinearInterpolator())
                        .start();
            }
        };
        ImageView imageView = findViewById(R.id.imgQuat);
        imageView.animate()
                .rotationBy(360)
                .withEndAction(runnable)
                .setDuration(3000)
                .setInterpolator(new LinearInterpolator())
                .start();
    }
    private  void starSlow(long tinme){
        Runnable runnable = new Runnable() {
            ImageView imageView = findViewById(R.id.imgQuat);
            @Override
            public void run() {
                imageView.animate()
                        .rotationBy(360)
                        .withEndAction(this)
                        .setDuration(tinme)
                        .setInterpolator(new LinearInterpolator())
                        .start();
            }
        };
        ImageView imageView = findViewById(R.id.imgQuat);
        imageView.animate()
                .rotationBy(360)
                .withEndAction(runnable)
                .setDuration(3000)
                .setInterpolator(new LinearInterpolator())
                .start();
    }
    private void stopFan(){
        ImageView imageView = findViewById(R.id.imgQuat);
        imageView.animate().cancel();
    }
}