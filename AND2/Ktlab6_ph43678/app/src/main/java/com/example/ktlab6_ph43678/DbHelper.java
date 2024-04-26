package com.example.ktlab6_ph43678;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static final String Db_name ="QLDV";
    public DbHelper(@Nullable Context context) {
        super(context, Db_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tb_dichVu="CREATE TABLE DICHVU(MADV INTEGER PRIMARY KEY AUTOINCREMENT, NOIDUNG TEXT, NGAY TEXT, THANHTIEN INTEGER)";
                db.execSQL(tb_dichVu);
        db.execSQL("INSERT INTO DICHVU VALUES(1,'Kiểm tra bảo mật','02/05/2023',300000)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion!=newVersion){
            db.execSQL("DROP TABLE IF EXISTS DICHVU");
            onCreate(db);
        }
    }
}
