package com.example.thithu_ph43678;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static final String Db_name = "QLDV";

    public DbHelper(@Nullable Context context) {
        super(context, Db_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE DONGVAT(ID INTEGER PRIMARY KEY AUTOINCREMENT, TENDV TEXT, MAUSAC TEXT, CANNANG INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    if(oldVersion!=newVersion){
        db.execSQL("DROP TABLE IF EXISTS DONGVAT");
        onCreate(db);
    }
    }
}
