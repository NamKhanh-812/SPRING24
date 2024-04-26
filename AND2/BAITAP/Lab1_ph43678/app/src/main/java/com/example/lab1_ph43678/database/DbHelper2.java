package com.example.lab1_ph43678.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper2 extends SQLiteOpenHelper  {
    public static final String name = "QLHS";

    public DbHelper2(@Nullable Context context) {
        super(context, name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tb_hocSinh=
                "CREATE TABLE hocSinh(\n" +
                        "ID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                        "MASV TEXT,\n" +
                        "HOTEN TEXT,\n" +
                        "SDT TEXT,\n" +
                        "QUEQUAN TEXT\n" +
                        ")";
        db.execSQL(tb_hocSinh);
        String data =
        "INSERT INTO hocSinh VALUES(1,'PH27562','HOÀNG VĂN THUYẾT','0937268473','HÀ NỘI')," +
                "(2,'PH29362','NÔNG VĂN THỊNH','0375268473','HÀ NAM')";
        db.execSQL(data);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion!=newVersion){
            db.execSQL("DROP TABLE IF EXISTS hocSinh");
            onCreate(db);
        }
    }
}
