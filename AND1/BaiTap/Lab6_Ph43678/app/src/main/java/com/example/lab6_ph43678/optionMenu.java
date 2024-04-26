package com.example.lab6_ph43678;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class optionMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_menu);
        // ánh xạ
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); // gán toolbar thay thế cho actionBar
        getSupportActionBar().setTitle("Poly");
        getSupportActionBar().setSubtitle("Polytechnic");
        getSupportActionBar().setLogo(R.drawable.baseline_search_24);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    // gán optionMenu lên toolbar

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mn_option,menu);
        return super.onCreateOptionsMenu(menu);
    }
    // Xử lý sự kiện khi chọn item trên menu

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.lichHoc){
            Toast.makeText(this, "Lịch học", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId()==R.id.bangDiem) {
            Toast.makeText(this, "Bảng điểm", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}