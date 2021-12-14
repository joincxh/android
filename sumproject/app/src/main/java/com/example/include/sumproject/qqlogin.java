package com.example.include.sumproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class qqlogin extends AppCompatActivity {
Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qqlogin);
    /*    button = (Button)findViewById(R.id.logonbtn);
        button=(Button)findViewById(R.id.returnto);*/
        findViewById(R.id.logonbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(qqlogin.this, MainActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.returnto).setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v){
                Intent intent2=new Intent("firstactivity");/*隐式调用*/
             /*   intent2.setComponent(new ComponentName(qqlogin.this,MainActivity.class));*/
                startActivity(intent2);
            }
        });
    }
}
