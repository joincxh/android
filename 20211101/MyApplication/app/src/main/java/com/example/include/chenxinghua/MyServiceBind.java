package com.example.include.chenxinghua;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyServiceBind extends Service {
    public MyServiceBind() {
    }
    class MyBind extends Binder{
        /*public void callMethodInService(){
            methodInSer();//服务代理
        }*/
    }
    public void onCreate(){
        Log.i("MyServiceBind","创建服务，onCreate()");
        super.onCreate();
    }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        Log.i("MyServiceBind", "绑定服务，调用onBind()");
        return new MyBind();
    }
    public void methodInSer(){
        Log.i("MyServiceBind","自定义方法methodInSer()");
    }
    public boolean onUnbind(Intent intent){
        Log.i("MyServiceBind","解绑服务，调用onUnbind()");
        return super.onUnbind(intent);
    }
}
