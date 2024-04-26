package com.example.lab5_ph43678;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.lab5_ph43678.adapter.sinhvienAdapter;
import com.example.lab5_ph43678.model.sinhvien;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
ListView lstHome ;
private  ArrayList<sinhvien> list = new ArrayList<sinhvien>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // ánh xạ
        lstHome = findViewById(R.id.lstHome);
        Button btnAdd = findViewById(R.id.btnAdd);
        // thêm ds vào list
        list.add(new sinhvien("Fpoly Hà Nội","Họ tên: Nguyễn Huy Trung","Địa chỉ: 2 Lê Lợi"));
        list.add(new sinhvien("Fpoly Hồ Chí Minh","Họ tên: Hà Văn Lâm","Địa chỉ: 8 Lê Lợi"));
        //

        sinhvienAdapter adapter=new sinhvienAdapter(this,list);
        lstHome.setAdapter(adapter);

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
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getdata.launch(new Intent(Home.this, Bai1.class));
            }
        });
    }
}