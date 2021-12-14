package com.example.myapplication.Control;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Model.UserBean;
import com.example.myapplication.R;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {
    UserBean userBean=new UserBean();
    EditText ed_name;
    EditText ed_password;
/*    Handler handler=new Handler();*/
    public static boolean loginload=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
     /*   userBean.setName("cxh");
        userBean.setPassword("123");
        userBean.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if(e==null){
                    toast("添加数据成功，返回objectId为："+objectId);
                }else{
                    toast("创建数据失败：" + e.getMessage());
                }
            }
        });*/
        findViewById(R.id.loginbtn).setOnClickListener(this);
        findViewById(R.id.btn_registertext).setOnClickListener(this);
        ed_name=(EditText)findViewById(R.id.ed_name);
        ed_password=(EditText)findViewById(R.id.ed_password);
    }
    public void toast(String s) {
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.loginbtn:
             /*   login();*/
                final String name=ed_name.getText().toString();
                final String password=ed_password.getText().toString();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        BmobUser bmobUser=new BmobUser();
                        bmobUser.setUsername(name);
                        bmobUser.setPassword(password);
                        bmobUser.login(new SaveListener<BmobUser>() {
                            @Override
                            public void done(BmobUser bmobUser, BmobException e) {
                                if (e==null){
                                    /*loginload=true;
                                    Map<Boolean,Boolean> loginloding=LoginSave.savelogin(this,loginload);*/
                                    Logining();
                                    Intent intent = new Intent(MainActivity2.this, MainActivity4.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    if (name.isEmpty()){
                                        toast("账号不能为空！");
                                    }else if (password.isEmpty()){
                                        toast("密码不能为空！");
                                    }else {
                                        toast("账号或者密码错误！");
                                    }
                                }
                            }
                        });
                    }
                },3000);

                break;
            case R.id.btn_registertext:
                Intent intent2=new Intent(this,MainActivity3.class);
                startActivity(intent2);
                break;
        }
    }
    public void Logining(){
        loginload=true;
        LoginSave.savelogin(this,loginload);
    }
   /* public void login(){


       *//* BmobQuery<UserBean> is=new BmobQuery<>();*//*
   *//*     if ((ed_name.getText().toString().equals(userBean.getName()))&(ed_password.getText().toString()
        .equals(userBean.getPassword()))){
            Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this,MainActivity4.class);
            startActivity(intent);
        }else if (name.isEmpty()){
            toast("账号不能为空！");
            return;
        }else if (password.isEmpty()){
            toast("密码不能为空！");
            return;
        }else {
            toast("账号或者密码错误！");
            return;
        }*//*

    }*/
}