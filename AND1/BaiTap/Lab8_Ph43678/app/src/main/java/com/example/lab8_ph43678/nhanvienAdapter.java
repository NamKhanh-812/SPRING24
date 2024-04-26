package com.example.lab8_ph43678;

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
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.item_gv, parent,false);
        // ánh xạ
        TextView txtMa = convertView.findViewById(R.id.txtma);
        TextView txtht = convertView.findViewById(R.id.txtht);
        TextView txtdc = convertView.findViewById(R.id.txtdc);
        Button btnUpdate = convertView.findViewById(R.id.btnupdate);
        Button btnXoa = convertView.findViewById(R.id.btnxoa);
        // gắn dữ liệu
        txtMa.setText(list.get(position).getManv());
        txtht.setText(list.get(position).getHoten());
        txtdc.setText(list.get(position).getDiachi());
        // xóa
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(position);
                notifyDataSetChanged();
                Toast.makeText(context, "đã xóa succ", Toast.LENGTH_SHORT).show();
                xfile.ghifile(context, "nv.txt", list);
            }
        });
        nhanvien nv = list.get(position);
        // update
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialog(nv);
                xfile.ghifile(context, "nv.txt", list);
            }
        });
        return convertView;
    }
    public void opendialog(nhanvien nv) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_update, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        //anh xa
        EditText txtmanvs = view.findViewById(R.id.txtmanvs);
        EditText txthotens = view.findViewById(R.id.txthotens);
        EditText txtdiachis = view.findViewById(R.id.txtdiachis);
        Button btnedit = view.findViewById(R.id.btnedit);
        //gán du lieu lên các textview
        txtmanvs.setText(nv.getManv());
        txthotens.setText(nv.getHoten());
        txtdiachis.setText(nv.getDiachi());
        //click update
        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nv.setManv(txtmanvs.getText().toString());
                nv.setHoten(txthotens.getText().toString());
                nv.setDiachi(txtdiachis.getText().toString());
                dialog.dismiss();
                xfile.ghifile(context, "nv.txt", list);
                notifyDataSetChanged();
            }
        });
    }
}
