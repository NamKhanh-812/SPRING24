package com.example.thithu3;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class xfile {
    public static void ghifile(Context context,String path, Object o){
        try{
            FileOutputStream fos = context.openFileOutput(path,Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(o);
            oos.close();
            fos.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static Object docfile(Context context, String path){
        Object list = null;
        try {
            FileInputStream fis = context.openFileInput(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            list = (ArrayList) ois.readObject();
            ois.close();
            fis.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
