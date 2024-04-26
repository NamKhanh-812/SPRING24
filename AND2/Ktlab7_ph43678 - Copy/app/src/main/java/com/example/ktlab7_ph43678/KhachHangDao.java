package com.example.ktlab7_ph43678;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class KhachHangDao {
    private final DbHelper dbHelper;

    public KhachHangDao(Context context) {
        dbHelper = new DbHelper(context);
    }

    public ArrayList<KhachHang> selectAll() {
        ArrayList<KhachHang> list = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        try {
            Cursor cursor = database.rawQuery("SELECT * FROM KHACHHANG", null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    KhachHang khachHang = new KhachHang();
                    khachHang.setMakh(cursor.getString(0));
                    khachHang.setTenKH(cursor.getString(1));
                    khachHang.setQuequan(cursor.getString(2));
                    khachHang.setGioitinh(cursor.getString(3));
                    khachHang.setNgaysinh(cursor.getString(4));
                    list.add(khachHang);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            Log.i(TAG, "Lỗi", e);
        }
        return list;
    }

    public boolean add(KhachHang khachHang) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        // sử dụng ContentValues  để đưa dữ liệu vào database
        ContentValues values = new ContentValues();

        values.put("MAKH", khachHang.getMakh());
        values.put("TENKH", khachHang.getTenKH());
        values.put("QUEQUAN", khachHang.getQuequan());
        values.put("GIOITINH", khachHang.getGioitinh());
        values.put("NGAYSINH", khachHang.getNgaysinh());
        // Nếu thành công sẽ trả về giá trị tương tự số hàng mà dữ liệu được add trong bảng
        long row = database.insert("KHACHHANG1",null,values);
        return (row > 0);
    }

    public boolean update(KhachHang khachHang) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        // sử dụng ContentValues  để đưa dữ liệu vào database
        ContentValues values = new ContentValues();
        // id là gtri tự động tăng nên không cần thêm vào
        values.put("MAKH", khachHang.getMakh());
        values.put("TENKH", khachHang.getTenKH());
        values.put("QUEQUAN", khachHang.getQuequan());
        values.put("GIOITINH", khachHang.getGioitinh());
        values.put("NGAYSINH", khachHang.getNgaysinh());
        // Nếu thành công sẽ trả về giá trị tương tự số hàng mà dữ liệu được add trong bảng
        long row = database.update("KHACHHANG1", values,"MAKH=?",new String[]{khachHang.getMakh()});
        return (row > 0);
    }

    public boolean delete(String id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long row = db.delete("KHACHHANG1", "MAKH=?", new String[]{id});
        return (row > 0);
    }
}
