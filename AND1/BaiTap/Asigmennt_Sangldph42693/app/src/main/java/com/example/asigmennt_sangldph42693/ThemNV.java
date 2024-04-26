package com.example.asigmennt_sangldph42693;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class ThemNV extends AppCompatActivity {

    private RadioGroup rdgCV;
    private RadioButton rdoHC;
    private RadioButton rdoDT;
    private RadioButton rdoNS;
    private TextInputEditText edtManv_add;
    private TextInputEditText edtTennv_add;
    private Button btnADD;
    private Button btnReset;
    private Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_nv);
        getView();
        btnADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThemNV.this, Nhanvien1.class);
                Bundle bundle = new Bundle();
                String MaNv = edtManv_add.getText().toString();
                String TenNv = edtTennv_add.getText().toString();
                if (MaNv.isEmpty() || TenNv.isEmpty()){
                    Toast.makeText(ThemNV.this, "Không được bỏ trống", Toast.LENGTH_SHORT).show();
                } else {
                        if (rdgCV.getCheckedRadioButtonId() == -1){
                            Toast.makeText(ThemNV.this, "Chưa chọn chức vụ", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            String CV = rdoDT.isChecked() ? "Đào tạo"
                                    : rdoHC.isChecked() ? "Hành chính"
                                    : "Nhân sự" ;
                            bundle.putString("CV",CV);
                            bundle.putString("Ma",MaNv);
                            bundle.putString("Ten",TenNv);
                            intent.putExtras(bundle);
                            setResult(1,intent);
                            finish();
                            Toast.makeText(ThemNV.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        }
                }
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtManv_add.setText("");
                edtTennv_add.setText("");
                rdgCV.clearCheck();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ThemNV.this, Nhanvien1.class));
            }
        });
    }
    private void getView(){
        rdgCV = findViewById(R.id.rdgCV);
        rdoDT = findViewById(R.id.rdoDT);
        rdoHC = findViewById(R.id.rdoHC);
        rdoNS = findViewById(R.id.rdoNS);
        edtManv_add = findViewById(R.id.edtMa_add);
        edtTennv_add = findViewById(R.id.edtTen_add);
        btnADD = findViewById(R.id.btnAdd);
        btnReset = findViewById(R.id.btnReset);
        btnBack = findViewById(R.id.btnBack1);
    }
}