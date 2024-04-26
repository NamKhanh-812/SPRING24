package com.example.lab3_ph43678;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Bai5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bai5);
        Button btn = findViewById(R.id.btnDN);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Bai5.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
            }
        });
    }
}