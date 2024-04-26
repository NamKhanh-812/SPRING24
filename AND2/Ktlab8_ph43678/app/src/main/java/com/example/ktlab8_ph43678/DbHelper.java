package com.example.ktlab8_ph43678;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
public static final String Db_name = "QLMH";
    public DbHelper(@Nullable Context context) {
        super(context, Db_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String TB_MONHOC="CREATE TABLE MONHOC(MAMH TEXT PRIMARY KEY , TENMH TEXT, SOTIET INTEGER)";
        db.execSQL(TB_MONHOC);
        db.execSQL("INSERT INTO MONHOC VALUES('1','Toán',7),('2','Văn',9)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion!=newVersion){
            db.execSQL("DROP TABLE IF EXISTS MONHOC");
            onCreate(db);
        }
    }
}
