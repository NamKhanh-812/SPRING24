package com.example.asigmennt_sangldph42693;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.asigmennt_sangldph42693.adapter.phongbanadapter;
import com.example.asigmennt_sangldph42693.model.phongban;

import java.util.ArrayList;

public class Phongban1 extends AppCompatActivity {
    private Toolbar menubar;
    private ListView lvphongban;
    ArrayList<phongban> list = new ArrayList<phongban>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phongban1);
        getview();
        list.add(new phongban(R.drawable.home,"Nhân sự"));
        list.add(new phongban(R.drawable.home,"Hành chính"));
        list.add(new phongban(R.drawable.home,"Đào tạo"));
        phongbanadapter adapter =  new phongbanadapter(this,list);
        lvphongban.setAdapter(adapter);

        setSupportActionBar(menubar);
        getSupportActionBar().setTitle("Phòng Ban");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        menubar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        registerForContextMenu(lvphongban);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.search){
            Toast.makeText(this, "Chức năng đang bổ sung", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.add) {
            Toast.makeText(this, "Chức năng đang bổ sung", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.exit){
            startActivity(new Intent(Phongban1.this, Dangnhap.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contextmenu,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.xoa){
            Toast.makeText(this, "Vui lòng qua danh sách để xóa", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.them) {
            Toast.makeText(this, "Chức năng đang bổ sung", Toast.LENGTH_SHORT).show();
        }
        return super.onContextItemSelected(item);
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

    private void getview(){
        menubar = findViewById(R.id.menubar);
        lvphongban = findViewById(R.id.lvphongban);
    }
}