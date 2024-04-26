package com.example.lab2_ph43678;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.lab2_ph43678.adapter.cvAdapter;
import com.example.lab2_ph43678.model.CongViec;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    RecyclerView rcvCongViec;
    FloatingActionButton fltAdd;
    FirebaseFirestore database;
    cvAdapter adapter;
    CongViec congViec;
    private ArrayList<CongViec> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = FirebaseFirestore.getInstance();
        rcvCongViec = findViewById(R.id.rcvCongViec);
        fltAdd = findViewById(R.id.fltAdd);
        // lấy toàn bộ dữ liệu từ bảng cng việc , add dữ liệu vào list
        //set layout cho recylerView
        listenfirebase();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rcvCongViec.setLayoutManager(layoutManager);
        //đổ lữ liệu lên recylerView
        adapter = new cvAdapter(this, list,database);
        rcvCongViec.setAdapter(adapter);
        //xử lý nút add
        fltAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialogThem();
            }
        });
    }

    public void opendialogThem() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // gắn layout
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.item_add, null);
        builder.setView(view); //gắn view vào hộp thoại
        Dialog dialog = builder.create(); // tạo dialog
        dialog.show();
        // ánh xạ các thành phần
        EditText txtTitleA = view.findViewById(R.id.edtTitleA);
        EditText txtContentA = view.findViewById(R.id.edtContentA);
        EditText txtDateA = view.findViewById(R.id.edtDateA);
        EditText txtTypeA = view.findViewById(R.id.edtTypeA);
        Button btnThem = view.findViewById(R.id.btnThem);
        //
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = txtTitleA.getText().toString();
                String content = txtContentA.getText().toString();
                String date = txtDateA.getText().toString();
                String type = txtTypeA.getText().toString();
                String id = UUID.randomUUID().toString();
                 congViec = new CongViec(id, title, content, date, type, 0);//tạo đối tượng
                //thêm cv vào database
                HashMap<String, Object> mapcongviec = congViec.converHasmap();
                database.collection("congviec").document(id).set(mapcongviec).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(MainActivity.this, "insert succ", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Insert fail", Toast.LENGTH_SHORT).show();
                    }
                });
                //
                dialog.dismiss();
            }
        });
    }
    private void listenfirebase() {
        database.collection("congviec").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    return;
                }
                if (value != null) {
                    for (DocumentChange dc : value.getDocumentChanges()) {
                        switch (dc.getType()) {
                            case ADDED://sự kiện khi có 1 document thêm vào
                                dc.getDocument().toObject(CongViec.class);
                                list.add(dc.getDocument().toObject(CongViec.class));
                                adapter.notifyItemInserted(list.size() - 1);
                                break;
                            case MODIFIED://sự kiện khi có 1 document update
                                CongViec cvupdate = dc.getDocument().toObject(CongViec.class);
                                //neu ở vị trí của đối tượng trùng với vị trí mới
                                if (dc.getOldIndex() == dc.getNewIndex()) {
                                    //set thay đổi của đối tượng chưa cập nhật(cũ) thành đối tượng đã cập nhật
                                    list.set(dc.getOldIndex(), cvupdate);
                                    //thông báo cho adapter có 1 đối tượng đã cập nhật
                                    adapter.notifyItemChanged(dc.getOldIndex());
                                } else {
                                    //nếu khác vị trí sẽ xóa đối tượng ở danh sách
                                    list.remove(dc.getOldIndex());
                                    //và thêm lại
                                    list.add(cvupdate);
                                    adapter.notifyItemMoved(dc.getOldIndex(), dc.getNewIndex());
                                }
                                break;
                            case REMOVED://sự kiện khi có 1 document bị xóa khỏi document
                                dc.getDocument().toObject(CongViec.class);
                                list.remove(dc.getOldIndex());
                                adapter.notifyItemRemoved(dc.getOldIndex());
                                break;
                        }
                    }
                }
            }
        });
    }
}