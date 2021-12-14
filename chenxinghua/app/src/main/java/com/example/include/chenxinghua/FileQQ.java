package com.example.include.chenxinghua;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2021/10/4.
 */
public class FileQQ {
    //保存账号密码至xml中
    public static boolean saveinfo(Context context,String name,String password){
        SharedPreferences sp=context.getSharedPreferences("data",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("username",name);
        editor.putString("userpwd",password);
        editor.commit();
        return true;
    }
    //获取账号密码
    public static Map<String,String> getUserIn(Context context){
        SharedPreferences sp=context.getSharedPreferences("data",Context.MODE_PRIVATE);
        String name=sp.getString("username", null);
        String password=sp.getString("userpwd",null);
        Map<String,String> usermap=new HashMap<String,String>();
        usermap.put("name",name);
        usermap.put("password",password);
        return usermap;
    }
}
