package com.example.on;


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


public class nguoiAdapter extends BaseAdapter {
    private final Context context;
    private final ArrayList<nguoi> list;

    public nguoiAdapter(Context context, ArrayList<nguoi> list) {
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
        convertView = inflater.inflate(R.layout.item_ds,parent,false);

        TextView txtMa = convertView.findViewById(R.id.txtMa);
        TextView txtHoTen = convertView.findViewById(R.id.txtHoTen);
        TextView txtTuoi = convertView.findViewById(R.id.txtTuoi);
        Button btnXoa = convertView.findViewById(R.id.btnXoa);
        Button btnUpdate = convertView.findViewById(R.id.btnUpdate);

        txtMa.setText(list.get(position).getMa());
        txtHoTen.setText(list.get(position).getHoTen());
        txtTuoi.setText(list.get(position).getTuoi());

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(position);
                Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                Xfile.ghi(context,"cb.txt",list);
                notifyDataSetChanged();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDiaLog(list.get(position));
            }
        });
        return convertView;
    }

    private void openDiaLog (nguoi n){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_up,null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        EditText edtMaUP = view.findViewById(R.id.edtMaUp);
        EditText edtHTUp = view.findViewById(R.id.edtHTUp);
        EditText edtTuoiUp = view.findViewById(R.id.edtTuoiUp);
        Button btnXong = view.findViewById(R.id.btnXong);

        edtMaUP.setText(n.getMa());
        edtHTUp.setText(n.getHoTen());
        edtTuoiUp.setText(n.getTuoi());

        btnXong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ma = edtMaUP.getText().toString();
                String hoTen = edtHTUp.getText().toString();
                String tuoi = edtTuoiUp.getText().toString();

                n.setMa(ma);
                n.setHoTen(hoTen);
                n.setTuoi(tuoi);

                dialog.dismiss();
                Xfile.ghi(context,"cb.txt",list
                );
                notifyDataSetChanged();
            }
        });
    }
}
