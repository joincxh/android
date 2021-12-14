package com.example.myapplication.Control;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.R;

public class MainActivity4 extends AppCompatActivity implements View.OnClickListener{
    public static boolean loginload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        findViewById(R.id.quit).setOnClickListener(this);
    }
    public void Loginquit(){
        boolean loginload=false;
        LoginSave.savelogin(this,loginload);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.quit:
                Loginquit();
                Intent intent=new Intent(this,MainActivity2.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}