package com.example.thithu3;

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

public class Dongvat extends AppCompatActivity {

    ArrayList<dv> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dongvat);
        Button btnadd = findViewById(R.id.btnadd);
        ListView lvdongvat = findViewById(R.id.lvds);
        list = (ArrayList<dv>) xfile.docfile(Dongvat.this,"dv.txt");
        if(list == null){
            list = new ArrayList<>();
            themds();
        }

        dvadapter adapter = new dvadapter(this,list);
        lvdongvat.setAdapter(adapter);

        ActivityResultLauncher<Intent> getdata = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode()==1){
                    Intent intent = result.getData();
                    if (intent!= null){
                        Bundle bundle = intent.getExtras();
                        String ma = bundle.getString("ma");
                        String ten = bundle.getString("ten");
                        Double cannang = bundle.getDouble("cnang");
                        int soluong = bundle.getInt("soluong");
                        list.add(new dv(ma,ten,cannang,soluong));
                        xfile.ghifile(Dongvat.this,"dv.txt",list);
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dongvat.this, Themdv.class);
                getdata.launch(intent);
            }
        });
    }
    private void themds (){
        list.add(new dv("DV1","Chó",22.4,5));
        list.add(new dv("DV2","Mèo",12.3,2));
        list.add(new dv("DV3","Lợn",72.8,8));
        list.add(new dv("DV4","Gà",2.4,50));
        list.add(new dv("DV5","Bò",122.4,1));
    }
}