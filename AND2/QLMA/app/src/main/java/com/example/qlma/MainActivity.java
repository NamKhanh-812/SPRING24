package com.example.qlma;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.qlma.Adapter.MonAnAdapter;

import com.example.qlma.model.MonAn;
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
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    RecyclerView rcvBaiTap;
    FloatingActionButton fltAdd;
    MonAnAdapter adapter;
    private ArrayList<MonAn> list = new ArrayList<>();
    FirebaseFirestore firebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rcvBaiTap = findViewById(R.id.rcvBaiTap);
        fltAdd = findViewById(R.id.fltAdd);
        // lấy toàn bộ dữ liệu từ bảng cng việc , add dữ liệu vào list
        firebaseFirestore = FirebaseFirestore.getInstance();
        listenfirebase();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rcvBaiTap.setLayoutManager(layoutManager);
        adapter = new MonAnAdapter(this, list,firebaseFirestore);
        rcvBaiTap.setAdapter(adapter);
        fltAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialogThem();
            }
        });
    }

    public void opendialogThem() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.item_add, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        EditText edtMa = view.findViewById(R.id.edtMaMonAnA);
        EditText edtTen = view.findViewById(R.id.edtTenMonAnA);
        EditText edtNgayLam = view.findViewById(R.id.edtNgayLamA);
        Button btnAdd = view.findViewById(R.id.btnThem);
        edtTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                builder1.setTitle("Chọn tên món");
                String[] ten = {"Mỳ xào chua cay", "Mỳ cay", "Mỳ hải sản", "Cơm tấm", "Bún bò sốt vang", "Bún chả"};
                builder1.setItems(ten, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        edtTen.setText(ten[which]);
                    }
                });
                AlertDialog dialog1 = builder1.create();
                dialog1.show();
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtMa = edtMa.getText().toString();
                String txtTen = edtTen.getText().toString();
                String txtNgayLam = edtNgayLam.getText().toString();

                String id = UUID.randomUUID().toString();
                MonAn monAn = new MonAn(id, txtMa, txtTen, txtNgayLam, 0);//tạo đối tượng
                //thêm cv vào database
                HashMap<String, Object> mapcongviec = monAn.converHashMap();
                firebaseFirestore.collection("MonAn").document(id).set(mapcongviec).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(MainActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Thất bại", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.dismiss();

            }
        });

    }
    private void listenfirebase() {
        firebaseFirestore.collection("MonAn").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    return;
                }
                if (value != null) {
                    for (DocumentChange dc : value.getDocumentChanges()) {
                        switch (dc.getType()) {
                            case ADDED://sự kiện khi có 1 document thêm vào
                                dc.getDocument().toObject(MonAn.class);
                                list.add(dc.getDocument().toObject(MonAn.class));
                                adapter.notifyItemInserted(list.size() - 1);
                                break;
                            case MODIFIED://sự kiện khi có 1 document update
                                MonAn monAn = dc.getDocument().toObject(MonAn.class);
                                //neu ở vị trí của đối tượng trùng với vị trí mới
                                if (dc.getOldIndex() == dc.getNewIndex()) {
                                    //set thay đổi của đối tượng chưa cập nhật(cũ) thành đối tượng đã cập nhật
                                    list.set(dc.getOldIndex(), monAn);
                                    //thông báo cho adapter có 1 đối tượng đã cập nhật
                                    adapter.notifyItemChanged(dc.getOldIndex());
                                } else {
                                    //nếu khác vị trí sẽ xóa đối tượng ở danh sách
                                    list.remove(dc.getOldIndex());
                                    //và thêm lại
                                    list.add(monAn);
                                    adapter.notifyItemMoved(dc.getOldIndex(), dc.getNewIndex());
                                }
                                break;
                            case REMOVED://sự kiện khi có 1 document bị xóa khỏi document
                                dc.getDocument().toObject(MonAn.class);
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