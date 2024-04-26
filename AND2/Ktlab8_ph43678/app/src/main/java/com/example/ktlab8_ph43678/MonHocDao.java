package com.example.ktlab8_ph43678;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class MonHocDao {
    private final DbHelper dbHelper;

    public MonHocDao(Context context) {
        dbHelper = new DbHelper(context);
    }
    public ArrayList<MonHocModel> selectAll() {
        ArrayList<MonHocModel> list = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        try {
            Cursor cursor = database.rawQuery("SELECT * FROM MONHOC", null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    MonHocModel monHocModel = new MonHocModel();
                    monHocModel.setMaMonHoc(cursor.getString(0));
                    monHocModel.setTenMonHoc(cursor.getString(1));
                    monHocModel.setSoTiet(cursor.getInt(2));
                    list.add(monHocModel);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            Log.i(TAG, "Lỗi", e);
        }
        return list;
    }
    public boolean add(MonHocModel monHocModel) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        // sử dụng ContentValues  để đưa dữ liệu vào database
        ContentValues values = new ContentValues();
        values.put("MAMH",monHocModel.getMaMonHoc());
        values.put("TENMH", monHocModel.getTenMonHoc());
        values.put("SOTIET", monHocModel.getSoTiet());
        // Nếu thành công sẽ trả về giá trị tương tự số hàng mà dữ liệu được add trong bảng
        long row = database.insert("MONHOC", null, values);
        return (row > 0);
    }
    public boolean update(MonHocModel monHocModel) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        // sử dụng ContentValues  để đưa dữ liệu vào database
        ContentValues values = new ContentValues();
        // id là gtri tự động tăng nên không cần thêm vào
        values.put("MAMH",monHocModel.getMaMonHoc());
        values.put("TENMH", monHocModel.getTenMonHoc());
        values.put("SOTIET", monHocModel.getSoTiet());
        // Nếu thành công sẽ trả về giá trị tương tự số hàng mà dữ liệu được add trong bảng
        long row = database.update("MONHOC",  values,"MAMH=?",new String[]{monHocModel.getMaMonHoc()});
        return (row > 0);
    }
    public boolean delete(String id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long row = db.delete("MONHOC", "MAMH=?", new String[]{id});
        return (row > 0);
    }
}
