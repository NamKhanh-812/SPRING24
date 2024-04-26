package com.example.lab2_ph11;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lab2_ph11.adapter.cvAdapter;

import com.example.lab2_ph11.model.congviec;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    RecyclerView rcvcongviec;
    FloatingActionButton fltadd;
    private ArrayList<congviec> list = new ArrayList<congviec>();
    cvAdapter adapter;

    //tao bien toan cuc firebare
    FirebaseFirestore database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ánh xạ
        rcvcongviec = findViewById(R.id.rcvcongviec);
        fltadd = findViewById(R.id.fltadd);
        //cvdao = new congviecDao(this);
        // list = cvdao.selectAll();//lấy toàn bộ dữ liệu từ bảng congviec,add vao list
        //kết nối database hiện tại
        database = FirebaseFirestore.getInstance();
        //gọi hàm lắng nghe cập nhật
        listenfirebase();
        //đổ dữ liệu lên recyclerview
        adapter = new cvAdapter(this, list, database);
        LinearLayoutManager layout = new LinearLayoutManager(this);
        //GridLayoutManager layoutManager=new GridLayoutManager(this,2);
        rcvcongviec.setLayoutManager(layout);
        rcvcongviec.setAdapter(adapter);
        //xư ly event khi click add
        fltadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opendialogthem();
            }
        });
    }

    //opendialogthem
    public void opendialogthem() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);//tạo đối tượng của alerdialog
        //gán layout, tạo view
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.item_add, null);
        builder.setView(view);//gán view cho hộp thoại
        Dialog dialog = builder.create();//tạo hộp thoại
        dialog.show();//hiển thị hộp thoại lên màn hình
        //ánh xạ các thành phần widget
        EditText txttieudea = view.findViewById(R.id.txttieudea);
        EditText txtnoidunga = view.findViewById(R.id.txtnoidunga);
        EditText txtngaya = view.findViewById(R.id.txtngaya);
        EditText txtloaia = view.findViewById(R.id.txtloaia);
        Button btnthem = view.findViewById(R.id.btnthem);
        //xu lý evvent khi click them
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tieude = txttieudea.getText().toString();
                String noidung = txtnoidunga.getText().toString();
                String ngay = txtngaya.getText().toString();
                String loai = txtloaia.getText().toString();
                String id = UUID.randomUUID().toString();
                congviec cv = new congviec(id, tieude, noidung, ngay, loai, 0);//tạo đối tượng
                //thêm cv vào database
                HashMap<String, Object> mapcongviec = cv.convertHaspmap();
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


    //hàm lắng nghe sự thay đổi dữ liệu trên firebase
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
                                dc.getDocument().toObject(congviec.class);
                                list.add(dc.getDocument().toObject(congviec.class));
                                adapter.notifyItemInserted(list.size() - 1);
                                break;
                            case MODIFIED://sự kiện khi có 1 document update
                                congviec cvupdate = dc.getDocument().toObject(congviec.class);
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
                                dc.getDocument().toObject(congviec.class);
                                list.remove(dc.getOldIndex());
                                adapter.notifyItemRemoved(dc.getOldIndex());
                                break;
                        }
                    }
                }
            }
        });
    }
    //
}