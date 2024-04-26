package com.example.assignment.adapter;

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

import com.example.assignment.R;
import com.example.assignment.dao.SanPhamDao;
import com.example.assignment.model.SanPham;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.viewHolder> {
    private final Context context;
    private final ArrayList<SanPham> list;
    SanPhamDao sanPhamDao;

    public SanPhamAdapter(Context context, ArrayList<SanPham> list) {
        this.context = context;
        this.list = list;
        sanPhamDao = new SanPhamDao(context);
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_qlsp, null);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.txtTenSP.setText(list.get(position).getTenSanPham());
        holder.txtGiaBanSP.setText(list.get(position).getGiaBan() + " VNĐ");
        holder.txtSoLuongSP.setText("SL: "+list.get(position).getSoLuong());
        SanPham sanPham = list.get(position);
        holder.txtXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có muốn xóa sản phẩm ' " + holder.txtTenSP.getText().toString() + " ' ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (sanPhamDao.delete(sanPham.getMaSanPham())) {
                            list.clear();
                            list.addAll(sanPhamDao.selectAll());
                            notifyDataSetChanged();
                            Toast.makeText(context, "Đã xóa sản phẩm ' " + holder.txtTenSP.getText().toString() + " '!", Toast.LENGTH_SHORT).show();
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
        holder.txtChinhSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDiaLog(sanPham);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        TextView txtTenSP, txtGiaBanSP, txtSoLuongSP, txtChinhSua, txtXoa;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenSP = itemView.findViewById(R.id.txtTenSanPham);
            txtGiaBanSP = itemView.findViewById(R.id.txtGiaSanPham);
            txtSoLuongSP = itemView.findViewById(R.id.txtSoLuongSanPham);
            txtChinhSua = itemView.findViewById(R.id.txtChinhSua);
            txtXoa = itemView.findViewById(R.id.txtXoa);
        }
    }

    public void openDiaLog(SanPham sanPham) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_chinh_sua, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        EditText edtTen = view.findViewById(R.id.edtTenSanPhamS);
        EditText edtGiaBan = view.findViewById(R.id.edtGiaBanS);
        EditText edtSoLuong = view.findViewById(R.id.edtSoLuongS);
        Button btnSua = view.findViewById(R.id.btnSua);

        edtTen.setText(sanPham.getTenSanPham());
        edtGiaBan.setText(String.valueOf(sanPham.getGiaBan()));
        edtSoLuong.setText(String.valueOf(sanPham.getSoLuong()));

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = edtTen.getText().toString();
                String gia = edtGiaBan.getText().toString();
                String sl = edtSoLuong.getText().toString();
                if (ten.isEmpty() || gia.isEmpty() || sl.isEmpty()) {
                    Toast.makeText(context, "Không được bỏ trống", Toast.LENGTH_SHORT).show();
                    return;}

                if (Integer.parseInt(gia) <= 0) {
                    Toast.makeText(context, "Giá bán phải lớn hơn 0", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (Integer.parseInt(sl) < 0) {
                    Toast.makeText(context, "Số lượng phải lớn hơn hoặc bằng 0", Toast.LENGTH_SHORT).show();
                    return;
                }
                sanPham.setTenSanPham(ten);
                sanPham.setGiaBan(Integer.parseInt(gia));
                sanPham.setSoLuong(Integer.parseInt(sl));
                if (sanPhamDao.update(sanPham)) {
                    list.clear();
                    list.addAll(sanPhamDao.selectAll());
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
