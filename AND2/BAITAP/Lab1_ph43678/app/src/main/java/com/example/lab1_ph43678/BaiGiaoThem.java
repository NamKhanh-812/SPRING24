package com.example.lab1_ph43678;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lab1_ph43678.adapter.ThemAdapter;
import com.example.lab1_ph43678.dao.ThemDao;
import com.example.lab1_ph43678.model.Them;

import java.util.ArrayList;

public class BaiGiaoThem extends AppCompatActivity {
ListView listView;
private ArrayList<Them> list = new ArrayList<>();
ThemDao themDao;
ThemAdapter adapter;
Them them;
EditText edtMaSV, edtHoTen, edtSDT, edtQueQuan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai_giao_themy2);
        listView = findViewById(R.id.lsvDS);
        themDao = new ThemDao(this);
        edtMaSV = findViewById(R.id.edtMaSV);
        edtHoTen = findViewById(R.id.edtHoTen);
        edtSDT = findViewById(R.id.edtSDT);
        edtQueQuan = findViewById(R.id.edtQueQuan);
        Button btnAdd = findViewById(R.id.btnThem);
        Button btnUpdate = findViewById(R.id.btnSua);
        Button btnDelete = findViewById(R.id.btnXoa);

        list = themDao.selectAll();
        adapter = new ThemAdapter(this,list);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maSV = edtMaSV.getText().toString();
                String hoTen = edtHoTen.getText().toString();
                String sdt = edtSDT.getText().toString();
                String queQuan = edtQueQuan.getText().toString();

                them = new Them(maSV,hoTen,sdt,queQuan);
                if(themDao.insert(them)){
                    list.clear();
                    list.addAll(themDao.selectAll());
                    adapter.notifyDataSetChanged();
                    Toast.makeText(BaiGiaoThem.this, " Thêm thành công", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(BaiGiaoThem.this, "Lỗi", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                them.setMaSV(edtMaSV.getText().toString());
                them.setHoTen(edtHoTen.getText().toString());
                them.setSdt(edtSDT.getText().toString());
                them.setQueQuan(edtQueQuan.getText().toString());
                if(themDao.update(them)){
                    list.clear();
                    list.addAll(themDao.selectAll());
                    adapter.notifyDataSetChanged();
                    Toast.makeText(BaiGiaoThem.this, "Update thành công", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(BaiGiaoThem.this, "Lỗi", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(themDao.delete(them.getId())){
                    list.clear();
                    list.addAll(themDao.selectAll());
                    adapter.notifyDataSetChanged();
                    reset();
                    Toast.makeText(BaiGiaoThem.this, "Delete thành công", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(BaiGiaoThem.this, "Lỗi", Toast.LENGTH_SHORT).show();
                }
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                them = list.get(position);//truy xuất đến đối tượng được chọn
                edtMaSV.setText(them.getMaSV());
                edtHoTen.setText(them.getHoTen());
                edtSDT.setText(them.getSdt());
                edtQueQuan.setText(them.getQueQuan());
            }
        });
    }
    public void reset(){
        edtMaSV.setText("");
        edtHoTen.setText("");
        edtSDT.setText("");
        edtQueQuan.setText("");
    }
}