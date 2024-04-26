package com.example.lab6;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class Myapplication extends Application {
    public static final String CHANNEL_ID = "FPOLYTECHNIC";
// Channel_id được sử dụng để xác định kênh thông báo khi tạo hoặc cập nhật thông báo
    @Override
    public void onCreate() {
        super.onCreate();
        createChannelNotification();
    }
    // tạo phương thức kênh thông báo

    private void createChannelNotification(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O);
        // đăng ký notificationChannel
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID,"FPT POLYTECHNIC",
                NotificationManager.IMPORTANCE_DEFAULT);
        // đăng ký channel với hệ thống
        NotificationManager manager = getSystemService(NotificationManager.class);
        if (manager!=null){
            manager.createNotificationChannel(channel);
        }

    }
}
