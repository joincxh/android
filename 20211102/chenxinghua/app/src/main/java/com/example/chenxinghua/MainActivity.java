package com.example.chenxinghua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private MyServiceBind.MyBind myBind;
    private MyConn myConn;
    /* private EditText path;*/
    /*private Intent intent3;*/
    /*private myConnMusic connMusic;
    MyServiceMusic.MyBinder binder;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnstart=(Button)findViewById(R.id.start);
        Button btnstop=(Button)findViewById(R.id.stop);
        Button btnsave=(Button)findViewById(R.id.save);
        Button btnclear=(Button)findViewById(R.id.clear);
        findViewById(R.id.play).setOnClickListener(this);
        btnstart.setOnClickListener(this);
        btnstop.setOnClickListener(this);
        btnsave.setOnClickListener(this);
        btnclear.setOnClickListener(this);
       /* connMusic=new myConnMusic();
        intent3=new Intent(this,MyServiceMusic.class);
        bindService(intent3,connMusic,BIND_AUTO_CREATE);*/
    }

    /*private class myConnMusic implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder=(MyServiceMusic.MyBinder)service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i("MainActivity","内存溢出");
        }
    }*/
    private class MyConn implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBind=(MyServiceBind.MyBind)service;
            Log.i("MainActivity","服务成功绑定，内存地址为："+myBind.toString());
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
    @Override
    public void onClick(View v) {
        /* String pathway=path.getText().toString().trim();*/
        switch (v.getId()){
            case R.id.start://开启服务
                Intent intent0=new Intent(this,MyService.class);
                startService(intent0);
                break;
            case R.id.stop://停止服务
                Intent intent1=new Intent(this,MyService.class);
                stopService(intent1);
                break;
            case R.id.save://绑定
                if (myConn==null){
                    myConn=new MyConn();
                }
                Intent intent2=new Intent(this,MyServiceBind.class);
                bindService(intent2, myConn, BIND_AUTO_CREATE);//创建绑定服务
                break;
            case R.id.clear://解绑
                /* myBind.callMethInser();//自定义方法调用*/
                if (myConn!=null){//解绑服务
                    unbindService(myConn);
                    myConn=null;
                }
                break;
            case R.id.play:
                Intent intent3=new Intent(MainActivity.this,MainActivity2.class);//跳转到音乐服务主类
                startActivity(intent3);
                break;
            default:
                break;
        }
    }
 /*   protected void onDestroy(){
        unbindService(connMusic);
        super.onDestroy();
    }*/
}