package com.example.baigiaothem.Adapter;

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

import com.example.baigiaothem.Model.Phim;
import com.example.baigiaothem.R;
import com.example.baigiaothem.dao.PhimDao;

import java.util.ArrayList;

public class PhimAdapter extends RecyclerView.Adapter<PhimAdapter.viewHolder> {
    private final Context context;
    private final ArrayList<Phim> list;
    PhimDao phimDao;

    public PhimAdapter(Context context, ArrayList<Phim> list) {
        this.context = context;
        this.list = list;
        phimDao = new PhimDao(context);
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_phim, null);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.txtTen.setText(list.get(position).getTenPhim());

        Phim phim = list.get(position);
        holder.txtTen.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Cảnh báo");
                builder.setIcon(R.drawable.baseline_warning_24);
                builder.setMessage("Bạn có chắc chắn muốn xóa không?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (phimDao.delete(phim.getId())) {
                            list.clear();
                            list.addAll(phimDao.selectAll());
                            notifyDataSetChanged();
                            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "Không xóa", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                return false;
            }
        });
        holder.txtTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialog(phim);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        TextView txtTen;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            txtTen = itemView.findViewById(R.id.txtTenPhim);
        }
    }

    public void opendialog(Phim phim) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_update, null); // gắn layout
        builder.setView(view); // gắn view vào hộp thoại
        Dialog dialog = builder.create();
        dialog.show(); // hiển thị hộp thoại

        EditText edtTen = view.findViewById(R.id.edtTenU);
        Button btnUpdate = view.findViewById(R.id.btnSua);

        edtTen.setText(phim.getTenPhim());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phim.setTenPhim(edtTen.getText().toString());

                if (phimDao.update(phim)) {
                    list.clear();
                    list.addAll(phimDao.selectAll());
                    notifyDataSetChanged();
                    Toast.makeText(context, "Update thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Update thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
