package com.example.baigiaothem.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static final String Db_name = "QL";

    public DbHelper(@Nullable Context context) {
        super(context, Db_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tb_phim = "CREATE TABLE PHIM (ID INTEGER PRIMARY KEY AUTOINCREMENT, TENPHIM TEXT)";
        db.execSQL(tb_phim);
        String tb_dienvien ="CREATE TABLE DIENVIEN (ID INTEGER PRIMARY KEY AUTOINCREMENT, TENDV TEXT)";
        db.execSQL(tb_dienvien);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion!=newVersion){
            db.execSQL("DROP TABLE IF EXISTS PHIM");
            db.execSQL("DROP TABLE IF EXISTS DIENVIEN");
            onCreate(db);
        }
    }
}
