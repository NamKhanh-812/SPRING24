package com.example.thi;

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
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.item_ds, parent, false);

        TextView txtMa = convertView.findViewById(R.id.txtMa);
        TextView txtHoTen = convertView.findViewById(R.id.txtHoTen);
        TextView txtTuoi = convertView.findViewById(R.id.txtTuoi);
        Button btnUpdate = convertView.findViewById(R.id.btnUpdate);
        Button btnXoa = convertView.findViewById(R.id.btnXoa);

        txtMa.setText(list.get(position).getMa());
        txtHoTen.setText(list.get(position).getHoTen());
        txtTuoi.setText(list.get(position).getTuoi());

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(position);
                Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                Xfile.ghi(context,"thi.txt",list);
                notifyDataSetChanged();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialog(list.get(position));
            }
        });

        return convertView;
    }

    private void opendialog(nhanvien nv) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_up, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        EditText edtMaUp = view.findViewById(R.id.edtMaUp);
        EditText edtHoTenUp = view.findViewById(R.id.edtHoTenUp);
        EditText edtTuoiUp = view.findViewById(R.id.edtTuoiUp);
        Button btnXong = view.findViewById(R.id.btnXong);

        edtMaUp.setText(nv.getMa());
        edtHoTenUp.setText(nv.getHoTen());
        edtTuoiUp.setText(nv.getTuoi());

        btnXong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ma = edtMaUp.getText().toString();
                String hoTen = edtHoTenUp.getText().toString();
                String tuoi = edtTuoiUp.getText().toString();

                nv.setMa(ma);
                nv.setHoTen(hoTen);
                nv.setTuoi(tuoi);

                dialog.dismiss();
                Xfile.ghi(context,"thi.txt",list);
                notifyDataSetChanged();
            }
        });

    }
}
