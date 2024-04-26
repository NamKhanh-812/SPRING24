package com.example.baigiaothem.dao;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.baigiaothem.Database.DbHelper;
import com.example.baigiaothem.Model.DienVien;
import com.example.baigiaothem.Model.Phim;

import java.util.ArrayList;

public class DienVienDao {
    private final DbHelper dbHelper;

    public DienVienDao(Context context) {
        dbHelper = new DbHelper(context);
    }
    public ArrayList<DienVien> selectAll() {
        ArrayList<DienVien> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase(); // đọc dữ liệu từ database
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM DIENVIEN", null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst(); // đưa con trỏ về dòng đầu tiên
                while (!cursor.isAfterLast()) {
                    DienVien dienVien = new DienVien(); // tạo đối tượng
                    // nhập thông tin cho đối tượng
                    dienVien.setId(cursor.getInt(0));
                    dienVien.setTenDV(cursor.getString(1));
                    list.add(dienVien);
                    cursor.moveToNext(); // next con trỏ dòng tiếp theo
                }
            }
        } catch (Exception e) {
            Log.i(TAG, "Lỗi", e);
        }
        return list;
    }
    public boolean add(DienVien dienVien) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        // sử dụng ContentValues  để đưa dữ liệu vào database
        ContentValues values = new ContentValues();
        // id là gtri tự động tăng nên không cần thêm vào
        values.put("TENDV", dienVien.getTenDV());
        // Nếu thành công sẽ trả về giá trị tương tự số hàng mà dữ liệu được add trong bảng
        long row = database.insert("DIENVIEN", null, values);
        return (row > 0);
    }

    public boolean update(DienVien dienVien) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        // sử dụng ContentValues  để đưa dữ liệu vào database
        ContentValues values = new ContentValues();
        // id là gtri tự động tăng nên không cần thêm vào
        values.put("TENDV", dienVien.getTenDV());

        // Nếu thành công sẽ trả về giá trị tương tự số hàng mà dữ liệu được add trong bảng
        long row = database.update("DIENVIEN", values, "ID=?", new String[]{String.valueOf(dienVien.getId())});
        return (row > 0);
    }

    public boolean delete(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long row = db.delete("DIENVIEN", "id=?", new String[]{String.valueOf(id)});
        return (row > 0);
    }
}
