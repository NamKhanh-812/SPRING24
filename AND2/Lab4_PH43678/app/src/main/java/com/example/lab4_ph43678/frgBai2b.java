package com.example.lab4_ph43678;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class frgBai2b extends Fragment {
    TextView txtKq;

    public frgBai2b() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_frg_bai2b, container, false);
        txtKq = view.findViewById(R.id.txtKq);
        return view;
    }
}