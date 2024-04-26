package com.example.lab6_ph43678;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ContextMenu extends AppCompatActivity {
    String[] ten = new String[]{"Hoa","Trang","Thúy","Trung","Linh"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context_menu);
        // ánh xạ
        ListView lstTen = findViewById(R.id.lstVd2);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,ten);
        //
        lstTen.setAdapter(adapter);
        //đăng ký contextMenu
        registerForContextMenu(lstTen);
    }

    @Override
    public void onCreateContextMenu(android.view.ContextMenu menu, View v, android.view.ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.mn_context,menu);

        super.onCreateContextMenu(menu, v, menuInfo);
    }
    //

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.xoa){
            Toast.makeText(this, "Đã xóa", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId()==R.id.update) {
            Toast.makeText(this, "Update", Toast.LENGTH_SHORT).show();
        }
        return super.onContextItemSelected(item);
    }
}