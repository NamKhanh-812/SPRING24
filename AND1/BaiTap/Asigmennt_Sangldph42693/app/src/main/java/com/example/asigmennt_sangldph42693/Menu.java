package com.example.asigmennt_sangldph42693;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Menu extends AppCompatActivity {

    private Button btnPBan;
    private Button btnNV;
    private TextView btnExit;
    private ImageView imghome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getView();
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnPBan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu.this, Phongban1.class));
            }
        });
        btnNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu.this, Nhanvien1.class));
            }
        });
        Animation animation = AnimationUtils.loadAnimation(Menu.this,R.anim.dichuyen);
        imghome.startAnimation(animation);
        imghome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu.this, Dangnhap.class));
            }
        });
    }
    private void getView(){
        btnPBan = findViewById(R.id.btnPBan);
        btnNV = findViewById(R.id.btnNV);
        btnExit = findViewById(R.id.btnExit);
        imghome = findViewById(R.id.imghome);
    }
}