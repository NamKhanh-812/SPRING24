package com.example.lab1_ph43678.dao;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.lab1_ph43678.database.DbHelper;
import com.example.lab1_ph43678.model.CongViec;

import java.util.ArrayList;

public class CongViecDao {
    private final DbHelper dbHelper;

    public CongViecDao(Context context ) {
        dbHelper = new DbHelper(context);
    }
    // Lấy dữ liệu từ database , add dữ liệu vào list
    public ArrayList<CongViec> selectAll(){
        ArrayList<CongViec> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase(); // đọc dữ liệu từ database
        try{
            Cursor cursor = db.rawQuery("SELECT * FROM congviec",null);
            if(cursor.getCount()>0){
                cursor.moveToFirst(); // đưa con trỏ về dòng đầu tiên
                while(!cursor.isAfterLast()){
                    CongViec cv = new CongViec(); // tạo đối tượng
                    // nhập thông tin cho đối tượng
                    cv.setId(cursor.getInt(0));
                    cv.setTitle(cursor.getString(1));
                    cv.setContent(cursor.getString(2));
                    cv.setDate(cursor.getString(3));
                    cv.setType(cursor.getString(4));
                    cv.setTrangthai(cursor.getInt(5));
                    list.add(cv);
                    cursor.moveToNext(); // next con trỏ dòng tiếp theo
                }
            }
        }catch (Exception e){
            Log.i(TAG,"Lỗi",e);
        }
        return list;
    }

    public boolean add(CongViec congViec){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        // sử dụng ContentValues  để đưa dữ liệu vào database
        ContentValues values = new ContentValues();
        // id là gtri tự động tăng nên không cần thêm vào
        values.put("TITLE",congViec.getTitle());
        values.put("CONTENT",congViec.getContent());
        values.put("DATE",congViec.getDate());
        values.put("TYPE",congViec.getType());
        values.put("TRANGTHAI",congViec.getTrangthai());
        // Nếu thành công sẽ trả về giá trị tương tự số hàng mà dữ liệu được add trong bảng
        long row = database.insert("congviec",null,values);
        return (row>0);
    }

    public boolean update(CongViec congViec){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        // sử dụng ContentValues  để đưa dữ liệu vào database
        ContentValues values = new ContentValues();
        // id là gtri tự động tăng nên không cần thêm vào
        values.put("TITLE",congViec.getTitle());
        values.put("CONTENT",congViec.getContent());
        values.put("DATE",congViec.getDate());
        values.put("TYPE",congViec.getType());
        values.put("TRANGTHAI",congViec.getTrangthai());
        // Nếu thành công sẽ trả về giá trị tương tự số hàng mà dữ liệu được add trong bảng
        long row = database.update("congviec",values,"ID=?",new String[]{String.valueOf(congViec.getId())});
        return (row>0);
    }
    public boolean delete(int id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long row = db.delete("congviec","id=?",new String[]{String.valueOf(id)});
        return (row>0);
    }
}
