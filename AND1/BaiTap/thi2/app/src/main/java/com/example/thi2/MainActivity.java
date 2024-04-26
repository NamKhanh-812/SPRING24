package com.example.thi2;

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

public class MainActivity extends AppCompatActivity {
    ListView listView;
    private ArrayList<person> list = new ArrayList<person>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.lvDS);
        Button btnADD = findViewById(R.id.btnAdd);
        list = (ArrayList<person>) Xfile.doc(MainActivity.this,"ik.txt");
        if(list==null){
            list = new ArrayList<>();
            themdl();
        }

        personAdapter adapter = new personAdapter(this, list);
        listView.setAdapter(adapter);

        ActivityResultLauncher<Intent> getData = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == 1) {
                    Intent intent = result.getData();
                    if (intent != null) {
                        Bundle bundle = intent.getExtras();

                        String ma = bundle.getString("ma");
                        String hoTen = bundle.getString("hoTen");
                        String tuoi = bundle.getString("tuoi");

                        list.add(new person(ma,hoTen,tuoi));
                        Xfile.ghi(MainActivity.this,"ik.txt",list);
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });
        btnADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData.launch(new Intent(MainActivity.this,Them.class));
            }
        });
    }
    private void themdl(){
        list.add(new person("5643","6543","64534"));
        list.add(new person("5643","6543","64534"));
        list.add(new person("5643","6543","64534"));
        list.add(new person("5643","6543","64534"));
        list.add(new person("5643","6543","64534"));
    }

}