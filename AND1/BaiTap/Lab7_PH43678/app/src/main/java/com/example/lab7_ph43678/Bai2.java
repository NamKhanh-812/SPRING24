package com.example.lab7_ph43678;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.animation.Animation;
import android.widget.ImageView;

public class Bai2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai2);
        ImageView img = findViewById(R.id.imgAnh);
        img.setBackgroundResource(R.drawable.anml);
        AnimationDrawable frame = (AnimationDrawable) img.getBackground();
        frame.start();
    }
}