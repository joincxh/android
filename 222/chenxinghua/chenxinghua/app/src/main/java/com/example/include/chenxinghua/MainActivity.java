package com.example.include.chenxinghua;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int flag=0;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*声明对象*/
        final AlertDialog dialog;
       /* 绑定窗口  设置标题*/
        findViewById(R.id.tb1).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog dialog;
                        dialog = new AlertDialog.Builder(MainActivity.this).setTitle("普通dialog对话框")
                                .setMessage("是否确定退出") /*提示信息*/
                                .setIcon(R.mipmap.ic_launcher)   /*logo*/
                                .setPositiveButton("确定", null)  /*确定*/
                                .setNegativeButton("取消", null) /*取消*/
                                .create();
                        dialog.show();
                        Log.v("MainActivity","Verbose");  /*log日志信息*/
                    }
                }
        );
        /*普通对话框触发事件*/
        findViewById(R.id.tb2).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog dialog;
                        dialog = new AlertDialog.Builder(MainActivity.this).setTitle("性别选择")
                                .setIcon(R.mipmap.ic_launcher)   /*logo*/
                                .setSingleChoiceItems(new String[]{"男", "女"}, 0,
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                            }
                                        }
                                )
                                .setPositiveButton("确定", null)  /*确定*/
                                .create();
                        dialog.show();
                        Log.d("MainActivity", "Degug");  /*log日志信息*/
                    }
                }
        );/*单选对话框触发事件*/
        findViewById(R.id.tb3).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog dialog;
                        dialog = new AlertDialog.Builder(MainActivity.this).setTitle("添加兴趣爱好")
                                .setIcon(R.mipmap.ic_launcher)   /*logo*/
                                .setMultiChoiceItems(new String[]{"游泳", "旅游", "汽车", "美食", "美女", "帅哥", "宠物"}, null, null)
                                .setPositiveButton("确定", null)  /*确定*/
                                .setNegativeButton("取消", null) /*取消*/
                                .create();
                        dialog.show();
                        Log.i("MainActivity", "Info");  /*log日志信息*/
                    }
                }
        );  /*多选对话框触发事件*/
        findViewById(R.id.tb4).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       final ProgressDialog prodialog;

                        prodialog = new ProgressDialog(MainActivity.this);
                        prodialog.setTitle("进度条对话框");
                        prodialog.setMessage("正在下载请等候......"); /*提示信息*/
                        prodialog.setIcon(R.mipmap.ic_launcher) ;  /*logo*/
                        prodialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                        /*添加水平进度条*/
                        prodialog.setMax(200);
                        prodialog.setProgress(0);
                        prodialog.setSecondaryProgress(80);
                        prodialog.setIndeterminate(false);
                        prodialog.setCancelable(true);
                        prodialog.setButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();  /*取消*/
                            }
                        });
                        new Thread(){
                            public void run(){
                                try{
                                    while (flag<=200)
                                    {
                                        prodialog.setProgress(flag++);
                                        Thread.sleep(100);
                                    }
                                    prodialog.cancel();
                                }
                                catch(Exception e){
                                    prodialog.cancel();
                                }
                            }
                        }.start();
                        prodialog.show();
                        Log.w("MainActivity", "Warning");  /*log日志信息*/
                    }
                }
        );  /*进度条对话框触发事件*/
        findViewById(R.id.tb5).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this,"你好，安卓！",Toast.LENGTH_LONG).show();
                        Log.e("MainActivity", "Error");  /*log日志信息*/
                    }
                }
        );  /*消息对话框触发事件*/
        findViewById(R.id.tb6).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setContentView(R.layout.activity_main);
                        new_dialog dialog=new  new_dialog(MainActivity.this,null);
                        dialog.show();
                        Log.e("MainActivity", "Error");  /*log日志信息*/
                    }
                }
        );/*自定义对话框触发事件*/
    }
}
