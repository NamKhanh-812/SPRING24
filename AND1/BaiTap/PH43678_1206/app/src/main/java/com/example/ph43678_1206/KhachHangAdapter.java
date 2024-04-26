package com.example.ph43678_1206;

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

public class KhachHangAdapter extends BaseAdapter {
    private final Context context;
    private final ArrayList<KhachHang> list;

    public KhachHangAdapter(Context context, ArrayList<KhachHang> list) {
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
        convertView=inflater.inflate(R.layout.item_lv,parent,false);
        TextView txtMaKH = convertView.findViewById(R.id.txtMaKh);
        TextView txtHoTen = convertView.findViewById(R.id.txtHoTen);
        TextView txtTuoi = convertView.findViewById(R.id.txtTuoi);
        TextView txtSDT = convertView.findViewById(R.id.txtSDT);
        Button btnSua = convertView.findViewById(R.id.btnSua);
        Button btnXoa = convertView.findViewById(R.id.btnXoa);

        txtMaKH.setText(list.get(position).getMaKH());
        txtHoTen.setText(list.get(position).getHoten());
        txtTuoi.setText(list.get(position).getTuoi());
        txtSDT.setText(list.get(position).getSdt());

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(position);
                notifyDataSetChanged();
                Toast.makeText(context, "Xóa thành công!", Toast.LENGTH_SHORT).show();
            }
        });
        KhachHang kh = list.get(position);
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialog(kh);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }
    public void opendialog(KhachHang kh) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_update, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        EditText edtMaKh = view.findViewById(R.id.edtMaKhSua);
        EditText edtHoTen = view.findViewById(R.id.edtHoTenSua);
        EditText edtTuoi = view.findViewById(R.id.edtTuoiSua);
        EditText edtSDT = view.findViewById(R.id.edtSDTSua);
        Button btnXong = view.findViewById(R.id.btnXong);
        edtMaKh.setText(kh.getMaKH());
        edtHoTen.setText(kh.getHoten());
        edtTuoi.setText(kh.getTuoi());
        edtSDT.setText(kh.getSdt());
        btnXong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kh.setMaKH(edtMaKh.getText().toString());
                kh.setHoten(edtHoTen.getText().toString());
                kh.setTuoi(edtTuoi.getText().toString());
                kh.setSdt(edtSDT.getText().toString());
                dialog.dismiss();
                notifyDataSetChanged();
            }
        });
    }
}
