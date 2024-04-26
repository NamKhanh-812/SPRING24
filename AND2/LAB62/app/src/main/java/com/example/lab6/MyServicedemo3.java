package com.example.lab6;

import static com.example.lab6.Myapplication.CHANNEL_ID;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class MyServicedemo3 extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onSmtartCommand(Intent intent, int flags, int startId) {
        String ten = intent.getStringExtra("ten");
        sendNotification(ten);
        return START_NOT_STICKY;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    private void sendNotification(String data){
        //sử dụng intent or bundle để nhận dữ liệu trong service
        Intent intent=new Intent(this,MainActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,
                intent,PendingIntent.FLAG_UPDATE_CURRENT);
        //tạo ra một thông báo và truyền nó vào startForeground services
        Notification notification=new NotificationCompat.Builder(this,CHANNEL_ID)
                .setContentTitle("Chào mừng bạn đến với FPT")
                .setContentText(data)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentIntent(pendingIntent).build();
        //id của startforeground sẽ là 1 số lớn hơn 0
        startForeground(1,notification);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Tạm biệt", Toast.LENGTH_SHORT).show();
    }
}
