package com.example.ktlab6_ph43678;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class DichVuDao {
    private final DbHelper dbHelper;

    public DichVuDao(Context context) {
        dbHelper = new DbHelper(context);
    }
    public ArrayList<DichVu> selectAll() {
        ArrayList<DichVu> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase(); // đọc dữ liệu từ database
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM DICHVU", null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst(); // đưa con trỏ về dòng đầu tiên
                while (!cursor.isAfterLast()) {
                    DichVu dichVu = new DichVu(); // tạo đối tượng
                    // nhập thông tin cho đối tượng
                    dichVu.setMaDV(cursor.getInt(0));
                    dichVu.setNoiDung(cursor.getString(1));
                    dichVu.setNgay(cursor.getString(2));
                    dichVu.setThanhTien(cursor.getInt(3));
                    list.add(dichVu);
                    cursor.moveToNext(); // next con trỏ dòng tiếp theo
                }
            }
        } catch (Exception e) {
            Log.i(TAG, "Lỗi", e);
        }
        return list;
    }
    public boolean add(DichVu dichVu) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        // sử dụng ContentValues  để đưa dữ liệu vào database
        ContentValues values = new ContentValues();
        // id là gtri tự động tăng nên không cần thêm vào
        values.put("NOIDUNG", dichVu.getNoiDung());
        values.put("NGAY", dichVu.getNgay());
        values.put("THANHTIEN", dichVu.getThanhTien());
        // Nếu thành công sẽ trả về giá trị tương tự số hàng mà dữ liệu được add trong bảng
        long row = database.insert("DICHVU", null, values);
        return (row > 0);
    }

    public boolean update(DichVu dichVu) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        // sử dụng ContentValues  để đưa dữ liệu vào database
        ContentValues values = new ContentValues();
        // id là gtri tự động tăng nên không cần thêm vào
        values.put("NOIDUNG", dichVu.getNoiDung());
        values.put("NGAY", dichVu.getNgay());
        values.put("THANHTIEN", dichVu.getThanhTien());
        // Nếu thành công sẽ trả về giá trị tương tự số hàng mà dữ liệu được add trong bảng
        long row = database.update("DICHVU", values, "MADV=?", new String[]{String.valueOf(dichVu.getMaDV())});
        return (row > 0);
    }

    public boolean delete(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long row = db.delete("DICHVU", "MADV=?", new String[]{String.valueOf(id)});
        return (row > 0);
    }
}
