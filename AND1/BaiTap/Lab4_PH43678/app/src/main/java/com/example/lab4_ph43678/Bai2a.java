package com.example.lab4_ph43678;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Bai2a extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai2a);
        EditText edtTenSp = findViewById(R.id.edtTenSp);
        Button btnGui = findViewById(R.id.btnGui);
        TextView txtGia = findViewById(R.id.txtGia);
        txtGia.setText("0");
        ActivityResultLauncher<Intent> getQuote = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == 1) {
                            Intent intent = result.getData();
                            if (intent != null) {
                                Bundle bundle = intent.getExtras();
                                String priceQuote = bundle.getString("priceQuote");
                                txtGia.setText(priceQuote + " VND");
                                edtTenSp.setText("");
                            }
                        }
                    }
                }
        );
        btnGui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sanPham = edtTenSp.getText().toString();
                if(sanPham.isEmpty()){
                    Toast.makeText(Bai2a.this, "Bạn hãy nhập đồ cần mua!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(Bai2a.this, Bai2b.class);
                    intent.putExtra("TenSp", sanPham);
                    getQuote.launch(intent);
                }
            }
        });
    }
}