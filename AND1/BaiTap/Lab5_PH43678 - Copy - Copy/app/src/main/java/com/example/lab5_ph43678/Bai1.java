package com.example.lab5_ph43678;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.lab5_ph43678.adapter.schoolAdapter;
import com.example.lab5_ph43678.model.school;

import java.util.ArrayList;

public class Bai1 extends AppCompatActivity {
    Spinner spbai1;
    String tenCoSo;
    private ArrayList<school> list = new ArrayList<school>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai1);
        //ánh xạ
        spbai1 = findViewById(R.id.spinerdiachi);
        EditText edtTenSv = findViewById(R.id.edtTenSv);
        EditText edtDiaChiSv = findViewById(R.id.edtDiaChiSv);
        Button btnSubmit = findViewById(R.id.btnSubmit);

        // thêm đối tượng list
        list.add(new school(R.drawable.hn, "Hà Nội"));
        list.add(new school(R.drawable.hcm, "Hồ Chí Minh"));
        list.add(new school(R.drawable.hue, "Huế"));
        list.add(new school(R.drawable.tn, "Tây Nguyên"));
        list.add(new school(R.drawable.dn, "Đà Nẵng"));
        list.add(new school(R.drawable.ct, "Cần Thơ"));

        // đổ dữ liệu lên spiner
        schoolAdapter adapter = new schoolAdapter(this, list);
        spbai1.setAdapter(adapter);

        // gửi dữ liệu
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Bai1.this, Home.class);
                Bundle bundle = new Bundle();
                //
                String hoTenSv = edtTenSv.getText().toString();
                String diaChiSv = edtDiaChiSv.getText().toString();

                // gửi dữ liệu đi
                bundle.putString("tenCoSo", tenCoSo);
                bundle.putString("hoTenSv", hoTenSv);
                bundle.putString("diaChiSv", diaChiSv);

                //
                intent.putExtras(bundle);
                setResult(1, intent);
                finish();
            }
        });
        spbai1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tenCoSo = list.get(position).getTen();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}