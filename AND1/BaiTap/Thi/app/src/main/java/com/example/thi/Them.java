package com.example.thi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Them extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them);
        EditText edtMa = findViewById(R.id.edtMa);
        EditText edtHoTen = findViewById(R.id.edtHoTen);
        EditText edtTuoi = findViewById(R.id.edtTuoi);
        Button btnThem = findViewById(R.id.btnThem);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();

                String ma = edtMa.getText().toString();
                String hoTen = edtHoTen.getText().toString();
                String tuoi = edtTuoi.getText().toString();

                bundle.putString("ma",ma);
                bundle.putString("hoTen",hoTen);
                bundle.putString("tuoi",tuoi);

                intent.putExtras(bundle);
                setResult(1,intent);
                finish();
            }
        });
    }
}