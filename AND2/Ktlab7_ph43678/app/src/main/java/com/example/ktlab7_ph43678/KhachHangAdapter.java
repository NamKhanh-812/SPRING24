package com.example.ktlab7_ph43678;

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

public class KhachHangAdapter extends RecyclerView.Adapter<KhachHangAdapter.viewHolder> {
    private final Context context;
    private final ArrayList<KhachHang> list;
    KhachHangDao khachHangDao;

    public KhachHangAdapter(Context context, ArrayList<KhachHang> list) {
        this.context = context;
        this.list = list;
        khachHangDao = new KhachHangDao(context);
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_kh, null);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.txtTenKH.setText("Tên KH: " + list.get(position).getTenKH());
        holder.txtQueQuan.setText("Quê quán: " + list.get(position).getQuequan());
        holder.txtGioiTinh.setText("Giới tính: " + list.get(position).getGioitinh());
        holder.txtNgaySinh.setText("Ngày sinh: " + list.get(position).getNgaysinh());
        KhachHang khachHang = list.get(position);
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn muốn xóa khách hàng này ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (khachHangDao.delete(khachHang.getMakh())) {
                            list.clear();
                            list.addAll(khachHangDao.selectAll());
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
                openDiaLog(khachHang);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        TextView txtTenKH, txtQueQuan, txtGioiTinh, txtNgaySinh;
        Button btnUpdate, btnDelete;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenKH = itemView.findViewById(R.id.txtTenKH);
            txtQueQuan = itemView.findViewById(R.id.txtQueQuan);
            txtGioiTinh = itemView.findViewById(R.id.txtGioiTinh);
            txtNgaySinh = itemView.findViewById(R.id.txtNgaySinh);
            btnUpdate = itemView.findViewById(R.id.btnUpdate);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
    public void openDiaLog(KhachHang khachHang) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_update, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        EditText edtTenKh = view.findViewById(R.id.edtTenKhU);
        EditText edtQueQuan = view.findViewById(R.id.edtQueQuanU);
        EditText edtGioiTinh = view.findViewById(R.id.edtGioiTinhU);
        EditText edtNgaySinh = view.findViewById(R.id.edtNgaySinhU);
        Button btnSua = view.findViewById(R.id.btnSua);

        edtTenKh.setText(khachHang.getTenKH());
        edtQueQuan.setText(khachHang.getQuequan());
        edtGioiTinh.setText(khachHang.getGioitinh());
        edtNgaySinh.setText(khachHang.getNgaysinh());

        edtGioiTinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setTitle("Chọn giới tính");
                String[] ten = {"Nam","Nữ"};
                builder1.setItems(ten, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        edtGioiTinh.setText(ten[which]);
                    }
                });
                AlertDialog dialog1 = builder1.create();
                dialog1.show();
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenkh = edtTenKh.getText().toString();
                String quequan = edtQueQuan.getText().toString();
                String gioitinh = edtGioiTinh.getText().toString();
                String ngaysinh = edtNgaySinh.getText().toString();

                if (tenkh.isEmpty() || quequan.isEmpty() || gioitinh.isEmpty()||ngaysinh.isEmpty()) {
                    Toast.makeText(context, "Không được bỏ trống", Toast.LENGTH_SHORT).show();
                    return;
                }

                khachHang.setTenKH(tenkh);
                khachHang.setQuequan(quequan);
                khachHang.setGioitinh(gioitinh);
                khachHang.setNgaysinh(ngaysinh);


                if (khachHangDao.update(khachHang)) {
                    list.clear();
                    list.addAll(khachHangDao.selectAll());
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
