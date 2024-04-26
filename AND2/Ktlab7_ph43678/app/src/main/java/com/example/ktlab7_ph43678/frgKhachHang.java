package com.example.ktlab7_ph43678;

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


public class frgKhachHang extends Fragment {
    RecyclerView recyclerView;
    FloatingActionButton fltAdd;
    KhachHangDao khachHangDao;
    KhachHangAdapter adapter;
    private ArrayList<KhachHang> list = new ArrayList<>();

    public frgKhachHang() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_frg_khach_hang, container, false);
        recyclerView = view.findViewById(R.id.rcvKhachHang);
        fltAdd = view.findViewById(R.id.fltAddKhachHang);
        khachHangDao = new KhachHangDao(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = khachHangDao.selectAll();
        adapter = new KhachHangAdapter(getActivity(), list);
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

                EditText edtTenKH = view1.findViewById(R.id.edtTenKhA);
                EditText edtQueQuan = view1.findViewById(R.id.edtQueQuanA);
                EditText edtGioiTinh = view1.findViewById(R.id.edtGioiTinhA);
                EditText edtNgaySinh = view1.findViewById(R.id.edtNgaySinhA);

                Button button = view1.findViewById(R.id.btnThem);
                edtGioiTinh.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                        builder1.setTitle("Chọn giới tính");
                        String[] ten = {"Nam","Nữ"};
                        builder1.setItems(ten, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                edtGioiTinh.setText(ten[which]);
                            }
                        });
                        AlertDialog dialog1 = builder1.create();
                        dialog1.show();
                    }
                });
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String tenkh = edtTenKH.getText().toString();
                        String quequan = edtQueQuan.getText().toString();
                        String gioitinh = edtGioiTinh.getText().toString();
                        String ngaysinh = edtNgaySinh.getText().toString();

                        if(tenkh.isEmpty()|| quequan.isEmpty()||gioitinh.isEmpty()||ngaysinh.isEmpty()){
                            Toast.makeText(getActivity(), "Không được để trống", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            KhachHang khachHang = new KhachHang();
                            khachHang.setTenKH(tenkh);
                            khachHang.setQuequan(quequan);
                            khachHang.setGioitinh(gioitinh);
                            khachHang.setNgaysinh(ngaysinh);

                            if(khachHangDao.add(khachHang)){
                                list.clear();
                                list.addAll(khachHangDao.selectAll());
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