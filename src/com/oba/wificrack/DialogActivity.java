// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.oba.wificrack;


import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.Toast;

import com.android.mywifi02.parser.R;

public class DialogActivity extends Activity
{

    public DialogActivity()
    {
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.activity_dialog);
//        PointsManager.getInstance(this).spendPoints(PointsManager.getInstance(this).queryPoints());
        name = (TextView)findViewById(0x7f060005);
        pass = (TextView)findViewById(0x7f060006);
//        name.setText((new StringBuilder("\u7528\u6237\u540D\uFF1A")).append(getIntent().getExtras().getString("name")).toString());
//        pass.setText((new StringBuilder("\u5BC6\u7801\uFF1A")).append(getIntent().getExtras().getString("pass")).toString());
        name.setText(this.getString(R.string.crack_ok));
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        if(System.currentTimeMillis() - lastBackTime < 2000L)
        {
            finish();
        } else
        {
            lastBackTime = System.currentTimeMillis();
            Toast.makeText(this, "\u518D\u70B9\u4E00\u6B21\u8FD4\u56DE\u952E\u9000\u51FA!", 0).show();
        }
        return true;
    }

    private long lastBackTime;
    private TextView name;
    private TextView pass;
}
