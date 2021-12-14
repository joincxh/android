package com.example.myapplication.Control;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Model.UserBean;
import com.example.myapplication.Model.UserInfo;
import com.example.myapplication.R;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class MainActivity3 extends AppCompatActivity implements View.OnClickListener{
UserBean userBean=new UserBean();
UserInfo userInfo=new UserInfo();
    EditText re_name;
    EditText repassword;
    EditText resname;
    EditText rephone;
    EditText remail;
    EditText rejob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        findViewById(R.id.btn_register).setOnClickListener(this);
         re_name=(EditText)findViewById(R.id.re_name);
         repassword=(EditText)findViewById(R.id.re_password);
         resname=(EditText)findViewById(R.id.re_sname);
         rephone=(EditText)findViewById(R.id.re_phone);
         remail=(EditText)findViewById(R.id.re_email);
         rejob=(EditText)findViewById(R.id.re_job);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_register:
                /*Toast.makeText(this,"注册成功",Toast.LENGTH_SHORT).show();*/
                register();
                break;
            default:
                break;
        }
    }
    public void register(){
        BmobUser bmobUser=new BmobUser();
        bmobUser.setUsername(re_name.getText().toString());
        final String name=re_name.getText().toString();
        bmobUser.setPassword(repassword.getText().toString());
        final String password=repassword.getText().toString();
        userInfo.setSname(resname.getText().toString());
        userInfo.setPhone(rephone.getText().toString());
        userInfo.setEmail(remail.getText().toString());
        userInfo.setJob(rejob.getText().toString());
        if (TextUtils.isEmpty(name)||TextUtils.isEmpty(password)){
            toast("账号或者密码不能为空！");
            return;
        }
        userInfo.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if (e==null){
                    /*toast("注册成功！");*/
                }else {
                    toast("注册失败！" + e.getMessage());
                }
            }
        });
        bmobUser.signUp(new SaveListener<BmobUser>() {
            @Override
            public void done(BmobUser bmobUser, BmobException e) {
                if(e==null){
                    toast("注册成功！");
                    finish();//注销
                   /* Intent intent=new Intent(MainActivity3.this,MainActivity2.class);
                    startActivity(intent);*/
                }else{
                    toast("注册失败！" + e.getMessage());
                }
            }
           /* @Override
            public void done(String objectId, BmobException e) {
               *//* if(e==null){
                    toast("注册成功！");
                    Intent intent=new Intent(MainActivity3.this,MainActivity2.class);
                    startActivity(intent);
                }else{
                    toast("注册失败！" + e.getMessage());
                }*//*
            }*/
        });

    }
    public void toast(String s) {
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }
}