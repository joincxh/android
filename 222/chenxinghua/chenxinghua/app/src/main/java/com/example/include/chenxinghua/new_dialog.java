package com.example.include.chenxinghua;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by include on 2021/9/20.
 */
public class new_dialog extends Dialog {
    private String dialogName;
    private TextView Msgname;/*用户名*/
    private TextView Msgpassword;/*密码*/
    private Button btnOK;
    private Button btnCancle;
    Dialog dialog;
    public new_dialog(Context context,String dialogName){
        super(context);
        this.dialogName=dialogName;
    }
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);/*去除标题*/
        setContentView(R.layout.new_dialog);
        Msgname=(TextView)findViewById(R.id.rootname);
        Msgpassword=(TextView)findViewById(R.id.rootpassword);
        btnOK=(Button)findViewById(R.id.logonbtn);
        btnCancle=(Button)findViewById(R.id.canclebtn);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnCancle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
               dismiss();
            }
        });
    }
}
