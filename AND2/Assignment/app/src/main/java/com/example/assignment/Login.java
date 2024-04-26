package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment.dao.NguoiDungDao;

import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;

public class Login extends AppCompatActivity {
    private NguoiDungDao nguoiDungDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText edtTaiKhoan = findViewById(R.id.edtTaiKhoanLogin);
        EditText edtMatKhau = findViewById(R.id.edtMatKhauLogin);
        Button btnDangNhap = findViewById(R.id.btnDangNhap);
        ImageView imgAnh = findViewById(R.id.anhdong);
        TextView txtForgot = findViewById(R.id.txtForgot);
        TextView txtSignUp = findViewById(R.id.txtSignUp);
        nguoiDungDao = new NguoiDungDao(this);

        try {
            GifDrawable gifDrawable = new GifDrawable(getResources(), R.drawable.anhdong);
            imgAnh.setImageDrawable(gifDrawable);
        } catch (IOException e) {
            e.printStackTrace();
        }
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String username = edtTaiKhoan.getText().toString();
                    String password = edtMatKhau.getText().toString();

                    if(username.isEmpty() || password.isEmpty()){
                        Toast.makeText(Login.this, "Tài khoản hoặc mật khẩu không chinh xác!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        if (nguoiDungDao.checkLogin(username, password)) {
                            Toast.makeText(Login.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Login.this, MainActivity.class));
                        } else {
                            Toast.makeText(Login.this, "Tài khoản hoặc mật khẩu không chinh xác!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        });
        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,DangKy.class));
            }
        });
    }
}