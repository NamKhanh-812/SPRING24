package com.example.on;

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
        EditText edtThemMa = findViewById(R.id.edtThemMa);
        EditText edtThemHT = findViewById(R.id.edtThemHT);
        EditText edtThemTuoi = findViewById(R.id.edtThemTuoi);
        Button btnThem =findViewById(R.id.btnThem);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Them.this,DanhSach.class);
                Bundle bundle = new Bundle();

                String ma = edtThemMa.getText().toString();
                String hoTen = edtThemHT.getText().toString();
                String tuoi = edtThemTuoi.getText().toString();

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