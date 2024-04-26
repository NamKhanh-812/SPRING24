package com.example.lab5_ph43678;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lab5_ph43678.adapter.sinhvienAdapter;
import com.example.lab5_ph43678.model.sinhvien;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
ListView lstHome ;
private  ArrayList<sinhvien> list = new ArrayList<sinhvien>();


    sinhvienAdapter adapter=new sinhvienAdapter(this,list);
    // lắng nghe dữ liệu trả về
    ActivityResultLauncher<Intent>  getdata = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode()==1){
                Intent intent= result.getData();
                if(intent!=null){
                    Bundle bundle = intent.getExtras();
                    String hoTenSv = bundle.getString("hoTenSv");
                    String diaChiSv= bundle.getString("diaChiSv");
                    String tenCoSo = bundle.getString("tenCoSo");
                    list.add(new sinhvien(tenCoSo,"Họ tên: "+hoTenSv,"Địa chỉ: "+diaChiSv));
                    adapter.notifyDataSetChanged();
                }
            }
        }
    });



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        getSupportActionBar().setTitle("Home");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        lstHome = findViewById(R.id.lstHome);
        lstHome.setAdapter(adapter);

        if(item.getItemId()==R.id.themMoiSv){
            getdata.launch(new Intent(Home.this, Bai1.class));
        } else if (item.getItemId()==R.id.dangXuat) {
            finish();
        }
        else if(item.getItemId()==R.id.lichHoc){
            Toast.makeText(this, "Lịch học", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId()==R.id.bangDiem) {
            Toast.makeText(this, "Bảng điểm", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId()==R.id.diemdanh) {
            Toast.makeText(this, "Điểm danh", Toast.LENGTH_SHORT).show();
        }
        else if(item.getItemId()==R.id.timKiem){
            EditText edtTimKiem = findViewById(R.id.timKiem);
            edtTimKiem.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    adapter.getFilter().filter(s);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }else if(item.getItemId()==android.R.id.home){
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // ánh xạ
        lstHome = findViewById(R.id.lstHome);
        Button btnAdd = findViewById(R.id.btnAdd);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // thêm ds vào list
        list.add(new sinhvien("Fpoly Hà Nội","Họ tên: Nguyễn Huy Trung","Địa chỉ: 2 Lê Lợi"));
        list.add(new sinhvien("Fpoly Hồ Chí Minh","Họ tên: Hà Văn Lâm","Địa chỉ: 8 Lê Lợi"));
        //

        lstHome.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)  {
                getdata.launch(new Intent(Home.this, Bai1.class));
            }
        });
    }
}