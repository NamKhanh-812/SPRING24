package com.example.asigmennt_sangldph42693;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class Dangky extends AppCompatActivity {

    private TextInputEditText txtUser;
    private TextInputEditText txtPass;
    private TextInputEditText txtComfirm;
    private Button btnDK;
    private Button btnBack;
    xfile file = new xfile();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);
        getView();
        btnDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = txtUser.getText().toString();
                String mk = txtPass.getText().toString();
                String comfirm = txtComfirm.getText().toString();
                boolean y = mk.equals(comfirm);

                if(ten.isEmpty() || mk.isEmpty() || comfirm.isEmpty()){
                    Toast.makeText(Dangky.this, "Không được bỏ trống", Toast.LENGTH_SHORT).show();
                } else if (!y) {
                    Toast.makeText(Dangky.this, "Password chưa đồng bộ", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(Dangky.this, Dangnhap.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("User",ten);
                    bundle.putString("Pass",mk);
                    intent.putExtras(bundle);
                    Toast.makeText(Dangky.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                    file.writeUser(Dangky.this,"user.txt",new user());
                    startActivity(intent);
                }
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dangky.this,Dangnhap.class));
            }
        });

    }
    private void getView(){
        txtUser = findViewById(R.id.txtUser);
        txtPass = findViewById(R.id.txtPass);
        txtComfirm = findViewById(R.id.txtComfirm);
        btnDK = findViewById(R.id.btnDK);
        btnBack = findViewById(R.id.btnBack);
    }

}