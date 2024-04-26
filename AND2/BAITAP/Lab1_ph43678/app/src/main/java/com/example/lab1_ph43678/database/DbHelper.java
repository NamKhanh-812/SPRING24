package com.example.lab1_ph43678.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static final String Db_name ="QLCV";
    public DbHelper(@Nullable Context context) {
        super(context ,Db_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // tạo bảng
        String tb_congviec = "CREATE TABLE congviec(\n" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT ,\n" +
                "TITLE TEXT,\n" +
                "CONTENT TEXT,\n" +
                "DATE TEXT,\n" +
                "TYPE TEXT,\n" +
                "TRANGTHAI INTEGER \n" +
                ")";

        db.execSQL(tb_congviec);
        String data ="INSERT INTO congviec VALUES (1,'Học java','Học java cơ bản','27/2/2023','Bình thường',1)," +
                "(2,'Học React Navtive','Học React Navtive cơ bản','24/3/2023','Khó',0)," +
                "(3,'Học Kotlin','Học Kotlin cơ bản','1/4/2023','Dễ',0)";

        db.execSQL(data);
    }
// Khi nâng cấp csdl, csdl mới
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Khi upgrade  database ta cần kiểm tra version hiện tại version khi ta upgrade có khác nhau hay không
        // Nếu  có thực hiện câu lệnh xóa bảng và khởi tạo lại
        if(oldVersion!=newVersion){
            // Xóa bảng nếu tồn tại
            db.execSQL("DROP TABLE IF EXISTS congviec");
            // Gọi lại hàm onCreate
            onCreate(db);
        }
    }
}
