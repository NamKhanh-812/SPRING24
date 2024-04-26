package com.example.assignment_gd1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.assignment_gd1.adapter.phongBanAdapter;
import com.example.assignment_gd1.model.phongban;

import java.util.ArrayList;

public class ThemNhanVien extends AppCompatActivity {
    Spinner spnPhongBan;
    String tenPhongBan;
    private ArrayList<phongban> list = new ArrayList<phongban>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_nhan_vien);
        // ánh xạ
        spnPhongBan=findViewById(R.id.spinerPhongBan);
        EditText edtMaNv = findViewById(R.id.edtNhapMaNv);
        EditText edtHoTen = findViewById(R.id.edtNhapHoTen);
        Button btnThem = findViewById(R.id.btnThem);

        list.add(new phongban("Nhân sự"));
        list.add(new phongban("Hành chính"));
        list.add(new phongban("Đào tạo"));

        phongBanAdapter adapter= new phongBanAdapter(this,list);
        spnPhongBan.setAdapter(adapter);
        // gửi dữ liệu
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThemNhanVien.this, NhanVien.class);
                Bundle bundle = new Bundle();
                //
                String maNv = edtMaNv.getText().toString();
                String hoTenNv = edtHoTen.getText().toString();

                // gửi dữ liệu đi
                bundle.putString("maNv", maNv);
                bundle.putString("hoTen", hoTenNv);
                bundle.putString("tenPhongBan", tenPhongBan);

                //
                intent.putExtras(bundle);
                setResult(1, intent);
                finish();
            }
        });
        spnPhongBan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tenPhongBan = list.get(position).getTenPhongBan();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}