package com.example.lab2_ph43678;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Bai3 extends AppCompatActivity{
        @Override
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.bai3);
            TextView txtXuat = findViewById(R.id.txtXuat);
            EditText edtName = findViewById(R.id.edtName);
            EditText edtMssv = findViewById(R.id.edtMssv);
            EditText edtTuoi = findViewById(R.id.edtTuoi);

            RadioButton rdoNam = findViewById(R.id.rdoNam);
            RadioButton rdoNu = findViewById(R.id.rdoNu);
            CheckBox chkDaBong = findViewById(R.id.chkDaBong);
            CheckBox chkChoiGame = findViewById(R.id.chkChoiGame);
            Button btnLuu = findViewById(R.id.btnLuu);
            btnLuu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = edtName.getText().toString().trim();
                    String mssv = edtMssv.getText().toString().trim();
                    String tuoi = edtTuoi.getText().toString().trim();
                    String gioiTinh = rdoNam.isChecked()
                            ?rdoNam.getText().toString()
                            :rdoNu.isChecked()
                            ?rdoNu.getText().toString()
                            :"Chưa chọn giới tính";

                    String soThich = chkChoiGame.isChecked() && chkDaBong.isChecked()
                            ?"Đá bóng và chơi game"
                            :chkChoiGame.isChecked()
                            ?chkChoiGame.getText().toString()
                            :chkDaBong.isChecked()
                            ?chkDaBong.getText().toString()
                            :"Không thích gì cả";
                    txtXuat.setText(
                            "Tôi tên: "+name+"\n" +
                                    "Mssv: "+mssv+"\n" +
                                    "Tuổi: "+tuoi+"\n" +
                                    "Giới tính: "+gioiTinh+"\n" +
                                    "Sở thích: "+soThich+""
                    );
                }
            });
        }
    }

