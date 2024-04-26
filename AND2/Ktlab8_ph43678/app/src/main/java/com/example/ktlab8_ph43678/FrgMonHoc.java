package com.example.ktlab8_ph43678;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class FrgMonHoc extends Fragment {
    RecyclerView recyclerView;
    FloatingActionButton fltAdd;
    MonHocDao monHocDao;
    MonHocAdapter adapter;
    private ArrayList<MonHocModel> list = new ArrayList<>();

    public FrgMonHoc() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frg_mon_hoc, container, false);
        recyclerView = view.findViewById(R.id.rcvMonHoc);
        fltAdd = view.findViewById(R.id.fltAddMonHoc);
        monHocDao = new MonHocDao(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = monHocDao.selectAll();
        adapter = new MonHocAdapter(getActivity(), list);
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
                EditText edtMaMonHoc = view1.findViewById(R.id.edtMaMonHocA);
                EditText edtTenMonHoc = view1.findViewById(R.id.edtTenMonHocA);
                EditText edtSoTiet = view1.findViewById(R.id.edtSoTietA);
                Button button = view1.findViewById(R.id.btnThem);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String mamh = edtMaMonHoc.getText().toString();
                        String tenmh = edtTenMonHoc.getText().toString();
                        String sotiet = edtSoTiet.getText().toString();


                        if (mamh.isEmpty()||tenmh.isEmpty() || sotiet.isEmpty()) {
                            Toast.makeText(getActivity(), "Không được để trống", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(Integer.parseInt(sotiet)<=15){
                            Toast.makeText(getActivity(), "Phải lớn hơn 15", Toast.LENGTH_SHORT).show();
                            return;
                        }
                            MonHocModel monHocModel = new MonHocModel();
                            monHocModel.setTenMonHoc(tenmh);
                            monHocModel.setMaMonHoc(mamh);
                            monHocModel.setSoTiet(Integer.parseInt(sotiet));


                            if (monHocDao.add(monHocModel)) {
                                list.clear();
                                list.addAll(monHocDao.selectAll());
                                adapter.notifyDataSetChanged();
                                Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            } else {
                                Toast.makeText(getActivity(), "Thêm không thành công", Toast.LENGTH_SHORT).show();
                            }
                        }


                });
            }
        });
        return view;
    }
}