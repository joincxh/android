package com.mk.chenxinghua.loginexample;
/* Creted by joincxh or include_chen*/

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private DbUtil dbUtil;
    private TextView mTvRegister;
    private EditText mEtUsername;
    private EditText mEtPassword;
    private Button mBtLLogin;
    SQLiteDatabase db;
    ContentValues values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        dbUtil = new DbUtil(this);
    }

    private void initView() {
        mBtLLogin = findViewById(R.id.bt_login);
        mTvRegister = findViewById(R.id.tv_register);
        mEtUsername = findViewById(R.id.et_username);
        mEtPassword = findViewById(R.id.et_password);
        mBtLLogin.setOnClickListener(this);
        mTvRegister.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_register:
                startActivity(new Intent(this, Regis.class));
                finish();
                break;
            case R.id.bt_login:
                String name = mEtUsername.getText().toString().trim();
                String password = mEtPassword.getText().toString().trim();
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password)) {
                    ArrayList<User> data = dbUtil.getAllData();
                    boolean match = false;
                    for (int i = 0; i < data.size(); i++) {
                        User user = data.get(i);
                        if (name.equals(user.getName()) && password.equals(user.getPassword())) {
                            match = true;
                            break;
                        } else {
                            match = false;
                        }
                    }
                    if (match) {
                        Toast.makeText(this, getString(R.string.ex1), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(this, MainHome.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(this, getString(R.string.ex2), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, getString(R.string.ex3), Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}