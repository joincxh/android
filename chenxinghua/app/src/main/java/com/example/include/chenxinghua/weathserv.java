package com.example.include.chenxinghua;

import android.util.Xml;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.xmlpull.v1.XmlPullParser;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2021/10/4.
 */
public class weathserv {/*工具类*/
    public static List<weathinfo> getInfoFromXml(InputStream is)/*XML格式*/
        throws Exception{
        XmlPullParser parser= Xml.newPullParser();
        parser.setInput(is,"utf-8");
        List<weathinfo> weathinfos=null;
        weathinfo weathin=null;
        int type=parser.getEventType();
        while (type!=XmlPullParser.END_DOCUMENT){
            switch (type){
                case XmlPullParser.START_TAG:
                    if ("infos".equals(parser.getName())){
                        weathinfos=new ArrayList<weathinfo>();
                    }else if ("city".equals(parser.getName())){
                        weathin=new weathinfo();
                        String idStr=parser.getAttributeValue(0);
                        weathin.setId(idStr);
                    }else if ("temp".equals(parser.getName())){
                        String temp=parser.nextText();
                        weathin.setTemp(temp);
                    }else if ("weath".equals(parser.getName())){
                        String weath=parser.nextText();
                        weathin.setWeath(weath);
                    }else if ("cname".equals(parser.getName())){
                        String cname=parser.nextText();
                        weathin.setCname(cname);
                    }else if ("pm".equals(parser.getName())){
                        String pm=parser.nextText();
                        weathin.setPm(pm);
                    }else if ("wind".equals(parser.getName())){
                        String wind=parser.nextText();
                        weathin.setWind(wind);
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if ("city".equals(parser.getName())){
                        weathinfos.add(weathin);
                        weathin=null;
                    }
                    break;
            }
            type=parser.next();
        }
        return weathinfos;
    }
    public static List<weathinfo> getInfosFromJSON(InputStream is)/*JSON格式*/
    throws IOException{
        byte[] buffer=new byte[is.available()];
        is.read(buffer);
        String json=new String(buffer,"utf-8");
        Gson gson=new Gson();
        Type listType=new TypeToken<List<weathinfo>>(){}.getType();
        List<weathinfo> weathinfos=gson.fromJson(json,listType);
        return weathinfos;
    }
}
