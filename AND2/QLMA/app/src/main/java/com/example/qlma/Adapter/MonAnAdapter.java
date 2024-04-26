package com.example.qlma.Adapter;

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
import androidx.recyclerview.widget.RecyclerView;

import com.example.qlma.R;
import com.example.qlma.model.MonAn;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.ArrayList;

public class MonAnAdapter extends RecyclerView.Adapter<MonAnAdapter.viewHolder> {
    private final Context context;
    private final ArrayList<MonAn> list;
    FirebaseFirestore firebaseFirestore;

    public MonAnAdapter(Context context, ArrayList<MonAn> list, FirebaseFirestore firebaseFirestore) {
        this.context = context;
        this.list = list;
        this.firebaseFirestore = firebaseFirestore;
    }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_bai_tap, null);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.txtMaMonAn.setText("Mã: "+list.get(position).getMaMonAn());
        holder.txtTenMonAn.setText("Món: "+list.get(position).getTenMonAn());
        holder.txtNgayLam.setText("Ngày: "+list.get(position).getNgayLam());
        if (list.get(position).getTrangThai() == 1) {
            holder.chkMonAn.setChecked(true);
        } else {
            holder.chkMonAn.setChecked(false);
        }


        MonAn monAn = list.get(position);


        holder.ibtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Cảnh báo");
                builder.setIcon(R.drawable.baseline_warning_24);
                builder.setMessage("Bạn có chắc chắn muốn xóa không?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        firebaseFirestore.collection("MonAn").document(monAn.getId()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(context, "Xóa thành công!", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

        holder.ibtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDiaLog(monAn);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        TextView txtMaMonAn, txtTenMonAn, txtNgayLam;
        CheckBox chkMonAn;
        ImageButton ibtnUpdate, ibtnDelete;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaMonAn = itemView.findViewById(R.id.txtMaMonAn);
            txtTenMonAn = itemView.findViewById(R.id.txtTenMonAn);
            txtNgayLam = itemView.findViewById(R.id.txtNgayLam);
            chkMonAn = itemView.findViewById(R.id.chkMon);
            ibtnUpdate = itemView.findViewById(R.id.ibtnUpdate);
            ibtnDelete = itemView.findViewById(R.id.ibtnDelete);
        }
    }

    public void openDiaLog(MonAn monAn) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_update, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();


        EditText txtMaMonAn = view.findViewById(R.id.edtMaMonAnU);
        EditText txtTenMonAn = view.findViewById(R.id.edtTenMonAnU);
        EditText txtNgayLam = view.findViewById(R.id.edtNgayLamU);
        Button btnUpdate = view.findViewById(R.id.btnUpdate);

        txtMaMonAn.setText(monAn.getMaMonAn());
        txtTenMonAn.setText(monAn.getTenMonAn());
        txtNgayLam.setText(monAn.getNgayLam());

        txtTenMonAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setTitle("Chọn tên món");
                String[] ten = {"Mỳ xào chua cay", "Mỳ cay", "Mỳ hải sản", "Cơm tấm", "Bún bò sốt vang", "Bún chả"};
                builder1.setItems(ten, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        txtTenMonAn.setText(ten[which]);
                    }
                });
                AlertDialog dialog1 = builder1.create();
                dialog1.show();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monAn.setMaMonAn(txtMaMonAn.getText().toString());
                monAn.setTenMonAn(txtTenMonAn.getText().toString());
                monAn.setNgayLam(txtNgayLam.getText().toString());
                firebaseFirestore.collection("MonAn").document(monAn.getId()).update(monAn.converHashMap()).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(context, "Update thành công", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Update thất bại", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.dismiss();
            }
        });
    }
}
