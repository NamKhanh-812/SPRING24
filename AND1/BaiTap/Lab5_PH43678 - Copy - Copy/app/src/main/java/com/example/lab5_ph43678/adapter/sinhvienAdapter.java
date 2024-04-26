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
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab5_ph43678.R;
import com.example.lab5_ph43678.model.sinhvien;

import java.util.ArrayList;

public class sinhvienAdapter extends BaseAdapter implements Filterable {
    private  final Context context;
    private  ArrayList<sinhvien> list;

    private final ArrayList<sinhvien> listBandau;

    public sinhvienAdapter(Context context, ArrayList<sinhvien> list) {
        this.context = context;
        this.list = list;
        this.listBandau=list;
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
        convertView=inflater.inflate(R.layout.item_home,parent,false);

        //ánh xạ

        TextView txtTenCoSo = convertView.findViewById(R.id.txttencs);
        TextView txtht = convertView.findViewById(R.id.txtht);
        TextView txtdc = convertView.findViewById(R.id.txtdc);
        Button btnUpdate = convertView.findViewById(R.id.btnupdate);
        Button btnXoa = convertView.findViewById(R.id.btnxoa);

        txtTenCoSo.setText(list.get(position).getTenCoSo());
        txtht.setText(list.get(position).getHoTen());
        txtdc.setText(list.get(position).getDiaChi());

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(position);
                Toast.makeText(context, "Xóa thành công!", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
            }
        });
        sinhvien sv=list.get(position);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialog(sv);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }
    public void opendialog(sinhvien sv){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_uppdate,null);
        builder.setView(view);
        Dialog dialog=builder.create();
        dialog.show();
        // ánh xạ
        EditText txtcs =view.findViewById(R.id.txtcs_ud);
        EditText txtten = view.findViewById(R.id.txtten_up);
        EditText txtDiachi = view.findViewById(R.id.txtdiachi_up);
        Button btnUppdate = view.findViewById(R.id.btnud);
        txtcs.setText(sv.getTenCoSo());
        txtten.setText(sv.getHoTen());
        txtDiachi.setText(sv.getDiaChi());
        btnUppdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sv.setTenCoSo(txtcs.getText().toString());
                sv.setHoTen(txtten.getText().toString());
                sv.setDiaChi(txtDiachi.getText().toString());
                dialog.dismiss();
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String s = constraint.toString();
                if(s.isEmpty()){
                    list=listBandau;
                }
                else{
                    ArrayList<sinhvien> listS = new ArrayList<>();
                    for (sinhvien st: listS){
                        if(st.getHoTen().toLowerCase().contains(s.toLowerCase())) {
                            listS.add(st);
                        }
                    }
                    list=listS;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = list;
                    return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list=(ArrayList<sinhvien>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
