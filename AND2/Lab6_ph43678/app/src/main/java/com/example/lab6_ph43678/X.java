package com.example.lab6_ph43678;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class X extends Application {
    public static final String CHANEL_ID = "FPTPOLYTECHNIC";

    @Override
    public void onCreate() {
        super.onCreate();
        createChanelNotification();
    }
    private void createChanelNotification(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O);
        NotificationChannel notificationChannel = new NotificationChannel(CHANEL_ID,"FPT POLYTECHNIC", NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        if(notificationManager!=null){
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }
}
