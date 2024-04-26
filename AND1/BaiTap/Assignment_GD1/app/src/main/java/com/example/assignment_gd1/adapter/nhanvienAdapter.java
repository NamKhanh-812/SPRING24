package com.example.assignment_gd1.adapter;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment_gd1.R;
import com.example.assignment_gd1.model.nhanvien;
import java.util.ArrayList;

public class nhanvienAdapter extends BaseAdapter {
    private final Context context;
    private final ArrayList<nhanvien> list;

    public nhanvienAdapter(Context context, ArrayList<nhanvien> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {

        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        convertView=inflater.inflate(R.layout.item_nhan_vien,parent,false);
        // ánh xạ
        TextView txtMaNv = convertView.findViewById(R.id.txtMaNv);
        TextView txtHoTen = convertView.findViewById(R.id.txtHoTen);
        TextView txtTenPhongBan = convertView.findViewById(R.id.txtPhongBan);
        Button btnSua = convertView.findViewById(R.id.btnSua);
        Button btnXoa = convertView.findViewById(R.id.btnXoa);

        txtMaNv.setText(list.get(position).getMaNv());
        txtHoTen.setText(list.get(position).getHoTen());
        txtTenPhongBan.setText(list.get(position).getTenPhongBan());

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(position);
                Toast.makeText(context, "Xóa thành công!", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
            }
        });
        nhanvien nv =list.get(position);

        btnSua.setOnClickListener(new View.OnClickListener() {
        @Override
            public void onClick(View v) {
                opendialog(nv);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }
    public void opendialog(nhanvien nv){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_sua,null);
        builder.setView(view);
        Dialog dialog=builder.create();
        dialog.show();
        // ánh xạ
        EditText edtMaNvSua =view.findViewById(R.id.edtMaNvSua);
        EditText edtHoTenSua = view.findViewById(R.id.edtHoTenSua);
        EditText edtTenPhongBanSua = view.findViewById(R.id.edtTenPhongBanSua);
        Button btnXong = view.findViewById(R.id.btnXong);
        edtMaNvSua.setText(nv.getMaNv());
        edtHoTenSua.setText(nv.getHoTen());
        edtTenPhongBanSua.setText(nv.getTenPhongBan());
        btnXong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nv.setMaNv(edtMaNvSua.getText().toString());
                nv.setHoTen(edtHoTenSua.getText().toString());
                nv.setTenPhongBan(edtTenPhongBanSua.getText().toString());
                dialog.dismiss();
                notifyDataSetChanged();
            }
        });
    }
}
