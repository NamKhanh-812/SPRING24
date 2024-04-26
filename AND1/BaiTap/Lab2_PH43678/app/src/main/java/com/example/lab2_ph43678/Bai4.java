package com.example.lab2_ph43678;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Bai4 extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bai4);
        TextView txtXuat = findViewById(R.id.txtRa);
        EditText edtName = findViewById(R.id.edtTen);
        EditText edtMssv = findViewById(R.id.edtMsv);
        EditText edtTuoi = findViewById(R.id.edtTuoii);

        RadioButton rdoNam = findViewById(R.id.rdoTrai);
        RadioButton rdoNu = findViewById(R.id.rdoGai);
        CheckBox chkDaBong = findViewById(R.id.chkDB);
        CheckBox chkChoiGame = findViewById(R.id.chkGame);
        Button btnLuu = findViewById(R.id.btnSave);
        Button btnXem = findViewById(R.id.btnXem);
        TextView txtTT = findViewById(R.id.txtTT);
        btnXem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtTT.setText(
                        "Tên: Khánh\n" +
                                "Tuổi: 18\n" +
                                "MSV: PH43678\n" +
                                "Giới tính: Nam\n" +
                                "Sở thích: Chơi game"
                );
            }
        });
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

