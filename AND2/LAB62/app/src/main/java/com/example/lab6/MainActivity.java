package com.example.lab6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txtten;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnstart = findViewById(R.id.btnstart);
        Button btnstop = findViewById(R.id.btnstop);
        txtten = findViewById(R.id.txtten);

        // Intent intent = new Intent(this, MyService.class);
        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // startService(intent);
                startSv();
            }
        });
        btnstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //    stopService(intent);
                stopSv();
            }
        });
    }

    public void startSv() {
        Intent intent = new Intent(this, MyServicedemo3.class);
        intent.putExtra("ten", txtten.getText().toString());
        startService(intent);
    }

    public void stopSv() {
        Intent intent = new Intent(this, MyServicedemo3.class);
        stopService(intent);
    }
}