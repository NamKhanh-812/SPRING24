package com.example.lab5_ph43678;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ThemMonAn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_mon_an);
        EditText edtTenMon = findViewById(R.id.edtTenMon);
        EditText edtGiaMon = findViewById(R.id.edtGiaMon);
        EditText edtDiaChi = findViewById(R.id.edtDiaChi);
        Button btnThemDs = findViewById(R.id.btnThemDs);
        btnThemDs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThemMonAn.this,DoAn.class);
                Bundle bundle = new Bundle();
                String tenMon = edtTenMon.getText().toString();
                String giaMon = edtGiaMon.getText().toString();
                String diaChi = edtDiaChi.getText().toString();
                bundle.putString("tenMon",tenMon);
                bundle.putString("giaMon",giaMon);
                bundle.putString("diaChi",diaChi);
                intent.putExtras(bundle);
                setResult(1,intent);
                finish();
            }
        });
    }
}