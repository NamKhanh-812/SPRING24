package com.example.ktlab7_ph43678;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
public static final String Db_name = "QLKH";
    public DbHelper(@Nullable Context context) {
        super(context, Db_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String TB_KHACHHANG="CREATE TABLE KHACHHANG1(MAKH TEXT PRIMARY KEY , TENKH TEXT, QUEQUAN TEXT, GIOITINH TEXT, NGAYSINH TEXT)";
        db.execSQL(TB_KHACHHANG);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion!=newVersion){
            db.execSQL("DROP TABLE IF EXISTS KHACHHANG1");
            onCreate(db);
        }
    }
}
