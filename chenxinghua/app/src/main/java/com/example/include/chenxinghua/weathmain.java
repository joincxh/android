package com.example.include.chenxinghua;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class weathmain extends AppCompatActivity implements View.OnClickListener {

    private TextView tvCity;
    private TextView tvWeath;
    private TextView tvTemp;
    private TextView tvWind;
    private TextView tvPm;
    private ImageView ivIcon;
    private Map<String,String> map;
    private List<Map<String,String>> list;
    private String temp,weath,cname,pm,wind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weathmain);
        initview();
        try {
            InputStream is=this.getResources().openRawResource(R.raw.weathone);/*xml资源*/
            List<weathinfo> weathinfos=weathserv.getInfoFromXml(is);/*xml格式*/
            list=new ArrayList<Map<String,String>>();
            for (weathinfo info:weathinfos){
                map=new HashMap<String,String>();
                map.put("temp",info.getTemp());
                map.put("weath",info.getWeath());
                map.put("cname",info.getCname());
                map.put("pm",info.getPm());
                map.put("wind",info.getWind());
                list.add(map);
            }
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this,"解析信息失败",Toast.LENGTH_SHORT).show();
        }
        getMap(1,R.drawable.sun);

    }
    public void initview(){
        tvCity=(TextView)findViewById(R.id.tv_city);
        tvWeath=(TextView)findViewById(R.id.tv_weather);
        tvTemp=(TextView)findViewById(R.id.tv_temp);
        tvWind=(TextView)findViewById(R.id.tv_wind);
        tvPm=(TextView)findViewById(R.id.tv_pm);
        ivIcon=(ImageView)findViewById(R.id.iv_icon);
        findViewById(R.id.btn_sh).setOnClickListener(this);
        findViewById(R.id.btn_bj).setOnClickListener(this);
        findViewById(R.id.btn_gz).setOnClickListener(this);
        findViewById(R.id.xmb).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_sh:
                getMap(0,R.drawable.cloud_sun);
                break;
            case R.id.btn_bj:
                getMap(1,R.drawable.sun);
                break;
            case R.id.btn_gz:
                getMap(2,R.drawable.clouds);
                break;
            case R.id.xmb:
                Intent intent=new Intent(this,weathsec.class);//跳转JSON界面
                startActivity(intent);
                break;
        }
    }
    private void getMap(int number,int iconNumber){
        Map<String,String> cityMap=list.get(number);
        temp=cityMap.get("temp");
        weath=cityMap.get("weath");
        cname=cityMap.get("cname");
        pm=cityMap.get("pm");
        wind=cityMap.get("wind");
        tvCity.setText(cname);
        tvWeath.setText(weath);
        tvTemp.setText(""+temp);
        tvWind.setText("风力："+wind);
        tvPm.setText("pm:"+pm);
        ivIcon.setImageResource(iconNumber);
    }
}
