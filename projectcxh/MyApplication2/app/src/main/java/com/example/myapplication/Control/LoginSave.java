package com.example.myapplication.Control;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

public class LoginSave {
    public static boolean savelogin(Context context,boolean flag){
        SharedPreferences sp=context.getSharedPreferences("loginload",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sp.edit();
        editor.putBoolean("loginflag",flag);
        editor.commit();
        return true;
    }
    public static Map<Boolean,Boolean> getlogin(Context context){
        SharedPreferences sp=context.getSharedPreferences("loginload",Context.MODE_PRIVATE);
      /*  SharedPreferences.Editor editor= sp.edit();*/
        boolean flaging=sp.getBoolean("loginflag",false);
        Map<Boolean,Boolean> loginmap=new HashMap<Boolean,Boolean>();
        loginmap.put(Boolean.valueOf("loginflag"),flaging);
        return loginmap;
    }
}

