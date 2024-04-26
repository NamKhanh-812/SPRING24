package com.example.ontap2;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class  xfile {
    public static void ghi(Context context,String path,Object object){
        try {
            FileOutputStream a = context.openFileOutput(path,Context.MODE_PRIVATE);
            ObjectOutputStream b = new ObjectOutputStream(a);
            b.writeObject(object);
            b.close();
            a.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Object doc(Context context,String path ){
        Object list=null;
        try {
            FileInputStream a = context.openFileInput(path);
            ObjectInputStream b = new ObjectInputStream(a);
            list =(ArrayList) b.readObject();
            b.close();
            a.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return  list;
    }
}
