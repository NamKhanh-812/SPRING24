package com.example.baigiaothem.dao;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.baigiaothem.Database.DbHelper;
import com.example.baigiaothem.Model.Phim;

import java.util.ArrayList;

public class PhimDao {
    private final DbHelper dbHelper;

    public PhimDao(Context context) {
        dbHelper = new DbHelper(context);
    }


    public ArrayList<Phim> selectAll() {
        ArrayList<Phim> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase(); // đọc dữ liệu từ database
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM PHIM", null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst(); // đưa con trỏ về dòng đầu tiên
                while (!cursor.isAfterLast()) {
                    Phim phim = new Phim(); // tạo đối tượng
                    // nhập thông tin cho đối tượng
                    phim.setId(cursor.getInt(0));
                    phim.setTenPhim(cursor.getString(1));
                    list.add(phim);
                    cursor.moveToNext(); // next con trỏ dòng tiếp theo
                }
            }
        } catch (Exception e) {
            Log.i(TAG, "Lỗi", e);
        }
        return list;
    }
    public boolean add(Phim phim) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        // sử dụng ContentValues  để đưa dữ liệu vào database
        ContentValues values = new ContentValues();
        // id là gtri tự động tăng nên không cần thêm vào
        values.put("TENPHIM", phim.getTenPhim());
        // Nếu thành công sẽ trả về giá trị tương tự số hàng mà dữ liệu được add trong bảng
        long row = database.insert("PHIM", null, values);
        return (row > 0);
    }

    public boolean update(Phim phim) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        // sử dụng ContentValues  để đưa dữ liệu vào database
        ContentValues values = new ContentValues();
        // id là gtri tự động tăng nên không cần thêm vào
        values.put("TENPHIM", phim.getTenPhim());

        // Nếu thành công sẽ trả về giá trị tương tự số hàng mà dữ liệu được add trong bảng
        long row = database.update("PHIM", values, "ID=?", new String[]{String.valueOf(phim.getId())});
        return (row > 0);
    }

    public boolean delete(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long row = db.delete("PHIM", "id=?", new String[]{String.valueOf(id)});
        return (row > 0);
    }
}
