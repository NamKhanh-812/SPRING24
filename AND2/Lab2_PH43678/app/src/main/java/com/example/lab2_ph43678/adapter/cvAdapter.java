package com.example.lab2_ph43678.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab2_ph43678.R;
import com.example.lab2_ph43678.model.CongViec;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class cvAdapter extends RecyclerView.Adapter<cvAdapter.viewHolder> {
    private final Context context;
    private final ArrayList<CongViec> list;
    private final FirebaseFirestore database;

    public cvAdapter(Context context, ArrayList<CongViec> list, FirebaseFirestore database) {
        this.context = context;
        this.list = list;
        this.database = database;
    }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_cv, null);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.txtTitle.setText(list.get(position).getTitle());
        holder.txtContent.setText(list.get(position).getContent());
        holder.txtDate.setText(list.get(position).getDate());
        holder.txtType.setText(list.get(position).getType());
        if (list.get(position).getTrangthai() == 1) {
            holder.chkcv.setChecked(true);
        } else {
            holder.chkcv.setChecked(false);
        }
        //truy suất đến phần tử đc chọn
        CongViec congViec = list.get(position);
        // xóa
        holder.ibtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context); // tạo đối tượng
                builder.setTitle("Cảnh báo"); // set tiêu đề
                builder.setIcon(R.drawable.baseline_warning_24); // set icon
                builder.setMessage("Bạn có chắc chắn xóa không?");
                // set nút button yes
               builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       database.collection("congviec").document(congViec.getId()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                           @Override
                           public void onSuccess(Void unused) {
                               Toast.makeText(context, "xóa succ", Toast.LENGTH_SHORT).show();
                           }
                       }).addOnFailureListener(new OnFailureListener() {
                           @Override
                           public void onFailure(@NonNull Exception e) {
                               Toast.makeText(context, "xóa thất bại", Toast.LENGTH_SHORT).show();
                           }
                       });
                   }
               });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(context, "Ko xóa", Toast.LENGTH_SHORT).show();
                    }
                });
                // tạo dialog và show hôp thoại
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        // update
        holder.ibtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDiaLog(congViec);
            }
        });

    }

    // trả về số lượng phần tử hiển thị trên RecylerView
    @Override
    public int getItemCount() {
        return list.size();
    }

    // tạo class tĩnh
    public static class viewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle, txtContent, txtDate, txtType;
        CheckBox chkcv;
        ImageButton ibtnUpdate, ibtnDelete;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            chkcv = itemView.findViewById(R.id.chkCv);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtContent = itemView.findViewById(R.id.txtContent);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtType = itemView.findViewById(R.id.txtType);
            ibtnDelete = itemView.findViewById(R.id.ibtnDelete);
            ibtnUpdate = itemView.findViewById(R.id.ibtnUpdate);
        }
    }

    // opendialog sửa
    public void openDiaLog(CongViec congViec) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context); // tạo đối tượng
        // gắn layout và tạo view
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_update, null); // gắn layout
        builder.setView(view); // gắn view vào hộp thoại
        Dialog dialog = builder.create();
        dialog.show(); // hiển thị hộp thoại
        // ánh xạ các thành phần widget

        EditText txtTitleU = view.findViewById(R.id.edtTitleU);
        EditText txtContentU = view.findViewById(R.id.edtContentU);
        EditText txtDateU = view.findViewById(R.id.edtDateU);
        EditText txtTypeU = view.findViewById(R.id.edtTypeU);
        Button btnUpdate = view.findViewById(R.id.btnUpdate);

        // hiển thị dữ liệu lên các thành phần widget
        txtTitleU.setText(congViec.getTitle());
        txtContentU.setText(congViec.getContent());
        txtDateU.setText(congViec.getDate());
        txtTypeU.setText(congViec.getType());
        // xử lý sự kiện khi click vào edtTypeU
        txtTypeU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setTitle("Chọn mức độ công viêc");
                String[] loai = {"Dễ", "Trung bình", "Khó"};
                builder1.setItems(loai, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        txtTypeU.setText(loai[which]);
                    }
                });
                // tạo dialog , hiển thị dialog
                AlertDialog dialog1 = builder1.create();
                dialog1.show();
            }
        });
        // xử lý event khi click vào nút Update
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // cập nhật tt cho đối tượng
                congViec.setTitle(txtTitleU.getText().toString());
                congViec.setContent(txtContentU.getText().toString());
                congViec.setDate(txtDateU.getText().toString());
                congViec.setType(txtTypeU.getText().toString());
                // update dữ liệu trong database, load lại dữ liệu trong rcv
//update vào firebase
                database.collection("congviec").document(congViec.getId()).update(congViec.converHasmap()).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(context, "update succ", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "update fail", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.dismiss();
            }
        });
    }
    //hàm lắng nghe sự thay đổi dữ liệu trên firebase

}
