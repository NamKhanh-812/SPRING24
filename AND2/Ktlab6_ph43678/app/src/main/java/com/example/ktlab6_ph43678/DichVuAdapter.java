package com.example.ktlab6_ph43678;

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

public class DichVuAdapter extends RecyclerView.Adapter<DichVuAdapter.viewholder> {
    private final Context context;
    private final ArrayList<DichVu> list;
    DichVuDao dichVuDao;

    public DichVuAdapter(Context context, ArrayList<DichVu> list) {
        this.context = context;
        this.list = list;
        dichVuDao = new DichVuDao(context);
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_dv, null);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.txtNoiDung.setText("Nội dung: "+list.get(position).getNoiDung());
        holder.txtNgay.setText("Ngày: "+list.get(position).getNgay());
        holder.txtThanhTien.setText("Thành tiền: "+list.get(position).getThanhTien()+" VNĐ");
        DichVu dichVu = list.get(position);
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có muốn xóa thật không ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (dichVuDao.delete(dichVu.getMaDV())) {
                            list.clear();
                            list.addAll(dichVuDao.selectAll());
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
                openDiaLog(dichVu);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewholder extends RecyclerView.ViewHolder {
        TextView txtNoiDung, txtNgay, txtThanhTien;
        Button btnUpdate, btnDelete;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            txtNoiDung = itemView.findViewById(R.id.txtNoiDung);
            txtNgay = itemView.findViewById(R.id.txtNgay);
            txtThanhTien = itemView.findViewById(R.id.txtThanhTien);
            btnUpdate = itemView.findViewById(R.id.btnUpdate);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
    public void openDiaLog(DichVu dichVu) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_update, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        EditText edtNoiDung = view.findViewById(R.id.edtNDU);
        EditText edtNgay = view.findViewById(R.id.edtNgayU);
        EditText edtThanhTien = view.findViewById(R.id.edtThanhTienU);
        Button btnSua = view.findViewById(R.id.btnSua);

        edtNoiDung.setText(dichVu.getNoiDung());
        edtNgay.setText(dichVu.getNgay());
        edtThanhTien.setText(String.valueOf(dichVu.getThanhTien()));

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nd = edtNoiDung.getText().toString();
                String ngay = edtNgay.getText().toString();
                String tt = edtThanhTien.getText().toString();
                if (nd.isEmpty() || ngay.isEmpty() || tt.isEmpty()) {
                    Toast.makeText(context, "Không được bỏ trống", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (Integer.parseInt(tt) <= 0) {
                    Toast.makeText(context, "Tiền phải lớn hơn 0", Toast.LENGTH_SHORT).show();
                    return;
                }
                dichVu.setNoiDung(nd);
                dichVu.setNgay(ngay);
                dichVu.setThanhTien(Integer.parseInt(tt));
                if (dichVuDao.update(dichVu)) {
                    list.clear();
                    list.addAll(dichVuDao.selectAll());
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
