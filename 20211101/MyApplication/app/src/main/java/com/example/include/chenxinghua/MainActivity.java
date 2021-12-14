package com.example.include.chenxinghua;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private MyServiceBind.MyBind myBind;
    private MyConn myConn;
   /* private EditText path;*/
    private Intent intent3;
    private myConnMusic connMusic;
    MyServiceMusic.MyBinder binder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnstart=(Button)findViewById(R.id.start);
        Button btnstop=(Button)findViewById(R.id.stop);
        Button btnsave=(Button)findViewById(R.id.save);
        Button btnclear=(Button)findViewById(R.id.clear);
        findViewById(R.id.play).setOnClickListener(this);
        findViewById(R.id.pause).setOnClickListener(this);
       /* path=(EditText)findViewById(R.id.input);*/
        btnstart.setOnClickListener(this);
        btnstop.setOnClickListener(this);
        btnsave.setOnClickListener(this);
        btnclear.setOnClickListener(this);
        connMusic=new myConnMusic();
        intent3=new Intent(this,MyServiceMusic.class);
        bindService(intent3,connMusic,BIND_AUTO_CREATE);
    }
    private class myConnMusic implements ServiceConnection{
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder=(MyServiceMusic.MyBinder)service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i("MainActivity","内存溢出");
        }
    }
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
            case R.id.start:
                Intent intent0=new Intent(this,MyService.class);
                startService(intent0);
                break;
            case R.id.stop:
                Intent intent1=new Intent(this,MyService.class);
                stopService(intent1);
                break;
            case R.id.save:
                if (myConn==null){
                    myConn=new MyConn();
                }
                Intent intent2=new Intent(this,MyServiceBind.class);
                bindService(intent2, myConn, BIND_AUTO_CREATE);//创建绑定服务
                break;
            case R.id.clear:
               /* myBind.callMethInser();//自定义方法调用*/
                if (myConn!=null){//解绑服务
                    unbindService(myConn);
                    myConn=null;
                }
                break;
            case R.id.play:
                binder.Play();
                break;
            case R.id.pause:
                binder.Pause();
                break;
            default:
                break;
        }
    }
    protected void onDestroy(){
        unbindService(connMusic);
        super.onDestroy();
    }
}
