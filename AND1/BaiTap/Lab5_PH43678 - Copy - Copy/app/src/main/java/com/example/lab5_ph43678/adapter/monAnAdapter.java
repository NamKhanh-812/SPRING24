package com.example.lab5_ph43678.adapter;

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

import com.example.lab5_ph43678.R;
import com.example.lab5_ph43678.model.monAn;

import java.util.ArrayList;

public class monAnAdapter extends BaseAdapter {
    private final Context context;
    private final ArrayList<monAn> list;

    public monAnAdapter(Context context, ArrayList<monAn> list) {
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
        LayoutInflater inflater =  ((Activity)context).getLayoutInflater();
        convertView=inflater.inflate(R.layout.item_do_an,parent,false);
        // ánh xạ
        TextView txtTenMonAn = convertView.findViewById(R.id.txtTenMonAn);
        TextView txtGiaMonAn = convertView.findViewById(R.id.txtGiaMonAn);
        TextView txtDiaChiMonAn = convertView.findViewById(R.id.txtDiaChiMonAn);
        Button btnSuaMonAn = convertView.findViewById(R.id.btnSuaMonAn);
        Button btnXoaMonAn = convertView.findViewById(R.id.btnXoaMonAn);
        // gắn dữ liệu
        txtTenMonAn.setText(list.get(position).getTenMonAn());
        txtGiaMonAn.setText(list.get(position).getGiaMonAn());
        txtDiaChiMonAn.setText(list.get(position).getDiaChiMonAn());
        btnXoaMonAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(position);
                Toast.makeText(context, "Xóa thành công!", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
            }
        });
        monAn mn = list.get(position);
        btnSuaMonAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(mn);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }
    public void openDialog(monAn mn){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_sua_do_an,null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        // ánh xạ
        EditText txtSuaTenMonAn = view.findViewById(R.id.edtSuaTenMonAn);
        EditText txtSuaGiaMonAn = view.findViewById(R.id.edtSuaGiaMonAn);
        EditText txtSuaDiaChiMonAn = view.findViewById(R.id.edtSuaDiaChi);
        Button btnXong = view.findViewById(R.id.btnXong);
        txtSuaTenMonAn.setText(mn.getTenMonAn());
        txtSuaGiaMonAn.setText(mn.getGiaMonAn());
        txtSuaDiaChiMonAn.setText(mn.getDiaChiMonAn());
        btnXong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mn.setTenMonAn(txtSuaTenMonAn.getText().toString());
                mn.setGiaMonAn(txtSuaGiaMonAn.getText().toString());
                mn.setDiaChiMonAn(txtSuaDiaChiMonAn.getText().toString());
                dialog.dismiss();
                notifyDataSetChanged();
            }
        });
    }
}
