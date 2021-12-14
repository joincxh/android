package com.example.chenxinghua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ContentResolver resolver;
    private Uri uri;
    private ContentValues values;
    private static final  String textto="数据库应用：";
    private static final String autopath="content://com.example.chenxinghua/info";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
        createDB();
    }
    private void initview(){//获取crud监听
        findViewById(R.id.btnadd).setOnClickListener(this);
        findViewById(R.id.btndelete).setOnClickListener(this);
        findViewById(R.id.btnupdate).setOnClickListener(this);
        findViewById(R.id.btnselect).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        resolver=getContentResolver();
        uri=Uri.parse(autopath);
        values=new ContentValues();
        switch (v.getId()){
            case R.id.btnadd:
                Random random=new Random();
                values.put("name","add_example"+random.nextInt(20));
                Uri newuri=resolver.insert(uri,values);
                Toast.makeText(this,"The add is successful.",Toast.LENGTH_SHORT).show();
                Log.i(textto,"add");
                break;
            case R.id.btndelete:
                int deleteCount=resolver.delete(uri,"name=?",new String[]{"example0"});
                Toast.makeText(this,"The delete is successful.",Toast.LENGTH_SHORT).show();
                Log.i(textto,"delete");
                break;
            case R.id.btnupdate:
                values.put("name","update_example");
                int updateCount=resolver.update(uri,values,"name=?",new String[]{"example1"});
                Toast.makeText(this,"The sum of update is "+updateCount,Toast.LENGTH_SHORT).show();
                Log.i(textto,"update");
                break;
            case R.id.btnselect:
                List<Map<String,String>> data=new ArrayList<Map<String,String>>();
                Cursor cursor=resolver.query(uri,new String[]{"_id","name"},null,null,null);
                while (cursor.moveToNext()){
                    Map<String,String> map=new HashMap<String,String>();
                    map.put("_id",cursor.getString(0));
                    map.put("name",cursor.getString(1));
                    data.add(map);
                }
                cursor.close();
                Log.i(textto,"The result of search:"+data.toString());
                break;
        }
    }
    private void createDB(){
        UserDBOpenHelper helper=new UserDBOpenHelper(this);
        SQLiteDatabase db=helper.getWritableDatabase();
        for (int i=0;i<3;i++){
            ContentValues values=new ContentValues();
            values.put("name","example"+i);
            db.insert("info",null,values);
        }
    }
}