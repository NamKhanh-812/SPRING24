package com.example.assignment_gd1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText edtUsername = findViewById(R.id.edtUsername);
        EditText edtPassword = findViewById(R.id.edtPassword);
        Button btnDangNhap = findViewById(R.id.btnDangNhap);
        Button btnDangKy = findViewById(R.id.btnDangKy);

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = getIntent();
                Bundle bundle = i.getExtras();
                if (bundle != null) {
                    String username = bundle.getString("Username");
                    String password = bundle.getString("Password");
                    Boolean a = username.equals(edtUsername.getText().toString());
                    Boolean b = password.equals(edtPassword.getText().toString());
                    if (a && b) {
                        Toast.makeText(Login.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Login.this, TrangChu.class));
                    }
                    else if(a){
                        Toast.makeText(Login.this, "Đăng nhập thất bại!\nSai mật khẩu.", Toast.LENGTH_SHORT).show();
                    } else if (b) {
                        Toast.makeText(Login.this, "Đăng nhập thất bại!\nSai tài khoản.", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(Login.this, "Đăng nhập thất bại!\nSai tài khoản và mật khẩu.", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(Login.this, "Đăng nhập thất bại!\nSai tài khoản hoặc mật khẩu!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, DangKy.class));
            }
        });
    }
}