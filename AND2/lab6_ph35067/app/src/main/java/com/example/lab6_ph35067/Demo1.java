package com.example.lab6_ph35067;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Demo1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo1);
        EditText txtten = findViewById(R.id.txtten);
        Button btnstart = findViewById(R.id.btnstart);
        Button btnstop = findViewById(R.id.btnstop);
       // Intent intent = new Intent(this,Myservice.class);
        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSV();
            }
        });

        btnstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopSV();
            }
        });
    }
    public void startSV(){
        Intent intent = new Intent(this,Myservice.class);
        EditText txtten = findViewById(R.id.txtten);
        intent.putExtra("ten", txtten.getText().toString());
        startService(intent);
    }
    public void stopSV(){
        Intent intent = new Intent(this,Myservice.class);
        stopService(intent);
    }
}