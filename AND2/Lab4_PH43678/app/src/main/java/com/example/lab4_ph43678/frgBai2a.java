package com.example.lab4_ph43678;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class frgBai2a extends Fragment {


    public frgBai2a() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frg_bai2a, container, false);
        EditText edtNhap = view.findViewById(R.id.edtNhap);
        Button btnSend = view.findViewById(R.id.btnSend);
        // ử lý sk nút btn
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                frgBai2b frgBai2b = (com.example.lab4_ph43678.frgBai2b) fragmentManager.findFragmentById(R.id.frgBai2b);
                frgBai2b.txtKq.setText(edtNhap.getText().toString());
            }
        });
        return view;
    }
}