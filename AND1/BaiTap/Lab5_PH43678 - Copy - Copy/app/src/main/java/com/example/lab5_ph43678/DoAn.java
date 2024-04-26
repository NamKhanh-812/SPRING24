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

import com.example.lab5_ph43678.R;
import com.example.lab5_ph43678.adapter.monAnAdapter;
import com.example.lab5_ph43678.model.monAn;

import java.util.ArrayList;

public class DoAn extends AppCompatActivity {
    ListView lstDoAn;
    private ArrayList<monAn> list = new ArrayList<monAn>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_an);
        lstDoAn=findViewById(R.id.lstDsDoAn);
        Button btnThemMon = findViewById(R.id.btnThemMon);

        monAnAdapter adapter = new monAnAdapter(this,list);
        lstDoAn.setAdapter(adapter);
        ActivityResultLauncher<Intent> getData = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode()==1){
                    Intent  intent = result.getData();
                    if(intent!=null){
                        Bundle  bundle = intent.getExtras();
                        String tenMon = bundle.getString("tenMon");
                        String giaMon = bundle.getString("giaMon");
                        String diaChi = bundle.getString("diaChi");
                        list.add(new monAn(tenMon,giaMon,diaChi));
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });
        btnThemMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoAn.this, ThemMonAn.class);
                getData.launch(intent);
            }
        });
    }
}