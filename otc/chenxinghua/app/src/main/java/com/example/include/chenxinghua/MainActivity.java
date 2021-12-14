package com.example.include.chenxinghua;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    MyHelper myHelper;
    private EditText sname;
    private EditText sphone;
    private ListView sshow;
    private TextView mTVshow;
    private Button btnadd;
    private Button btnarry;
    private Button btndel;
    private Button btnsim;
    private Button btnbase;
    private Button btnalt;
    private Button btnser;
    private ArrayAdapter<String> adapter;

    String name;
    String phone;
    SQLiteDatabase db;
    ContentValues values;
    public String[] sids = {"QQ", "微信", "淘宝", "天猫", "微闽科", "一梦江湖", "斗地主"};
    public int[] backids = {R.drawable.s1, R.drawable.s2, R.drawable.s3, R.drawable.s4, R.drawable.s5, R.drawable.s6, R.drawable.s7};
    /*   String []names={"1","2","3","4","5","6","7"};*/
    ArrayList<HashMap<String, String>> userlist = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();/*视图初始化*/
        clearview();/*清除输入框*/
        myHelper = new MyHelper(this);
        serall();/*显示全部联系人*/
    }

    @Override
    public void onClick(View v) {
        /*String name;
        String phone;
        SQLiteDatabase db;
        ContentValues values;*/
        switch (v.getId()) {
            case R.id.bt_add:
                add();/*添加*/
                break;
            case R.id.bt_del:
                del();/*删除*/
                break;
            case R.id.bt_alt:
                alt();/*修改*/
                break;
            case R.id.bt_ser:
                ser();/*查询*/
                break;
            case R.id.bt_arr1:
                sshow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(MainActivity.this, "姓名：" + userlist.get(position).get("name") + "   电话：" + userlist.get(position).get("phone"), Toast.LENGTH_SHORT).show();
                    }
                });
                arr1();
                break;
            case R.id.bt_simple:
                sshow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(MainActivity.this, "姓名：" + userlist.get(position).get("name") + "   电话：" + userlist.get(position).get("phone"), Toast.LENGTH_SHORT).show();
                    }
                });
                simp();
                break;
            case R.id.bt_base:
                sshow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(MainActivity.this,"商家："+sids[position], Toast.LENGTH_SHORT).show();
                    }
                });
                bbase();
                break;
            default:
                break;
        }
    }

    public void initview() {/*初始化封装函数*/
        sname = (EditText) findViewById(R.id.et_name);
        sphone = (EditText) findViewById(R.id.et_phone);
        sshow = (ListView) findViewById(R.id.show);
        mTVshow = (TextView) findViewById(R.id.tv_show);
        btnarry = (Button) findViewById(R.id.bt_arr1);
        btnadd = (Button) findViewById(R.id.bt_add);
        btndel = (Button) findViewById(R.id.bt_del);
        btnalt = (Button) findViewById(R.id.bt_alt);
        btnser = (Button) findViewById(R.id.bt_ser);
        btnbase = (Button) findViewById(R.id.bt_base);
        btnsim = (Button) findViewById(R.id.bt_simple);
        btnadd.setOnClickListener(this);
        btndel.setOnClickListener(this);
        btnalt.setOnClickListener(this);
        btnser.setOnClickListener(this);
        btnsim.setOnClickListener(this);
        btnarry.setOnClickListener(this);
        btnbase.setOnClickListener(this);

    }

    public void clearview() {
        sname.setText("");
        sphone.setText("");
    }

    public void add() {
        name = sname.getText().toString();
        phone = sphone.getText().toString();
        db = myHelper.getWritableDatabase();
        values = new ContentValues();
        values.put("name", name);
        values.put("phone", phone);
        db.insert("information", null, values);
        Toast.makeText(this, "联系人已经添加成功", Toast.LENGTH_SHORT).show();
       /* ser();*/
        serall();
        clearview();
        db.close();
    }

    public void del() {
        db = myHelper.getWritableDatabase();
        db.delete("information", "name=?", new String[]{sname.getText().toString()});
        Toast.makeText(this, "联系人信息删除成功", Toast.LENGTH_SHORT).show();
        clearview();
        serall();
        db.close();
    }

    public void alt() {
        db = myHelper.getWritableDatabase();
        values = new ContentValues();
        values.put("phone", phone = sphone.getText().toString());
        db.update("information", values, "name=?", new String[]{sname.getText().toString()});
        Toast.makeText(this, "联系人信息更新成功", Toast.LENGTH_SHORT).show();
        ser();
        serall();
        clearview();
        db.close();
    }

    public void ser() {
        db = myHelper.getReadableDatabase();
        userlist.clear();
        Cursor cursor = db.query("information", null, null, null, null, null, null);
        if (cursor.getCount() == 0) {
            mTVshow.setText("联系人不存在");
            Toast.makeText(this, "找不到该联系人", Toast.LENGTH_SHORT).show();
        } else {
            cursor.moveToFirst();
            mTVshow.setText("姓名：" + cursor.getString(1) + "   电话：" + cursor.getString(2));
            HashMap user = new HashMap();
            user.put("name", cursor.getString(1));
            user.put("phone", cursor.getString(2));
            userlist.add(user);
        }
        while (cursor.moveToNext()) {
            mTVshow.append("\n" + "姓名：" + cursor.getString(1) + "   电话：" + cursor.getString(2));
            HashMap user = new HashMap();
            user.put("name", cursor.getString(1));
            user.put("phone", cursor.getString(2));
            userlist.add(user);
        }
        cursor.close();
        db.close();
    }

    public void serall() {
        db = myHelper.getReadableDatabase();
        Cursor cursor = db.query("information", null, null, null, null, null, null);
        if (cursor.getCount() == 0) {
            mTVshow.setText("联系人为空");
            Toast.makeText(this, "空联系人", Toast.LENGTH_SHORT).show();
        } else {
            cursor.moveToFirst();
            mTVshow.setText("姓名：" + cursor.getString(1) + "   电话：" + cursor.getString(2));
        }
        while (cursor.moveToNext()) {
            mTVshow.append("\n" + "姓名：" + cursor.getString(1) + "   电话：" + cursor.getString(2));
        }
        cursor.close();
        db.close();
    }

    public void simp() {
        ser();
       /* for (HashMap<String,String> user:userlist){
            names.add(user.get("name"));
            names.add(user.get("phone"));
        }*/
        SimpleAdapter simpleAdapter = new SimpleAdapter(
                this,
                userlist,
                R.layout.simple,
                new String[]{"name", "phone"},
                new int[]{R.id.tv_name, R.id.tv_tel}
        );
        sshow.setAdapter(simpleAdapter);
    }

    public void arr1() {
        ser();
        List names = new ArrayList<String>();
        if (userlist != null && userlist.size() > 0) {
            for (HashMap<String, String> user : userlist) {
                names.add(user.get("name"));
            }
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, R.layout.arrayada, names);
        sshow.setAdapter(arrayAdapter);
    }

    public void bbase() {
        Myadapter myadapter = new Myadapter();
        sshow.setAdapter(myadapter);
    }
    class Myadapter extends BaseAdapter {
        @Override
        public int getCount() {
            return sids.length;
        }
        @Override
        public Object getItem(int position) {
            return sids[position];
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = View.inflate(MainActivity.this, R.layout.listitv, null);
            TextView itemt = (TextView) v.findViewById(R.id.itextv);
            itemt.setText(sids[position]);
            ImageView itemimag = (ImageView) v.findViewById(R.id.itimage);
            itemimag.setBackgroundResource(backids[position]);
            return v;
        }
    }
}
