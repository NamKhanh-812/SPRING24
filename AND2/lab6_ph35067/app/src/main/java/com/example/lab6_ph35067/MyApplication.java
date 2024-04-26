package com.example.lab6_ph35067;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class MyApplication extends Application {
    public static final String CHANNEL_ID="FPTPOLYTECHNIC";

    @Override
    public void onCreate() {
        super.onCreate();
        createChanelNotification();
    }
    private void createChanelNotification(){
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O);
        NotificationChannel channel = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            channel = new NotificationChannel(CHANNEL_ID,"FPT POLYTECHNIC", NotificationManager.IMPORTANCE_DEFAULT);
        }
        NotificationManager manager = getSystemService(NotificationManager.class);
        if (manager!=null){
            manager.createNotificationChannel(channel);
        }
    }
}
