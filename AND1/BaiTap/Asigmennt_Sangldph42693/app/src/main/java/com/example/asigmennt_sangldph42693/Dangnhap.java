package com.example.asigmennt_sangldph42693;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class Dangnhap extends AppCompatActivity {
    private TextInputEditText txtUsername;
    private TextInputEditText txtPassword;
    private CheckBox chkSave;
    private Button btnSignin;
    private Button btnDangki;

    private ImageView imgHome;
    xfile file = new xfile();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);
        getView();
        Animation animation = AnimationUtils.loadAnimation(Dangnhap.this,R.anim.dichuyen);
        imgHome.startAnimation(animation);

        txtUsername.setText(file.readUser(Dangnhap.this,"user.txt").toString());
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        btnDangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dangnhap.this, Dangky.class));
            }
        });
        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bundle != null){
                    String User = bundle.getString("User");
                    String Pass = bundle.getString("Pass");
                    user taikhoan =  new user(User);
                     boolean u = User.equals(txtUsername.getText().toString());
                     boolean p = Pass.equals(txtPassword.getText().toString());

                     if(u && p){
                         Toast.makeText(Dangnhap.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                         file.writeUser(Dangnhap.this,"user.txt",taikhoan);
                         startActivity(new Intent(Dangnhap.this,Menu.class));
                     } else {
                         Toast.makeText(Dangnhap.this, "Tài khoản hoặc mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
                     }
                } else {

                    Toast.makeText(Dangnhap.this, "Vui lòng đăng ký tài khoản", Toast.LENGTH_SHORT).show();
                }
            }
        });
        chkSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Dangnhap.this, "Đã nhớ mật khẩu", Toast.LENGTH_SHORT).show();
            }
        });
    }
        private void getView(){
            txtUsername = findViewById(R.id.txtUsername);
            txtPassword = findViewById(R.id.txtPassword);
            chkSave = findViewById(R.id.chkSave);
            btnSignin = findViewById(R.id.btnSignin);
            btnDangki = findViewById(R.id.btnDangki);
            imgHome = findViewById(R.id.imgHome);
        }

}