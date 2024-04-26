package com.example.thi;

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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    private ArrayList<nhanvien> list = new ArrayList<nhanvien>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.lstDS);
        Button btnAdd = findViewById(R.id.btnADD);
        list = (ArrayList<nhanvien>) Xfile.doc(MainActivity.this, "thi.txt");
        if (list == null) {
            list = new ArrayList<>();
            themdl();
        }
        nhanvienAdapter adapter = new nhanvienAdapter(this, list);
        listView.setAdapter(adapter);

        ActivityResultLauncher<Intent> getData = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == 1) {
                    Intent intent = result.getData();
                    if (intent != null) {
                        Bundle bundle = intent.getExtras();

                        String ma = bundle.getString("ma");
                        String hoTen = bundle.getString("hoTen");
                        String tuoi = bundle.getString("tuoi");

                        list.add(new nhanvien(ma, hoTen, tuoi));
                        Xfile.ghi(MainActivity.this, "thi.txt", list);
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData.launch(new Intent(MainActivity.this, Them.class));

            }
        });

    }

    private void themdl() {
        list.add(new nhanvien("tdrsa", "5643", "64534"));
        list.add(new nhanvien("tdra", "5643", "6454"));
        list.add(new nhanvien("tdrsa", "563", "64534"));
        list.add(new nhanvien("tdrsa", "5643", "64534"));
        list.add(new nhanvien("trsa", "5643", "64534"));
    }
}