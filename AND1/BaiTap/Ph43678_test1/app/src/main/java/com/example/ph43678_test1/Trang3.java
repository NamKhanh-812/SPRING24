package com.example.ph43678_test1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Trang3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang3);
        EditText edtMaGv = findViewById(R.id.edtNhapMa);
        EditText edtHoTen = findViewById(R.id.edtNhapHoTen);
        Button btnThem = findViewById(R.id.btnThem);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Trang3.this, Trang2.class);
                Bundle bundle = new Bundle();
                //
                String maNv = edtMaGv.getText().toString();
                String hoTenNv = edtHoTen.getText().toString();

                // gửi dữ liệu đi
                bundle.putString("maNv", maNv);
                bundle.putString("hoTen", hoTenNv);

                //
                intent.putExtras(bundle);
                setResult(1, intent);
                finish();
            }
        });
    }
}