package com.example.include.chenxinghua;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        /*throw new UnsupportedOperationException("Not yet implemented");*/
        return null;//TODO:return
    }
    public void onCreate(){
        super.onCreate();
        Log.i("StartService","onCreate()");
    }
    public int onStartCommand(Intent intent,int flags,int startId){
        Log.i("StartService","onStartCommand()");
        return super.onStartCommand(intent,flags,startId);
    }
    public void onDestroy(){
        super.onDestroy();
        Log.i("StartService","onDestroy()");
    }
}
