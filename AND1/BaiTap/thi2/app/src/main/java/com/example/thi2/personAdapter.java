package com.example.thi2;

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

public class personAdapter extends BaseAdapter {
    private final Context context;
    private final ArrayList<person> list;

    public personAdapter(Context context, ArrayList<person> list) {
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
        TextView txHoTen = convertView.findViewById(R.id.txtHoTen);
        TextView txtTuoi = convertView.findViewById(R.id.txtTuoi);
        Button btnUpdate = convertView.findViewById(R.id.btnUpdate);
        Button btnXoa = convertView.findViewById(R.id.btnXoa);

        txtMa.setText(list.get(position).getMa());
        txHoTen.setText(list.get(position).getHoTen());
        txtTuoi.setText(list.get(position).getTuoi());

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(position);
                Toast.makeText(context, "Delete success", Toast.LENGTH_SHORT).show();
                Xfile.ghi(context,"ik.txt",list);
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

    private void openDiaLog(person ps) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_up, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        EditText edtMa = view.findViewById(R.id.edtMaUp);
        EditText edtHoTen = view.findViewById(R.id.edtHTup);
        EditText edtTuoi = view.findViewById(R.id.edtTuoiUp);
        Button btnUp = view.findViewById(R.id.btnUp);

        edtMa.setText(ps.getMa());
        edtHoTen.setText(ps.getHoTen());
        edtTuoi.setText(ps.getTuoi());

        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ma = edtMa.getText().toString();
                String hoTen = edtHoTen.getText().toString();
                String tuoi = edtTuoi.getText().toString();

                ps.setMa(ma);
                ps.setHoTen(hoTen);
                ps.setTuoi(tuoi);

                dialog.dismiss();
                Xfile.ghi(context,"ik.txt",list);
                notifyDataSetChanged();
            }
        });
    }
}
