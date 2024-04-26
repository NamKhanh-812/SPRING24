package com.example.lab2_ph11.adapter;

import android.app.Activity;
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
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab2_ph11.R;

import com.example.lab2_ph11.model.congviec;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class cvAdapter extends RecyclerView.Adapter<cvAdapter.viewholder> {
    private final Context context;
    private final ArrayList<congviec> list;
    FirebaseFirestore database;

    public cvAdapter(Context context, ArrayList<congviec> list, FirebaseFirestore database) {
        this.context = context;
        this.list = list;
        this.database = database;
    }

    //gán layout
    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_cv, null);
        return new viewholder(view);
    }

    //cập nhật du liệu lên view
    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.txttieude.setText(list.get(position).getTieude());
        holder.txtnoidung.setText(list.get(position).getNoidung());
        holder.txtngay.setText(list.get(position).getNgay());
        holder.txtloai.setText(list.get(position).getLoai());
        if (list.get(position).getTrangthai() == 1) {
            holder.chkcv.setChecked(true);
        } else {
            holder.chkcv.setChecked(false);
        }


        //xóa
        congviec cv = list.get(position);//truy suất đến đối tượng đang chọn tại vt position
        holder.btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Cảnh báo");//set tiêu đề cho hộp thoại
                builder.setIcon(R.drawable.warning);//icon cho hộp thoại
                builder.setMessage("Bạn có muốn xóa công việc này không?");//chuỗi thông báo
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        database.collection("congviec").document(cv.getId()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
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
                      //  notifyDataSetChanged();


                    }
                });
                //nut no
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(context, "Ko xóa", Toast.LENGTH_SHORT).show();
                    }
                });
                //tạo và hiển thị hộp thoai
                AlertDialog dialog = builder.create();//tạo hộp thoại
                dialog.show();//hiển thị hộp thoại
            }
        });


        //update
        holder.btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opendialogsua(cv);
                // notifyItemChanged(holder.getAdapterPosition());
            }
        });
    }

    //trả về số lượng item hiển thi trên recyclerview
    @Override
    public int getItemCount() {
        return list.size();
    }

    //tạo class tĩnh
    public static class viewholder extends RecyclerView.ViewHolder {
        CheckBox chkcv;
        TextView txttieude, txtnoidung, txtngay, txtloai;
        ImageButton btnupdate, btndelete;

        //ánh xạ
        public viewholder(@NonNull View itemView) {
            super(itemView);
            chkcv = itemView.findViewById(R.id.chkcv);
            txttieude = itemView.findViewById(R.id.txttieude);
            txtnoidung = itemView.findViewById(R.id.txtnoidung);
            txtngay = itemView.findViewById(R.id.txtngay);
            txtloai = itemView.findViewById(R.id.txtloai);
            btndelete = itemView.findViewById(R.id.btndelete);
            btnupdate = itemView.findViewById(R.id.btnupdate);
        }
    }

    // opendialogsua
    public void opendialogsua(congviec cv) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);//tạo đối tượng của alerdialog
        //gán layout, tạo view
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_update, null);
        builder.setView(view);//gán view cho hộp thoại
        Dialog dialog = builder.create();//tạo hộp thoại
        dialog.show();//hiển thị hộp thoại lên màn hình
        //ánh xạ các thành phần widget
        EditText txttieudes = view.findViewById(R.id.txttieudes);
        EditText txtnoidungs = view.findViewById(R.id.txtnoidungs);
        EditText txtngays = view.findViewById(R.id.txtngays);
        EditText txtloais = view.findViewById(R.id.txtloais);
        Button btnsua = view.findViewById(R.id.btnsua);
        //gán dữ liệu lên các ô edittext
        txttieudes.setText(cv.getTieude());
        txtnoidungs.setText(cv.getNoidung());
        txtngays.setText(cv.getNgay());
        txtloais.setText(cv.getLoai());
        //xử lý event khi click txtloai
        txtloais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setTitle("Chọn mức độ khó của công việc");
                String[] loai = {"Dễ", "Trung bình", "Khó"};
                builder1.setItems(loai, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        txtloais.setText(loai[i]);
                    }
                });
                AlertDialog dialog1 = builder1.create();
                dialog1.show();
            }
        });

        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //cập nhật dữ liệu cho đối tượng được chọn
                cv.setTieude(txttieudes.getText().toString());
                cv.setNoidung(txtnoidungs.getText().toString());
                cv.setNgay(txtngays.getText().toString());
                cv.setLoai(txtloais.getText().toString());
                //update vào firebase
                database.collection("congviec").document(cv.getId()).update(cv.convertHaspmap()).addOnSuccessListener(new OnSuccessListener<Void>() {
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
              //  notifyDataSetChanged();
            }
        });

    }

}
