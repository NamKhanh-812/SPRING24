package com.example.lab1_ph43678.dao;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.lab1_ph43678.database.DbHelper2;
import com.example.lab1_ph43678.model.Them;

import java.util.ArrayList;

public class ThemDao {
    private final DbHelper2 dbHelper2;
    public ThemDao (Context context){
        dbHelper2 = new DbHelper2(context);
    }
    public ArrayList<Them> selectAll(){
        ArrayList<Them> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper2.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM hocSinh",null);
            if (cursor.getCount()>0){
                cursor.moveToFirst();
                while (!cursor.isAfterLast()){
                    Them them = new Them();
                    them.setId(cursor.getInt(0));
                    them.setMaSV(cursor.getString(1));
                    them.setHoTen(cursor.getString(2));
                    them.setSdt(cursor.getString(3));
                    them.setQueQuan(cursor.getString(4));
                    list.add(them);
                    cursor.moveToNext();
                }
            }
        }catch (Exception e){
            Log.i(TAG,"Lỗi",e);
        }
        return list;
    }
    public boolean insert(Them them){
        SQLiteDatabase db = dbHelper2.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MASV",them.getMaSV());
        values.put("HOTEN",them.getHoTen());
        values.put("SDT",them.getSdt());
        values.put("QUEQUAN",them.getQueQuan());

        long row = db.insert("hocSinh",null,values);
        return (row>0);
    }

    public boolean update(Them them){
        SQLiteDatabase db = dbHelper2.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MASV",them.getMaSV());
        values.put("HOTEN",them.getHoTen());
        values.put("SDT",them.getSdt());
        values.put("QUEQUAN",them.getQueQuan());

        long row = db.update("hocSinh",values,"ID=?",new String[]{String.valueOf(them.getId())});
        return (row>0);
    }

    public boolean delete (int id){
        SQLiteDatabase db = dbHelper2.getWritableDatabase();
        long row = db.delete("hocSinh","ID=?",new String[]{String.valueOf(id)});
        return (row>0);
    }
}
