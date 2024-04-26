package com.example.assignment.dao;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.example.assignment.database.DbHelper;
import com.example.assignment.model.NguoiDung;

import java.util.ArrayList;

public class NguoiDungDao {
    private final DbHelper dbHelper;

    public NguoiDungDao(Context context) {
        dbHelper = new DbHelper(context);
    }

    public boolean checkLogin(String username, String password) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor =
                sqLiteDatabase.rawQuery("SELECT * FROM NGUOIDUNG WHERE USERNAME = ? AND PASSWORD = ?",
                        new String[]{username, password});
        int row = cursor.getCount();
        return (row > 0);
    }

    public boolean checkusername(String username) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor =
                sqLiteDatabase.rawQuery("SELECT * FROM NGUOIDUNG WHERE USERNAME = ?",
                        new String[]{username});
        int row = cursor.getCount();
        return (row > 0);
    }

    public boolean add(NguoiDung nguoiDung) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("USERNAME", nguoiDung.getUsername());
        values.put("PASSWORD", nguoiDung.getPassword());
        values.put("HOTEN", nguoiDung.getHoten());
        long row = sqLiteDatabase.insert("NGUOIDUNG", null, values);
        return (row > 0);
    }
}
