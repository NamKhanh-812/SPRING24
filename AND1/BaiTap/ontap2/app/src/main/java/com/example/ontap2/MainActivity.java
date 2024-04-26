package com.example.ontap2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<sinhvien> list = new ArrayList<>();
    sinhvienadapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lv = findViewById(R.id.lv_main);
        Button btnthem = findViewById(R.id.btnthem_main);
//        themdl();
//
        if (list != null) {
            list = (ArrayList) xfile.doc(MainActivity.this, "sv.txt");
        }
        adapter = new sinhvienadapter(this, list);
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.them();
            }
        });

    }

    public void themdl() {
        list.add(new sinhvien("ph1", "hieu", "hai duong"));
        list.add(new sinhvien("ph2", "hung", "hanoi"));
        list.add(new sinhvien("ph3", "han", "hai duong"));
        list.add(new sinhvien("ph4", "tien", "hai duong"));


    }


}