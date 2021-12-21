package com.mk.chenxinghua.loginexample;
/* Creted by joincxh or include_chen*/

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Regis extends AppCompatActivity implements View.OnClickListener {

    private String geoCode;
    private DbUtil dbUtil;
    private Button mBtRegister;
    private EditText mEtUsername;
    private EditText mEtRegister2;
    private EditText mEtPhonecodes;
    private ImageView mIvShowcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regis);
        initView();
        dbUtil = new DbUtil(this);
        mIvShowcode.setImageBitmap(GeoCode.getInstance().createBitmap());
        geoCode = GeoCode.getInstance().getCode().toLowerCase();
    }

    private void initView() {
        mBtRegister = findViewById(R.id.bt_register);
        mEtUsername = findViewById(R.id.et_username);
        mEtRegister2 = findViewById(R.id.et_password2);
        mEtPhonecodes = findViewById(R.id.et_phoneCodes);
        mIvShowcode = findViewById(R.id.iv_showCode);
        mIvShowcode.setOnClickListener(this);
        mBtRegister.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_showCode:
                mIvShowcode.setImageBitmap(GeoCode.getInstance().createBitmap());
                geoCode = GeoCode.getInstance().getCode().toLowerCase();
                break;
            case R.id.bt_register:
                String username = mEtUsername.getText().toString().trim();
                String password = mEtRegister2.getText().toString().trim();
                String phoneCode = mEtPhonecodes.getText().toString().toLowerCase();
                if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(phoneCode)) {
                    if (phoneCode.equals(geoCode)) {
                        dbUtil.add(username, password);
                        Intent intent2 = new Intent(this, Login.class);
                        startActivity(intent2);
                        finish();
                        Toast.makeText(this, getString(R.string.ex4), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, getString(R.string.ex5), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, getString(R.string.ex6), Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}