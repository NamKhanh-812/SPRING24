package com.example.lab8_ph43678;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lstnv;
    ArrayList<nhanvien> listnv = new ArrayList<>();
    nhanvienAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ánh xạ
        lstnv = findViewById(R.id.lstnv);
        Button btnAdd = findViewById(R.id.btnadd);
        themDl();


        adapter = new nhanvienAdapter(this, listnv);
        lstnv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialogthem();
                xfile.ghifile(MainActivity.this, "nv.txt", listnv);
            }
        });

        // đọc file
        if (listnv != null) {
            listnv = (ArrayList<nhanvien>) xfile.docfile(MainActivity.this, "nv.txt");
        }
    }

    public void themDl() {
        listnv.add(new nhanvien("S01", "Nguyễn", "Hà nội"));
        listnv.add(new nhanvien("S02", "Ánh", "Hà nội"));
        listnv.add(new nhanvien("S03", "Hoàng", "Hà nội"));
    }
    public void opendialogthem() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        View view = getLayoutInflater().inflate(R.layout.itemm_add, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        //anh xa
        EditText txtmanv = view.findViewById(R.id.txtmanv);
        EditText txthoten = view.findViewById(R.id.txthoten);
        EditText txtdiachi = view.findViewById(R.id.txtdiachi);
        Button btnthem = view.findViewById(R.id.btnthem);

        //click thêm
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String manv = txtmanv.getText().toString();
                String hoten = txthoten.getText().toString();
                String diachi = txtdiachi.getText().toString();
                listnv.add(new nhanvien(manv, hoten, diachi));
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
    }

}