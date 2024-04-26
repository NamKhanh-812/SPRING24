package com.example.lab1_ph43678;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lab1_ph43678.adapter.CongViecAdapter;
import com.example.lab1_ph43678.dao.CongViecDao;
import com.example.lab1_ph43678.model.CongViec;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    private ArrayList<CongViec> list = new ArrayList<CongViec>();
    CongViecDao cvDao;
    CongViecAdapter adapter;
    CongViec cv;
    EditText edtTitle, edtContent, edtDate, edtType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Ánh xạ
        listView = findViewById(R.id.lvCV);
        cvDao = new CongViecDao(this);
        edtTitle = findViewById(R.id.edtTitle);
        edtContent = findViewById(R.id.edtContent);
        edtDate = findViewById(R.id.edtDate);
        edtType = findViewById(R.id.edtType);
        Button btnThem = findViewById(R.id.btnAdd);
        Button btnUpdate = findViewById(R.id.btnUpdate);
        Button btnDelete = findViewById(R.id.btnDelete);
        //
        list = cvDao.selectAll();
        adapter = new CongViecAdapter(this, list);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        //xử lý sk cho nút button add

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String title = edtTitle.getText().toString();
               String content = edtContent.getText().toString();
               String date = edtDate.getText().toString();
               String type = edtType.getText().toString();
               cv = new CongViec(title,content,date,type); // tạo đối tượng
               if(cvDao.add(cv)){
                   list.clear();
                   list.addAll(cvDao.selectAll());
                   adapter.notifyDataSetChanged();
                   Toast.makeText(MainActivity.this, " Thêm thành công", Toast.LENGTH_SHORT).show();
               }
               else {
                   Toast.makeText(MainActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
               }
            }
        });

        // update

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // cập nhật thông tin cho đối tượng
                cv.setTitle(edtTitle.getText().toString());
                cv.setContent(edtContent.getText().toString());
                cv.setDate(edtDate.getText().toString());
                cv.setType(edtType.getText().toString());
                if(cvDao.update(cv)){
                    list.clear();
                    list.addAll(cvDao.selectAll());
                    adapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Update thành công", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // delete
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cvDao.delete(cv.getId())){
                    list.clear();
                    list.addAll(cvDao.selectAll());
                    adapter.notifyDataSetChanged();
                    reset();
                    Toast.makeText(MainActivity.this, "Delete thành công", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // xử lý event khi click vào item trên listView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cv = list.get(position);//truy xuất đến đối tượng được chọn
                edtTitle.setText(cv.getTitle());
                edtContent.setText(cv.getContent());
                edtDate.setText(cv.getDate());
                edtType.setText(cv.getType());
            }
        });


    }
    public void reset(){
        edtTitle.setText("");
        edtContent.setText("");
        edtDate.setText("");
        edtType.setText("");
    }
}