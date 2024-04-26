package com.example.lab6_ph43678;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnb1=findViewById(R.id.btnb1);
        Button btnb2=findViewById(R.id.btnb2);
        Button btnb3=findViewById(R.id.btnb3);
        Button btnBanDo = findViewById(R.id.btnBanDo);
        btnb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, optionMenu.class);
                startActivity(intent);
            }
        });
        btnb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ContextMenu.class);
                startActivity(intent);
            }
        });
        btnb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ContextMenu.class);
                startActivity(intent);
            }
        });

    }
}