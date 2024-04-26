package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.assignment.dao.NguoiDungDao;
import com.example.assignment.model.NguoiDung;

public class DangKy extends AppCompatActivity {
    private NguoiDungDao nguoiDungDao;
    private NguoiDung nguoiDung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        EditText edtTaiKhoan = findViewById(R.id.edtTaiKhoanDangKy);
        EditText edtMatKhau = findViewById(R.id.edtMatKhauDangKy);
        EditText edtRe_MatKhau = findViewById(R.id.edtMatKhauDangKy2);
        EditText edtFullName = findViewById(R.id.edtfullname);
        Button btnDangKy = findViewById(R.id.btnXacNhanDangKy);
        ImageButton ibtnBack = findViewById(R.id.ibtnBackDangKy);
        nguoiDungDao = new NguoiDungDao(this);
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtTaiKhoan.getText().toString();
                String password = edtMatKhau.getText().toString();
                String re_password = edtRe_MatKhau.getText().toString();
                String fullName = edtFullName.getText().toString();
                nguoiDung = new NguoiDung(username, password, fullName);

                if (username.isEmpty() || password.isEmpty() || re_password.isEmpty() || fullName.isEmpty()) {
                    Toast.makeText(DangKy.this, "Không được bỏ trống thông tin!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!password.equals(re_password)) {
                    Toast.makeText(DangKy.this, "Mật khẩu không trùng khớp!", Toast.LENGTH_SHORT).show();
                } else if (nguoiDungDao.checkusername(username)) {
                    Toast.makeText(DangKy.this, "Tài khoản đã có, hãy chọn tài khoản khác!", Toast.LENGTH_SHORT).show();
                } else {
                    if (nguoiDungDao.add(nguoiDung)) {
                        Toast.makeText(DangKy.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(DangKy.this, "Đăng ký thất bại!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        ibtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}