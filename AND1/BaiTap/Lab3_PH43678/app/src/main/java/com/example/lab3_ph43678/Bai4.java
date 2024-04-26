package com.example.lab3_ph43678;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Bai4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bai4);
        EditText soA = findViewById(R.id.soA);
        EditText soB = findViewById(R.id.soB);
        EditText soC = findViewById(R.id.soC);
        Button btnGiai =findViewById(R.id.btnGiai);
        TextView txtKQ= findViewById(R.id.txtKQ);

        btnGiai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double a = Double.parseDouble(soA.getText().toString());
                double b = Double.parseDouble(soB.getText().toString());
                double c = Double.parseDouble(soC.getText().toString());
//                tính delta
                double delta = b * b - 4 * a * c;
//                trường hợp delta > , = , <
                if (delta > 0) {
                    double x1 = (-b + Math.sqrt(delta)) / (2 * a);
                    double x2 = (-b - Math.sqrt(delta)) / (2 * a);
                    txtKQ.setText("Phương trình có hai nghiệm x = " + x1 + " và x = " + x2);
                } else if (delta == 0) {
                    double x = -b / (2 * a);
                    txtKQ.setText("Phương trình có nghiệm kép x = " + x);
                } else {
                    txtKQ.setText("Phương trình vô nghiệm");
                }
            }
        });
    }
}