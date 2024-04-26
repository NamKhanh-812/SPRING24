package com.example.asigmennt_sangldph42693;

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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.asigmennt_sangldph42693.adapter.nhanvienadapter;
import com.example.asigmennt_sangldph42693.model.nhanvien;

import java.util.ArrayList;
import java.util.List;

public class Nhanvien1 extends AppCompatActivity {
    private Toolbar menubarNV;
    private ListView lvnhanvien;
    private ImageView imgadd;
    private ImageView docfile;
    private ImageView ghifile;
    ArrayList<nhanvien> list = new ArrayList<nhanvien>();
    xfile file = new xfile();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhanvien1);
        getView();

        list.add(new nhanvien("NV001","Nguyễn Văn A","Hành chính"));
        list.add(new nhanvien("NV002","Nguyễn Văn B","Nhân sự"));
        list.add(new nhanvien("NV003","Nguyễn Văn C","Hành chính"));
        list.add(new nhanvien("NV004","Nguyễn Văn D","Đào tạo"));
        nhanvienadapter adapter = new nhanvienadapter(this,list);
        lvnhanvien.setAdapter(adapter);


        setSupportActionBar(menubarNV);
        getSupportActionBar().setTitle("Nhân viên");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        menubarNV.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ghifile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ghi file
                String path = "nhanvien.txt";
                file.ghifile(Nhanvien1.this,path,list);
                Toast.makeText(Nhanvien1.this, "Ghi file thành công", Toast.LENGTH_SHORT).show();
            }
        });

        docfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // đọc file
                String path = "nhanvien.txt";
                List<nhanvien> dsNhanVien = file.docfile(Nhanvien1.this,path);
                list.clear();
                list.addAll(dsNhanVien);
                adapter.notifyDataSetChanged();
                Toast.makeText(Nhanvien1.this, "Đọc file thành công", Toast.LENGTH_SHORT).show();
            }
        });


        ActivityResultLauncher<Intent> getdata = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode()==1){
                    Intent intent = result.getData();
                    if (intent != null){
                        Bundle bundle = intent.getExtras();
                        String ma = bundle.getString("Ma");
                        String ten = bundle.getString("Ten");
                        String cv = bundle.getString("CV");
                        list.add(new nhanvien(ma,ten,cv));
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });
        imgadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Nhanvien1.this, ThemNV.class);
                getdata.launch(intent);
                adapter.notifyDataSetChanged();
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.search){
            Toast.makeText(this, "Chức năng đang bổ sung", Toast.LENGTH_SHORT).show();


        } else if (item.getItemId() == R.id.add) {
            // ghi file
            Toast.makeText(this, "Chức năng đang bổ sung", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.exit){
            startActivity(new Intent(Nhanvien1.this, Dangnhap.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbarpb,menu);
        return super.onCreateOptionsMenu(menu);
    }


    private void getView(){
        menubarNV = findViewById(R.id.menubarNV);
        lvnhanvien = findViewById(R.id.lvnhanvien);
        imgadd = findViewById(R.id.imgadd);
        docfile = findViewById(R.id.docfile);
        ghifile = findViewById(R.id.ghifile);
    }
}