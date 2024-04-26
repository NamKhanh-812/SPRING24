package com.example.ktlab6_ph43678;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
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


public class FrgDichVu extends Fragment {

    RecyclerView recyclerView;
    FloatingActionButton fltAdd;
    DichVuDao dichVuDao;
    DichVuAdapter adapter;
    private ArrayList<DichVu> list = new ArrayList<>();
    public FrgDichVu() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_frg_dich_vu, container, false);
        recyclerView = view.findViewById(R.id.rcvDichVu);
        fltAdd = view.findViewById(R.id.fltAddDichVu);
        dichVuDao = new DichVuDao(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = dichVuDao.selectAll();
        adapter = new DichVuAdapter(getActivity(), list);
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

                EditText edtND = view1.findViewById(R.id.edtNDA);
                EditText edtNgay = view1.findViewById(R.id.edtNgayA);
                EditText edtTT = view1.findViewById(R.id.edtThanhTienA);
                Button button = view1.findViewById(R.id.btnThem);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String nd = edtND.getText().toString();
                        String ngay = edtNgay.getText().toString();
                        String tt = edtTT.getText().toString();
                        if(nd.isEmpty()|| ngay.isEmpty()||tt.isEmpty()){
                            Toast.makeText(getActivity(), "Không được để trống", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            DichVu dichVu = new DichVu();
                            dichVu.setNoiDung(nd);
                            dichVu.setNgay(ngay);
                            dichVu.setThanhTien(Integer.parseInt(tt));
                            if(dichVuDao.add(dichVu)){
                                list.clear();
                                list.addAll(dichVuDao.selectAll());
                                adapter.notifyDataSetChanged();
                                Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                            else {
                                Toast.makeText(getActivity(), "Thêm không thành công", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                });
            }
        });
        return view;
    }
}