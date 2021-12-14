package com.example.include.chenxinghua;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText et_password;
    private Button loginbtn;
    private EditText et_name;
    private Button returnto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initv();
        Map<String,String> userinfo=FileQQ.getUserIn(this);
        if (userinfo!=null){//初始化
            et_name.setText("");
            et_password.setText("");
        }
        getlopd();//显示一次账号信息
        findViewById(R.id.loginbtn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                et_name = (EditText) findViewById(R.id.et_name);
                et_password = (EditText) findViewById(R.id.et_password);
                loginbtn = (Button) findViewById(R.id.loginbtn);
                loginbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        passDate();
                    }
                });
            }
        });

        findViewById(R.id.returnto).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                et_name=(EditText)findViewById(R.id.et_name);
                et_password =(EditText)findViewById(R.id.et_password);
                returnto=(Button)findViewById(R.id.returnto);
                returnto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        savepwd();//保存账号密码
                    }
                });
            /*    Intent intent2 = new Intent();*//*隐式调用*/
             /*   intent2.setComponent(new ComponentName(qqlogin.this,MainActivity.class));*/
                /*intent2.setAction("android.intent.action.VIEW");
                intent2.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent2);*/
            }
        });

    }
    private void getlopd(){//显示账号信息
        Map<String,String> userinfo=FileQQ.getUserIn(this);
        TextView mloname=(TextView)findViewById(R.id.discovername);
        TextView mpwd=(TextView)findViewById(R.id.discoverpwd);
        String loname=userinfo.get("name").toString().trim();
        String lopassword=userinfo.get("password").toString().trim();
        mloname.setText("账号:"+loname);
        mpwd.setText("密码:"+lopassword);
    }
    private  void  initv(){
        et_name=(EditText)findViewById(R.id.et_name);
        et_password =(EditText)findViewById(R.id.et_password);
        loginbtn = (Button) findViewById(R.id.loginbtn);
        returnto=(Button)findViewById(R.id.returnto);
    }
    public void savepwd(){//保存
        String name=et_name.getText().toString().trim();
        String password=et_password.getText().toString().trim();
        if (TextUtils.isEmpty(name)){//判空
            Toast.makeText(this,"请输入账号",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this,"请输入密码",Toast.LENGTH_SHORT).show();
            return;
        }
        boolean issave=FileQQ.saveinfo(this,name,password);//保存账号密码
        if (issave){
            Toast.makeText(this,"注册成功",Toast.LENGTH_SHORT).show();
            getlopd();//刷新一次
        }else {
            Toast.makeText(this,"注册失败",Toast.LENGTH_SHORT).show();
        }
    }
    public void passDate(){//登录
        Map<String,String> userinfo=FileQQ.getUserIn(this);
        Intent intent=new Intent();
        String name=et_name.getText().toString().trim();
        String password=et_password.getText().toString().trim();
        String loname=userinfo.get("name").toString().trim();
        String lopassword=userinfo.get("password").toString().trim();
        if (TextUtils.isEmpty(name)){//判空
            Toast.makeText(this,"请输入账号",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this,"请输入密码",Toast.LENGTH_SHORT).show();
            return;
        }
        if (name.equals(loname)&&password.equals(lopassword)){//登录判定  需要完善
            Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show();
            intent=new Intent(MainActivity.this,weathmain.class);//跳转新界面 主界面
            startActivity(intent);
        }else {
            Toast.makeText(this,"账号或密码错误，登录失败",Toast.LENGTH_SHORT).show();
            return;
        }
        /*intent.putExtra("name", et_name.getText().toString().trim());
        intent.putExtra("password",et_password.getText().toString().trim());*/
    }
}
