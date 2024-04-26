package com.example.ph43678_test1.adapter;

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

import com.example.ph43678_test1.R;
import com.example.ph43678_test1.model.gv;

import java.util.ArrayList;

public class gvAdapter extends BaseAdapter {
    private final Context context;
    private final ArrayList<gv> list;

    public gvAdapter(Context context, ArrayList<gv> list) {
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
        // ánh xạ
        TextView txtMaGv = convertView.findViewById(R.id.txtMaGv);
        TextView txtHoTen = convertView.findViewById(R.id.txtHoTen);
        Button btnSua = convertView.findViewById(R.id.btnSua);
        Button btnXoa = convertView.findViewById(R.id.btnXoa);
        txtMaGv.setText(list.get(position).getMaGv());
        txtHoTen.setText(list.get(position).getHoTen());


        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(position);
                Toast.makeText(context, "Xóa thành công!", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
            }
        });
        gv gv = list.get(position);

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialog(gv);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    public void opendialog(gv gv) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_sua, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        EditText edtMaGv = view.findViewById(R.id.edtMaGvSua);
        EditText edtHoTen = view.findViewById(R.id.edtHoTenSua);
        Button btnXong = view.findViewById(R.id.btnXong);
        edtMaGv.setText(gv.getMaGv());
        edtHoTen.setText(gv.getHoTen());
        btnXong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gv.setMaGv(edtMaGv.getText().toString());
                gv.setHoTen(edtHoTen.getText().toString());
                dialog.dismiss();
                notifyDataSetChanged();
            }
        });
    }
}
