package com.example.include.chenxinghua;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import static android.widget.Toast.*;

public class MyReceiverSOS extends BroadcastReceiver {
    public MyReceiverSOS() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
       /* throw new UnsupportedOperationException("Not yet implemented");*/
        Toast.makeText(context, intent.getAction(), LENGTH_LONG).show();
        Log.i("MyReceiverSOS", "Custom broadcast of Chen Xinghua");
        Log.i("MyReceiverSOS", intent.getAction());
    }
}
