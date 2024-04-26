package com.example.lab6;

import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyService extends android.app.Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
         return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "gọi oncreate", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Thông tin onstart", Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Hủy service", Toast.LENGTH_SHORT).show();
    }
}
