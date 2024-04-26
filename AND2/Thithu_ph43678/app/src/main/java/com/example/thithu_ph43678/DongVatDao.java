package com.example.thithu_ph43678;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class DongVatDao {
    private final DbHelper dbHelper;

    public DongVatDao(Context context) {
        dbHelper = new DbHelper(context);
    }

    public ArrayList<DongVat> selectAll() {
        ArrayList<DongVat> list = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        try {
            Cursor cursor = database.rawQuery("SELECT * FROM DONGVAT", null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    DongVat dongVat = new DongVat();
                    dongVat.setId(cursor.getInt(0));
                    dongVat.setTenDongVat(cursor.getString(1));
                    dongVat.setMauSac(cursor.getString(2));
                    dongVat.setCanNang(cursor.getInt(3));
                    list.add(dongVat);
                    cursor.moveToNext();
                }
            }

        } catch (Exception e) {
            Log.i(TAG, "Lỗi", e);
        }
        return list;
    }

    public boolean add(DongVat dongVat) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TENDV", dongVat.getTenDongVat());
        values.put("MAUSAC", dongVat.getMauSac());
        values.put("CANNANG", dongVat.getCanNang());
        long row = database.insert("DONGVAT", null, values);
        return (row > 0);
    }

    public boolean update(DongVat dongVat) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TENDV", dongVat.getTenDongVat());
        values.put("MAUSAC", dongVat.getMauSac());
        values.put("CANNANG", dongVat.getCanNang());
        long row = database.update("DONGVAT", values, "ID=?", new String[]{String.valueOf(dongVat.getId())});
        return (row > 0);
    }

    public boolean delete(int id) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        long row = database.delete("DONGVAT", "ID=?", new String[]{String.valueOf(id)});
        return (row>0);
    }
}
