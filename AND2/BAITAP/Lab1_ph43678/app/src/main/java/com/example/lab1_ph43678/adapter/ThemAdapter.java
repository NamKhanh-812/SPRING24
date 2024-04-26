package com.example.lab1_ph43678.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lab1_ph43678.R;
import com.example.lab1_ph43678.model.Them;

import java.util.ArrayList;

public class ThemAdapter extends BaseAdapter {
    private final Context context;
    private final ArrayList<Them> list;

    public ThemAdapter(Context context, ArrayList<Them> list) {
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
        convertView = inflater.inflate(R.layout.item_them,parent,false);

        TextView txtMaSV = convertView.findViewById(R.id.txtMaSV);
        TextView txtHoTen = convertView.findViewById(R.id.txtHoTen);
        TextView txtSDT = convertView.findViewById(R.id.txtSDT);
        TextView txtQueQuan = convertView.findViewById(R.id.txtQueQuan);

        txtMaSV.setText(list.get(position).getMaSV());
        txtHoTen.setText(list.get(position).getHoTen());
        txtSDT.setText(list.get(position).getSdt());
        txtQueQuan.setText(list.get(position).getQueQuan());

        return convertView;
    }
}
