package com.example.ph43678_1206;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class Trang2 extends AppCompatActivity {
    ListView lstView;
    private ArrayList<KhachHang> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang2);
        lstView = findViewById(R.id.lstDanhSach);
        list.add(new KhachHang("Mã KH: KH0856","Tần Văn Kiên","29","0963782976"));
        list.add(new KhachHang("Mã KH: KH9856","Hoàng Thị Kim","37","0876942976"));
        list.add(new KhachHang("Mã KH: KH056","Phạm Minh Hiếu","19","0987582976"));
        list.add(new KhachHang("Mã KH: KH2656","Vũ Thị Vân Anh","20","0876942976"));
        list.add(new KhachHang("Mã KH: KH856","Đỗ Đức Việt","19","0876942976"));
        KhachHangAdapter adapter = new KhachHangAdapter(this, list);
        lstView.setAdapter(adapter);
    }
}