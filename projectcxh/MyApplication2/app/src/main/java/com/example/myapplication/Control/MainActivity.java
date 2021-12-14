package com.example.myapplication.Control;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.example.myapplication.R;

import java.util.Map;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //提供以下两种方式进行初始化操作：

        //第一：默认初始化
        //Bmob.initialize(this, "63fb4bfec0d7005b2e71c13ecb445795");
        // 注:自v3.5.2开始，数据sdk内部缝合了统计sdk，开发者无需额外集成，传渠道参数即可，不传默认没开启数据统计功能
       // Bmob.initialize(this, "63fb4bfec0d7005b2e71c13ecb445795","bmob");

        //第二：自v3.4.7版本开始,设置BmobConfig,允许设置请求超时时间、文件分片上传时每片的大小、文件的过期时间(单位为秒)，
        BmobConfig config =new BmobConfig.Builder(this)
        ////设置appkey
        .setApplicationId("63fb4bfec0d7005b2e71c13ecb445795")
        ////请求超时时间（单位为秒）：默认15s
        .setConnectTimeout(30)
        ////文件分片上传时每片的大小（单位字节），默认512*1024
        .setUploadBlockSize(1024*1024)
        ////文件的过期时间(单位为秒)：默认1800s
        .setFileExpiration(2500)
        .build();
        Bmob.initialize(config);
        final View view = View.inflate(this, R.layout.activity_main, null);
        setContentView(view);
        //渐变展示启动屏
        AlphaAnimation aa = new AlphaAnimation(0.3f,1.0f);
        aa.setDuration(2000);
        view.startAnimation(aa);
        aa.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationEnd(Animation arg0) {
                redirectTo();
            }
            @Override
            public void onAnimationRepeat(Animation animation) {}
            @Override
            public void onAnimationStart(Animation animation) {}
        });
    }
    /**
     * 跳转到...
     */
    private void redirectTo(){
        Map<Boolean,Boolean> loginloding=LoginSave.getlogin(this);
        boolean flag= loginloding.get(Boolean.valueOf("loginflag"));
        if (flag){//已登录
            Intent intent = new Intent(this, MainActivity4.class);
            startActivity(intent);
            finish();
        }else {//未登录
            Intent intent = new Intent(this, MainActivity2.class);
            startActivity(intent);
            finish();
        }


    }
}