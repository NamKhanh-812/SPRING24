package com.example.lab5_ph43678.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lab5_ph43678.R;
import com.example.lab5_ph43678.model.school;

import java.util.ArrayList;

public class schoolAdapter extends BaseAdapter {
    private final Context context;
    private final ArrayList<school> list;

    public schoolAdapter(Context context, ArrayList<school> list) {
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
        convertView = inflater.inflate(R.layout.item_bai1, parent, false);
        ImageView imghinh = convertView.findViewById(R.id.imghinh);
        TextView txtcoso = convertView.findViewById(R.id.txtcoso);
        imghinh.setImageResource(list.get(position).getHinh());
        txtcoso.setText(list.get(position).getTen());
        return convertView;
    }
}
