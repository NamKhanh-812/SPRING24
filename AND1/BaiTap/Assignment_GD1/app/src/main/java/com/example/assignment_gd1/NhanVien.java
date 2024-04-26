package com.example.assignment_gd1;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.assignment_gd1.adapter.nhanvienAdapter;
import com.example.assignment_gd1.model.nhanvien;

import java.util.ArrayList;

public class NhanVien extends AppCompatActivity {
    ListView lstNhanVien;
    private ArrayList<nhanvien> list = new ArrayList<nhanvien>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhan_vien);
        // ánh xạ
        Toolbar toolbar = findViewById(R.id.toolbarNhanVien);
        setSupportActionBar(toolbar);
        lstNhanVien=findViewById(R.id.lstNhanVien);
        Button btnthemNv= findViewById(R.id.btnThemNv);

        nhanvienAdapter adapter = new nhanvienAdapter(this,list);
        ActivityResultLauncher<Intent> getdata =registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode()==1){
                    Intent intent = result.getData();
                    if(intent!=null){
                        Bundle bundle = intent.getExtras();
                        String maNv = bundle.getString("maNv");
                        String hoTen = bundle.getString("hoTen");
                        String tenPhongBan = bundle.getString("tenPhongBan");
                        list.add(new nhanvien("Mã NV: "+maNv,"Họ tên: "+hoTen,"Phòng ban: "+tenPhongBan));
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });
        btnthemNv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getdata.launch(new Intent(NhanVien.this,ThemNhanVien.class));
            }
        });
        lstNhanVien.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        getSupportActionBar().setTitle("Nhân viên");
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