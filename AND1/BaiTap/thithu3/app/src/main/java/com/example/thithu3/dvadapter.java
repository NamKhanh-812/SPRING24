package com.example.thithu3;


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

public class dvadapter extends BaseAdapter {
    private final Context context;
    private final ArrayList<dv> list;

    public dvadapter(Context context, ArrayList<dv> list) {
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
        convertView = inflater.inflate(R.layout.item_dongvat,parent,false);

        TextView ma = convertView.findViewById(R.id.txtma);
        TextView ten = convertView.findViewById(R.id.txtten);
        TextView cannang = convertView.findViewById(R.id.txtcannang);
        TextView soluong = convertView.findViewById(R.id.txtsoluong);
        Button btnUp = convertView.findViewById(R.id.btnupdate);
        Button btnDe = convertView.findViewById(R.id.btndelete);

        ma.setText(list.get(position).getMa());
        ten.setText(list.get(position).getTen());
        cannang.setText(list.get(position).getCannang()+"");
        soluong.setText(list.get(position).getSoluong()+"");

        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                list.remove(position);
                notifyDataSetChanged();
                xfile.ghifile(context,"dv.txt",list);
                Toast.makeText(context, "Đã xóa", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        btnDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(position);
                notifyDataSetChanged();
                xfile.ghifile(context,"dv.txt",list);
                Toast.makeText(context, "Đã xóa", Toast.LENGTH_SHORT).show();
            }
        });
        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialog(list.get(position));
            }
        });

        return convertView;
    }
    private void opendialog(dv dv){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_update,null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        EditText ma = view.findViewById(R.id.ma_ud);
        EditText ten = view.findViewById(R.id.edtten_ud);
        EditText cnang = view.findViewById(R.id.edtcannang_ud);
        EditText soluong = view.findViewById(R.id.edtsoluong_ud);
        Button btnSua = view.findViewById(R.id.btnsua);

        ma.setText(dv.getMa());
        ten.setText(dv.getTen());
        cnang.setText(dv.getCannang()+"");
        soluong.setText(dv.getSoluong()+"");

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String madv = ma.getText().toString();
                String tendv = ten.getText().toString();
                String cndv = cnang.getText().toString();
                String sldv = soluong.getText().toString();
                if(madv.isEmpty()||tendv.isEmpty()||cndv.isEmpty()||sldv.isEmpty()){
                    Toast.makeText(context, "Không được bỏ trống", Toast.LENGTH_SHORT).show();
                } else if (!isso(sldv)) {
                    Toast.makeText(context, "Số lượng phải lớn hơn 0", Toast.LENGTH_SHORT).show();
                }else {
                    dv.setMa(madv);
                    dv.setTen(tendv);
                    dv.setCannang(Double.parseDouble(cndv));
                    dv.setSoluong(Integer.parseInt(sldv));
                    dialog.dismiss();
                    xfile.ghifile(context,"dv.txt",list);
                    notifyDataSetChanged();

                }
            }
        });
    }
    private Boolean isso(String so){
        return  so.matches("[1-9](\\d+)?");
    }
}
