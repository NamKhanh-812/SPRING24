package com.example.assignment.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static final String Db_name = "QLSP";

    public DbHelper(@Nullable Context context) {
        super(context, Db_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // bảng người dùng
        String tb_NguoiDung = "CREATE TABLE NGUOIDUNG (USERNAME TEXT PRIMARY KEY , PASSWORD TEXT, HOTEN TEXT)";
        db.execSQL(tb_NguoiDung);
        //bảng sản phẩm
        String tb_SanPham = "CREATE TABLE SANPHAM (MASANPHAM INTEGER PRIMARY KEY AUTOINCREMENT, TENSP TEXT, GIABAN INTEGER, SOLUONG INTEGER )";
        db.execSQL(tb_SanPham);

        db.execSQL("INSERT INTO NGUOIDUNG VALUES('KhanhDepTrai','123','Sằm Nam Khánh')");
        db.execSQL("INSERT INTO SANPHAM VALUES(1,'Cần sa',2,2)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS NGUOIDUNG ");
            db.execSQL("DROP TABLE IF EXISTS SANPHAM ");
            onCreate(db);
        }
    }
}
