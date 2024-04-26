package com.example.lab1_ph43678.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lab1_ph43678.R;
import com.example.lab1_ph43678.model.CongViec;

import java.util.ArrayList;

public class CongViecAdapter extends BaseAdapter {
    private final Context context;
    private final ArrayList<CongViec> list;

    public CongViecAdapter(Context context, ArrayList<CongViec> list) {
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
    // Trả về converView và gắn lên item
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.item_cv,parent,false);
        // ánh xạ
        TextView txtTitle = convertView.findViewById(R.id.txtTitle);
        TextView txtContent = convertView.findViewById(R.id.txtContent);
        TextView txtDate = convertView.findViewById(R.id.txtDate);
        TextView txtType = convertView.findViewById(R.id.txtType);
        // Gán dữ liệu trên converView
        txtTitle.setText(list.get(position).getTitle());
        txtContent.setText(list.get(position).getContent());
        txtDate.setText(list.get(position).getDate());
        txtType.setText(list.get(position).getType());

        return convertView;
    }
}
