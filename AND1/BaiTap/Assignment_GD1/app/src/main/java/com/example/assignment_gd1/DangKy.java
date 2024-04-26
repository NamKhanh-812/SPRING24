package com.example.assignment_gd1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DangKy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        EditText edtUser = findViewById(R.id.edtUser);
        EditText edtPass = findViewById(R.id.edtPass);
        EditText edtPassCf = findViewById(R.id.edtPassCf);
        Button btnDK = findViewById(R.id.btnDK);
        Button btnTroVe = findViewById(R.id.btnTroVe);

        btnDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edtUser.getText().toString();
                String pass = edtPass.getText().toString();
                String confrim = edtPassCf.getText().toString();
                if(user.isEmpty() || pass.isEmpty() || confrim.isEmpty()){
                    Toast.makeText(DangKy.this, "Không được bỏ trống bất kì ô nào", Toast.LENGTH_SHORT).show();
                }
                else if(pass.equals(confrim)){
                    Toast.makeText(DangKy.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                    btnTroVe.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(DangKy.this,Login.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("Username",user);
                            bundle.putString("Password",pass);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    });
                }
                else {
                    Toast.makeText(DangKy.this, "Đăng ký thất bại!\nMKXN và MK không giống.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}