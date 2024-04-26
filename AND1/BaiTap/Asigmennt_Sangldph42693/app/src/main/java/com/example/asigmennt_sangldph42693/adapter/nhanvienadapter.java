package com.example.asigmennt_sangldph42693.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asigmennt_sangldph42693.R;
import com.example.asigmennt_sangldph42693.model.nhanvien;

import java.util.ArrayList;

public class nhanvienadapter extends BaseAdapter {

    private final Context context;
    private final ArrayList<nhanvien> list;

    public nhanvienadapter(Context context, ArrayList<nhanvien> list) {
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
        convertView = inflater.inflate(R.layout.item_nhanvien,parent,false);

        TextView txtManv= convertView.findViewById(R.id.txtManv);
        TextView txtTennv = convertView.findViewById(R.id.txtTennv);
        TextView txtTenpb = convertView.findViewById(R.id.txtPBnv);
        ImageView imgEdit = convertView.findViewById(R.id.imgEdit);
        ImageView imgDelete = convertView.findViewById(R.id.imgDelete);

        txtManv.setText(list.get(position).getMaNV());
        txtTennv.setText(list.get(position).getTenNV());
        txtTenpb.setText(list.get(position).getPbNV());

        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(position);
                notifyDataSetChanged();
            }
        });
        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(list.get(position));
            }
        });
        return convertView;
    }
    private void openDialog(nhanvien nv){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.update_nv,null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        EditText edtManv = view.findViewById(R.id.edtManv);
        EditText edtTennv = view.findViewById(R.id.edtTennv);
        EditText edtPbnv = view.findViewById(R.id.edtPBnv);
        ImageView imgUpdate = view.findViewById(R.id.imgUpdate);

        edtManv.setText(nv.getMaNV());
        edtTennv.setText(nv.getTenNV());
        edtPbnv.setText(nv.getPbNV());

        imgUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nv.setMaNV(edtManv.getText().toString());
                nv.setTenNV(edtTennv.getText().toString());
                nv.setPbNV(edtPbnv.getText().toString());
                dialog.dismiss();
                notifyDataSetChanged();
            }
        });

    }
}
