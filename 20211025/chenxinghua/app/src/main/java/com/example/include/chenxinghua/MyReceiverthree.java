package com.example.include.chenxinghua;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiverthree extends BroadcastReceiver {
    public MyReceiverthree() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        /*throw new UnsupportedOperationException("Not yet implemented");*/
        Log.i("MyReceiverthree", "自定义广播接收者three，接收到了广播事件");
    }
}
