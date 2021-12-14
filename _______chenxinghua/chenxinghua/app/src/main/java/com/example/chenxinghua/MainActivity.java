package com.example.chenxinghua;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    protected static final int CHANGE_UI=1;
    protected static final int ERROR=2;
    private EditText et_path;
    ProgressDialog progressDialog;
    private ImageView ivPic;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what==CHANGE_UI){
                Bitmap bitmap=(Bitmap) msg.obj;
                ivPic.setImageBitmap(bitmap);
            }else if (msg.what==ERROR){
                Toast.makeText(MainActivity.this,"photo is eror.",Toast.LENGTH_SHORT).show();
            }
            /*super.handleMessage(msg);*/
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_path=(EditText) findViewById(R.id.et_path);
        ivPic=(ImageView) findViewById(R.id.iv_pic);
        findViewById(R.id.bt_liulan).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_liulan://浏览直接赋值浏览地址
                Handler handler1=new Handler();
                Runnable runnable=new Runnable() {
                    @Override
                    public void run() {
                        dialogview();
                    }
                };
                handler1.postAtTime(runnable,3000);
                String imagepath="https://img2.baidu.com/it/u=2005393652,4064638024&fm=26&fmt=auto";
                et_path.setText(imagepath);
                /*download();*/
                break;
            default:break;
        }
    }
   /* protected void onDestroy(){
        super.onDestroy();
        if (handler1!=null){
            handler1.postDelayed(runnable,3000);//3秒延迟后下载完成
            handler1.removeCallbacks(runnable);
        }
    }*/
    public void dialogview(){
        progressDialog =new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("浏览进度");
        progressDialog.setIcon(R.mipmap.ic_launcher);
        progressDialog.setMessage("正在刷新浏览图片，请等候......");
        progressDialog.setCancelable(true);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        final Timer t=new Timer();
        t.schedule(
                new TimerTask() {
            @Override
            public void run() {
            progressDialog.dismiss();
            t.cancel();
            download();
            }
        },3000);//3s后自动消失

    }
    public void download(){
        final String path=et_path.getText().toString().trim();
        if (TextUtils.isEmpty(path)){
            Toast.makeText(MainActivity.this,"path of image can't empty.",Toast.LENGTH_SHORT).show();
        }else {
            new Thread(){
                private HttpURLConnection connection;
                private Bitmap bitmap;
                public void run(){
                    try {
                        URL url=new URL(path);
                        connection=(HttpURLConnection) url.openConnection();
                        connection.setRequestMethod("GET");
                        connection.setConnectTimeout(5000);
                        int code =connection.getResponseCode();
                        if (code==200){
                            //success
                            InputStream inputStream=connection.getInputStream();
                            bitmap = BitmapFactory.decodeStream(inputStream);
                            Message message=new Message();
                            message.what=CHANGE_UI;
                            message.obj=bitmap;
                            handler.sendMessage(message);
                        }else {
                            //error
                            Message message=new Message();
                            message.what=ERROR;
                            handler.sendMessage(message);
                        }
                    }catch (Exception e){
                            e.printStackTrace();
                            Message message=new Message();
                            message.what=ERROR;
                            handler.sendMessage(message);
                    }
                    connection.disconnect();
                }
            }.start();
        }
    }
}