package com.example.on;

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

import java.util.ArrayList;

public class DanhSach extends AppCompatActivity {
    ListView listView;
    private ArrayList<nguoi> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach);
        listView = findViewById(R.id.lstDs);
        Button btnAdd = findViewById(R.id.btnAdd);
        themdl();
        list = (ArrayList<nguoi>) Xfile.doc(DanhSach.this,"cb.txt");

        nguoiAdapter adapter = new nguoiAdapter(this,list);
        listView.setAdapter(adapter);
        ActivityResultLauncher<Intent> getdata = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode()==1){
                    Intent intent = result.getData();
                    if(intent!=null){
                        Bundle bundle = intent.getExtras();

                        String ma = bundle.getString("ma");
                        String hoTen = bundle.getString("hoTen");
                        String tuoi = bundle.getString("tuoi");

                        list.add(new nguoi(ma,hoTen,tuoi));
                        Xfile.ghi(DanhSach.this,"cb.txt",list);
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getdata.launch(new Intent(DanhSach.this,Them.class));
            }
        });

    }
    private void themdl(){
        list.add(new nguoi("utyre","tyrfsd","7654"));
        list.add(new nguoi("utyre","tyrfsd","7654"));
        list.add(new nguoi("utyre","tyrfsd","7654"));
        list.add(new nguoi("utyre","tyrfsd","7654"));
        list.add(new nguoi("utyre","tyrfsd","7654"));
        list.add(new nguoi("utyre","tyrfsd","7654"));
    }
}