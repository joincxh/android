package com.example.chenxinghuab;

import androidx.appcompat.app.AppCompatActivity;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String autopath="content://com.example.chenxinghua/info";
    private static final String textfaous="监测数据发生变化";
    private static final String textupdate="数据库被改动";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Uri uri=Uri.parse(autopath);
        getContentResolver().registerContentObserver(uri,true,new MyObserver(new Handler()));
    }
    private class MyObserver extends ContentObserver {

        /**
         * Creates a content observer.
         *
         * @param handler The handler to run {@link #onChange} on, or null if none.
         */
        public MyObserver(Handler handler) {
            super(handler);
        }
        public void onChange(boolean selfChange){
            Log.i(textfaous,textupdate);
            super.onChange(selfChange);
        /*    Log.i("监测数据变化","有人动了你的数据库");*/
        }
    }
}