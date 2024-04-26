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


public class frag1 extends Fragment {
    RecyclerView recyclerView;
    ArrayList<phim>list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_frag1, container, false);
        recyclerView =view.findViewById(R.id.rcvList);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        list = new ArrayList<>();
        phim p1 = new phim("Godzilla vs. Kong (2021)",R.drawable.gzl);
        list.add(p1);
        phim p2 = new phim("Inception (2010)",R.drawable.in);
        list.add(p2);
        phim p3 = new phim("John Wick 4",R.drawable.j);
        list.add(p3);
        phim p4 = new phim("The Flash",R.drawable.flash);
        list.add(p4);
        phim p5 = new phim("Thor: Love & Thunder",R.drawable.thor);
        list.add(p5);
        recyclerView.setAdapter(new myadapter(list));

        return view;
    }
}