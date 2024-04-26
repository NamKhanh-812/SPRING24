package com.example.asigmennt_sangldph42693;

import android.content.Context;

import com.example.asigmennt_sangldph42693.model.nhanvien;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class xfile {
    public void ghifile(Context context, String path, List<nhanvien> list){

        try {
            if (list == null || list.isEmpty()) {
                // nếu danh sách là rỗng, không ghi gì vào tệp tin
                return;
            }
            FileOutputStream fos=context.openFileOutput(path,Context.MODE_PRIVATE);
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            List<nhanvien> ds=new ArrayList<>();
            ds.addAll(list);
            oos.writeObject(ds);
            oos.close();
            fos.close();

        }
        catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi ghi tệp tin: " + e.getMessage());
        }
    }
    public List<nhanvien> docfile(Context context, String path) {
        List<nhanvien> list = new ArrayList<>();
        try {
            FileInputStream fis = context.openFileInput(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object object = ois.readObject();
            if (object instanceof List) {
                List<?> ds = (List<?>) object;
                for (Object o : ds) {
                    if (o instanceof nhanvien) {
                        list.add((nhanvien) o);
                    }
                }
            }
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            // hoặc có thể ném ra ngoại lệ cho lớp gọi xử lý tiếp
            throw new RuntimeException("Lỗi khi đọc tệp tin: " + e.getMessage());
        }
        return list;
    }

    public void writeUser(Context context, String fileName, user user){
        List<user> list = new ArrayList<>();
        try{
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            list.add(user);
            oos.writeObject(list);
            oos.close();
            fos.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public List<user> readUser(Context context,String fileName){
        List<user> obj = new ArrayList<>();
        try{
            FileInputStream fis = context.openFileInput(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            obj = (List<user>) ois.readObject();
            ois.close();
            fis.close();
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return obj;
    }

}
