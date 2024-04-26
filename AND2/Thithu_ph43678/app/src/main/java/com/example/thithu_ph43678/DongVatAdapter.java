package com.example.thithu_ph43678;

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

public class DongVatAdapter extends RecyclerView.Adapter<DongVatAdapter.viewHolder> {
    private final Context context;

    private final ArrayList<DongVat> list;

    DongVatDao dongVatDao;

    public DongVatAdapter(Context context, ArrayList<DongVat> list) {
        this.context = context;
        this.list = list;
        dongVatDao = new DongVatDao(context);
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_dv, null);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.txtTenDv.setText("Tên: " + list.get(position).getTenDongVat());
        holder.txtMauSac.setText("Màu sắc: " + list.get(position).getMauSac());
        holder.txtCanNang.setText("Cân nặng: " + list.get(position).getCanNang());
        DongVat dongVat = list.get(position);
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có muốn xóa không?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (dongVatDao.delete(dongVat.getId())) {
                            list.clear();
                            list.addAll(dongVatDao.selectAll());
                            notifyDataSetChanged();
                            Toast.makeText(context, "Đã xóa!", Toast.LENGTH_SHORT).show();
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
            }
        });
        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        TextView txtTenDv, txtMauSac, txtCanNang;
        Button btnUpdate, btnDelete;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenDv = itemView.findViewById(R.id.txtTenDongVat);
            txtMauSac = itemView.findViewById(R.id.txtMauSac);
            txtCanNang = itemView.findViewById(R.id.txtCanNang);
            btnUpdate = itemView.findViewById(R.id.btnUpdate);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }

    public void openDiaLog(DongVat dongVat) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_update, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        EditText edtTen = view.findViewById(R.id.edtTenDongVatU);
        EditText edtMau = view.findViewById(R.id.edtMauSacU);
        EditText edtCanNang = view.findViewById(R.id.edtCanNangU);
        Button btnUpdate = view.findViewById(R.id.btnSua);
    }
}
