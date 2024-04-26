package com.example.baithem_lab4.frg;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.baithem_lab4.R;
import com.example.baithem_lab4.model.phim;
import com.example.baithem_lab4.myadapter;

import java.util.ArrayList;


public class frag2 extends Fragment {
    RecyclerView recyclerView;
    ArrayList<phim> list1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frag2, container, false);
        recyclerView =view.findViewById(R.id.rcvList2);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        list1 = new ArrayList<>();
        phim p1 = new phim("Phim siêu nhân Gao",R.drawable.sn1);
        list1.add(p1);
        phim p2 = new phim("Phim Siêu Nhân Hải Tặc",R.drawable.sn2);
        list1.add(p2);
        phim p3 = new phim("Phim Siêu Nhân Điện Quang",R.drawable.sn3);
        list1.add(p3);
        phim p4 = new phim("Phim Siêu Nhân Cuồng Phong",R.drawable.sn4);
        list1.add(p4);
        phim p5 = new phim("Anh Em Siêu Nhân Deka",R.drawable.sn5);
        list1.add(p5);
        recyclerView.setAdapter(new myadapter(list1));

        return view;
    }
}