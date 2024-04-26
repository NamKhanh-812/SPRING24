package com.example.ph43678_test1;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.ph43678_test1.adapter.gvAdapter;
import com.example.ph43678_test1.model.gv;

import java.util.ArrayList;

public class Trang2 extends AppCompatActivity {
    ListView lstGv;
    private ArrayList<gv> list = new ArrayList<gv>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang2);
        lstGv = findViewById(R.id.lstDanhSach);
        Button btnThemGv = findViewById(R.id.btnThemDs);
        list.add(new gv("Mã GV: GV0856","Tần Văn Kiên"));
        list.add(new gv("Mã GV: GV9856","Hoàng Thị Kim"));
        gvAdapter adapter = new gvAdapter(this, list);
        ActivityResultLauncher<Intent> getdata = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == 1) {
                    Intent intent = result.getData();
                    if (intent != null) {
                        Bundle bundle = intent.getExtras();
                        String maGv = bundle.getString("maNv");
                        String hoTen = bundle.getString("hoTen");
                        list.add(new gv("Mã GV: " + maGv, "Họ tên: " + hoTen));
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });
        btnThemGv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getdata.launch(new Intent(Trang2.this, Trang3.class));
            }
        });
        lstGv.setAdapter(adapter);
    }
}