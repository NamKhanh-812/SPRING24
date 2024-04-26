package com.example.ktlab8_ph43678;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MonHocAdapter extends RecyclerView.Adapter<MonHocAdapter.viewHolder>{
    private final Context context;
    private final ArrayList<MonHocModel> list;
    MonHocDao monHocDao;

    public MonHocAdapter(Context context, ArrayList<MonHocModel> list) {
        this.context = context;
        this.list = list;
        monHocDao = new MonHocDao(context);
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_mh,null);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.txtMaMonHoc.setText("Mã: "+list.get(position).getMaMonHoc());
        holder.txtTenMonHoc.setText("Tên môn học: "+list.get(position).getTenMonHoc());
        holder.txtSoTiet.setText("Số tiết: "+list.get(position).getSoTiet()+" tiết");
        MonHocModel monHocModel =list.get(position);
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn muốn xóa môn học này ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (monHocDao.delete(monHocModel.getMaMonHoc())) {
                            list.clear();
                            list.addAll(monHocDao.selectAll());
                            notifyDataSetChanged();
                            Toast.makeText(context, "Đã xóa !", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        }
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
        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDiaLog(monHocModel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{
    TextView txtTenMonHoc, txtSoTiet,txtMaMonHoc;
    Button btnUpdate, btnDelete;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaMonHoc = itemView.findViewById(R.id.txtMaMonHoc);
            txtTenMonHoc = itemView.findViewById(R.id.txtTenMonHoc);
            txtSoTiet = itemView.findViewById(R.id.txtSoTiet);
            btnUpdate = itemView.findViewById(R.id.btnUpdate);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
    public void openDiaLog(MonHocModel monHocModel) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_update, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        EditText edtTenMonHoc = view.findViewById(R.id.edtTenMonHocU);
        EditText edtSoTiet = view.findViewById(R.id.edtSoTietU);
        EditText edtMaMonHoc = view.findViewById(R.id.edtMaMonHocU);

        Button btnSua = view.findViewById(R.id.btnSua);
        edtMaMonHoc.setText(monHocModel.getMaMonHoc());
        edtTenMonHoc.setText(monHocModel.getTenMonHoc());
        edtSoTiet.setText(String.valueOf(monHocModel.getSoTiet()));
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mamh = edtMaMonHoc.getText().toString();
                String tenmh = edtTenMonHoc.getText().toString();
                String sotiet = edtSoTiet.getText().toString();


                if (mamh.isEmpty()||tenmh.isEmpty() || sotiet.isEmpty() ) {
                    Toast.makeText(context, "Không được bỏ trống", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(Integer.parseInt(sotiet)<=15){
                    Toast.makeText(context, "Phải lớn hơn 15", Toast.LENGTH_SHORT).show();
                    return;
                }
                monHocModel.setTenMonHoc(tenmh);
                monHocModel.setSoTiet(Integer.parseInt(sotiet));

                if (monHocDao.update(monHocModel)) {
                    list.clear();
                    list.addAll(monHocDao.selectAll());
                    notifyDataSetChanged();
                    dialog.dismiss();
                    Toast.makeText(context, "Update thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
