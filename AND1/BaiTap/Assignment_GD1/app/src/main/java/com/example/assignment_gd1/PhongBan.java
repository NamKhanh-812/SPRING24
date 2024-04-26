package com.example.assignment_gd1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.assignment_gd1.adapter.phongBanAdapter;
import com.example.assignment_gd1.model.phongban;

import java.util.ArrayList;

public class PhongBan extends AppCompatActivity {
    ListView lstPhongBan;
    private ArrayList<phongban> list = new ArrayList<phongban>();
    phongBanAdapter adapter= new phongBanAdapter(this,list);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phong_ban);
        //ánh xạ
        lstPhongBan=findViewById(R.id.lstPhongBan);
        list.add(new phongban("Nhân sự"));
        list.add(new phongban("Hành chính"));
        list.add(new phongban("Đào tạo"));
        lstPhongBan.setAdapter(adapter);
        Toolbar toolbar = findViewById(R.id.toolbarPhongBan);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        getSupportActionBar().setTitle("Phòng ban");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
    }
