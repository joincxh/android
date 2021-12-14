package com.example.include.chenxinghua;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiverone extends BroadcastReceiver {
    public MyReceiverone() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        /*throw new UnsupportedOperationException("Not yet implemented");*/
        Log.i("MyReceiverone","自定义广播接收者one，接收到了广播事件");
        abortBroadcast();
        Log.i("MyReceiverone","我是广播接收者one，广播接收者three被我终结了");
    }
}
