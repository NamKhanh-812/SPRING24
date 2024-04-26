package com.example.assignment.dao;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.assignment.database.DbHelper;
import com.example.assignment.model.SanPham;

import java.util.ArrayList;

public class SanPhamDao {
    private final DbHelper dbHelper;

    public SanPhamDao(Context context) {
        dbHelper = new DbHelper(context);
    }

    public ArrayList<SanPham> selectAll() {
        ArrayList<SanPham> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        try {
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM SANPHAM", null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    SanPham sanPham = new SanPham();
                    sanPham.setMaSanPham(cursor.getInt(0));
                    sanPham.setTenSanPham(cursor.getString(1));
                    sanPham.setGiaBan(cursor.getInt(2));
                    sanPham.setSoLuong(cursor.getInt(3));
                    list.add(sanPham);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            Log.i(TAG, "Lỗi", e);
        }
        return list;
    }

    public boolean add(SanPham sanPham) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TENSP", sanPham.getTenSanPham());
        values.put("GIABAN", sanPham.getGiaBan());
        values.put("SOLUONG", sanPham.getSoLuong());
        long row = sqLiteDatabase.insert("SANPHAM", null, values);
        return (row > 0);
    }

    public boolean update(SanPham sanPham) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TENSP", sanPham.getTenSanPham());
        values.put("GIABAN", sanPham.getGiaBan());
        values.put("SOLUONG", sanPham.getSoLuong());
        long row = sqLiteDatabase.update("SANPHAM", values, "MASANPHAM=?", new String[]{String.valueOf(sanPham.getMaSanPham())});
        return (row > 0);
    }

    public boolean delete(int id) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        long row = sqLiteDatabase.delete("SANPHAM", "MASANPHAM=?", new String[]{String.valueOf(id)});
        return (row > 0);
    }

}
