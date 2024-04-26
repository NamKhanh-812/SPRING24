package com.example.assignment.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
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

import com.example.assignment.R;
import com.example.assignment.adapter.SanPhamAdapter;
import com.example.assignment.dao.SanPhamDao;
import com.example.assignment.model.SanPham;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;


public class QuanLySanPham extends Fragment {
//    RecyclerView recyclerView;
//    FloatingActionButton fltAdd;
//    SanPhamDao sanPhamDao;
//    SanPhamAdapter adapter;
//    private ArrayList<SanPham> list = new ArrayList<>();

    public QuanLySanPham() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quan_ly_san_pham, container, false);
//        recyclerView = view.findViewById(R.id.rcvLSP);
//        fltAdd = view.findViewById(R.id.fltADD);
//        sanPhamDao = new SanPhamDao(getActivity());
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        list = sanPhamDao.selectAll();
//        adapter = new SanPhamAdapter(getActivity(), list);
//        recyclerView.setAdapter(adapter);
//        fltAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                LayoutInflater inflater1 = getLayoutInflater();
//                View view1 = inflater1.inflate(R.layout.item_them, null);
//                builder.setView(view1);
//                Dialog dialog = builder.create();
//                dialog.show();
//                EditText edtTenSP = view1.findViewById(R.id.edtTenSanPham);
//                EditText edtGiaBan = view1.findViewById(R.id.edtGiaBan);
//                EditText edtSoLuong = view1.findViewById(R.id.edtSoLuong);
//                Button btnThem = view1.findViewById(R.id.btnThem);
//                btnThem.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        String ten = edtTenSP.getText().toString();
//                        String giaBan = edtGiaBan.getText().toString();
//                        String soLuong = edtSoLuong.getText().toString();
//
//                        if (ten.isEmpty() || giaBan.isEmpty() || soLuong.isEmpty()) {
//                            Toast.makeText(getActivity(), "Vui lòng nhập đầy đủ thông tin sản phẩm!", Toast.LENGTH_SHORT).show();
//                            return;
//                        }
//                        if (Integer.parseInt(giaBan) <= 0) {
//                            Toast.makeText(getActivity(), "Giá bán phải lớn hơn 0", Toast.LENGTH_SHORT).show();
//                            return;
//                        }
//                        if (Integer.parseInt(soLuong) < 0) {
//                            Toast.makeText(getActivity(), "Số lượng phải lớn hơn hoặc bằng 0", Toast.LENGTH_SHORT).show();
//                            return;
//                        }
//
//                        SanPham sanPham = new SanPham();
//                        sanPham.setTenSanPham(ten);
//                        sanPham.setGiaBan(Integer.parseInt(giaBan));
//                        sanPham.setSoLuong(Integer.parseInt(soLuong));
//
//                        if (sanPhamDao.add(sanPham)) {
//                            list.clear();
//                            list.addAll(sanPhamDao.selectAll());
//                            adapter.notifyDataSetChanged();
//                            Toast.makeText(getActivity(), "Thêm thành công!", Toast.LENGTH_SHORT).show();
//                            dialog.dismiss();
//                        } else {
//                            Toast.makeText(getActivity(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
//                        }
//                        edtTenSP.setText("");
//                        edtGiaBan.setText("");
//                        edtSoLuong.setText("");
//                    }
//                });
//            }
//        });
        return view;
    }
}