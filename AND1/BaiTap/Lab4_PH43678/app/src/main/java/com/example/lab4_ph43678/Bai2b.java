package com.example.lab4_ph43678;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Bai2b extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai2b);
        Button btnBaoGia = findViewById(R.id.btnBaoGia);
        EditText edtNhapGia = findViewById(R.id.edtNhapGia);
        TextView txtSpKhachGui = findViewById(R.id.txtSpKhachGui);
        Intent intent = getIntent();
        String  price =  intent.getStringExtra("TenSp");
        txtSpKhachGui.setText(price);
        btnBaoGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String priceQuote  = edtNhapGia.getText().toString();
                if(priceQuote.isEmpty()){
                    Toast.makeText(Bai2b.this, "Vui lòng nhập giá!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putString("priceQuote",priceQuote);
                    intent.putExtras(bundle);
                    setResult(1,intent);
                    finish();
                }
            }
        });
    }
}