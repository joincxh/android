package com.example.include.chenxinghua;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button sosbtn;
    private Button orderbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sosbtn=(Button)findViewById(R.id.sos);
        orderbtn=(Button)findViewById(R.id.order);
        sosbtn.setOnClickListener(this);
        orderbtn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sos:
                Intent intent1=new Intent();
                intent1.setAction("chenxinghua5122512020080");
                sendBroadcast(intent1);
                break;
            case R.id.order:
                Intent intent2=new Intent();
                intent2.setAction("chenxinghua080");
                sendOrderedBroadcast(intent2, null);
                break;
            default:
                break;
        }
    }
}
