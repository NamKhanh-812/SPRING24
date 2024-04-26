package com.example.baigiaothem.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.baigiaothem.Adapter.DienVienAdapter;
import com.example.baigiaothem.Adapter.PhimAdapter;
import com.example.baigiaothem.Model.DienVien;
import com.example.baigiaothem.Model.Phim;
import com.example.baigiaothem.R;
import com.example.baigiaothem.dao.DienVienDao;
import com.example.baigiaothem.dao.PhimDao;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class DienVienNe extends Fragment {
    RecyclerView recyclerView;
    FloatingActionButton fltAdd;
    DienVienDao dienVienDao;
    DienVienAdapter adapter;
    private ArrayList<DienVien> list = new ArrayList<>();

    public DienVienNe() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dien_vien_ne, container, false);
        recyclerView = view.findViewById(R.id.rcvDienVien);
        fltAdd = view.findViewById(R.id.fltAddDV);
        dienVienDao = new DienVienDao(getActivity());
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        list = dienVienDao.selectAll();
        adapter = new DienVienAdapter(getActivity(), list);
        recyclerView.setAdapter(adapter);
        fltAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater1 = getLayoutInflater();
                View view1 = inflater1.inflate(R.layout.item_add, null);
                builder.setView(view1);
                Dialog dialog = builder.create();
                dialog.show();

                EditText edtTen = view1.findViewById(R.id.edtTen);
                Button button = view1.findViewById(R.id.btnThem);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String ten = edtTen.getText().toString();
                        DienVien dienVien = new DienVien(ten);
                        if(dienVienDao.add(dienVien)){
                            list.clear();
                            list.addAll(dienVienDao.selectAll());
                            adapter.notifyDataSetChanged();
                            Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                        else {
                            Toast.makeText(getActivity(), "Thêm không thành công", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        return view;
    }
}