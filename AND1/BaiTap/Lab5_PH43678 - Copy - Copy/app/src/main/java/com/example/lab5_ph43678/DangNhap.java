package com.example.lab5_ph43678;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DangNhap extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dang_nhap);
        Button btnDN = findViewById(R.id.btnDN);
        Button btnDk = findViewById(R.id.btnDK);
        EditText edtNhap = findViewById(R.id.edtNhap);
        EditText edtMK = findViewById(R.id.edtMk);

        btnDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = getIntent();
                Bundle bundle = i.getExtras();
                if (bundle != null) {
                    String username = bundle.getString("Username");
                    String password = bundle.getString("Password");
                    Boolean u = username.equals(edtNhap.getText().toString());
                    Boolean p = password.equals(edtMK.getText().toString());
                    if (u && p) {
                        startActivity(new Intent(DangNhap.this, Home.class));
                        Toast.makeText(DangNhap.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(DangNhap.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(DangNhap.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnDk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangNhap.this, DangKy.class);
                startActivity(intent);
            }
        });
    }
}