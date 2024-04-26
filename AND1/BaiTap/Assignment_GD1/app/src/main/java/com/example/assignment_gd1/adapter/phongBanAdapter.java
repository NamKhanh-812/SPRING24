package com.example.assignment_gd1.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.assignment_gd1.R;
import com.example.assignment_gd1.model.phongban;

import java.util.ArrayList;

public class phongBanAdapter extends BaseAdapter {
    private final Context context;
    private final ArrayList<phongban> list;

    public phongBanAdapter(Context context, ArrayList<phongban> list) {
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
        LayoutInflater inflater =((Activity)context).getLayoutInflater();
        convertView=inflater.inflate(R.layout.item_phong_ban,parent,false);
        TextView txtTenPhongBan = convertView.findViewById(R.id.txtTenPhongBan);
        txtTenPhongBan.setText(list.get(position).getTenPhongBan() );
        return convertView;
    }
}
