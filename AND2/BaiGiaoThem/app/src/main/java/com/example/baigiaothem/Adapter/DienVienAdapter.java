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

import com.example.baigiaothem.Model.DienVien;
import com.example.baigiaothem.Model.Phim;
import com.example.baigiaothem.R;
import com.example.baigiaothem.dao.DienVienDao;

import java.util.ArrayList;

public class DienVienAdapter extends RecyclerView.Adapter<DienVienAdapter.viewHolder>{
    private final Context context;
    private final ArrayList<DienVien> list;
    DienVienDao dienVienDao;

    public DienVienAdapter(Context context, ArrayList<DienVien> list) {
        this.context = context;
        this.list = list;
        dienVienDao = new DienVienDao(context);
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_dien_vien, null);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.txtTen.setText(list.get(position).getTenDV());

        DienVien dienVien = list.get(position);
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
                        if (dienVienDao.delete(dienVien.getId())) {
                            list.clear();
                            list.addAll(dienVienDao.selectAll());
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
                })  ;
                AlertDialog dialog = builder.create();
                dialog.show();
                return false;
            }
        });
        holder.txtTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialog(dienVien);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{
        TextView txtTen ;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            txtTen= itemView.findViewById(R.id.txtTenDV);
        }
    }
    public void opendialog(DienVien dienVien) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_update, null); // gắn layout
        builder.setView(view); // gắn view vào hộp thoại
        Dialog dialog = builder.create();
        dialog.show(); // hiển thị hộp thoại

        EditText edtTen = view.findViewById(R.id.edtTenU);
        Button btnUpdate = view.findViewById(R.id.btnSua);

        edtTen.setText(dienVien.getTenDV());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dienVien.setTenDV(edtTen.getText().toString());

                if (dienVienDao.update(dienVien)) {
                    list.clear();
                    list.addAll(dienVienDao.selectAll());
                    notifyDataSetChanged();
                    Toast.makeText(context, "Update thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Update thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
