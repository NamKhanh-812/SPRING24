package com.example.ontap2;

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

public class sinhvienadapter extends BaseAdapter {
    private final Context context;
    private final ArrayList<sinhvien>list;

    public sinhvienadapter(Context context, ArrayList<sinhvien> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater =((Activity)context).getLayoutInflater();
        view=inflater.inflate(R.layout.item_lv,viewGroup,false);
        TextView txtma =view.findViewById(R.id.txtma_lv);
        TextView txtten =view.findViewById(R.id.txtten_lv);
        TextView txtdchi =view.findViewById(R.id.txtdchi_lv);

        txtma.setText(list.get(i).getMa());
        txtten.setText(list.get(i).getTen());
        txtdchi.setText(list.get(i).getDchi());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(i);
                xfile.ghi(context,"sv.txt",list);

                notifyDataSetChanged();
            }
        });


        return view;
    }

    public void them(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater =((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_add,null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        EditText edtma =view.findViewById(R.id.txtma_add);
        EditText edtten =view.findViewById(R.id.txtten_add);
        EditText edtdchi =view.findViewById(R.id.txtdchi_add);
        Button btnthem = view.findViewById(R.id.btnthem_add);

        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ma =edtma.getText().toString();
                String ten =edtten.getText().toString();
                String dchi =edtdchi.getText().toString();

                if(ma.isEmpty()||ten.isEmpty()||dchi.isEmpty()){
                    Toast.makeText(context, "khong dc de du lieu chong", Toast.LENGTH_SHORT).show();
                }
                else {
                    list.add(new sinhvien(ma,ten,dchi));
                    xfile.ghi(context,"sv.txt",list);
                    notifyDataSetChanged();
                    dialog.dismiss();
                }

            }
        });



    }
}
