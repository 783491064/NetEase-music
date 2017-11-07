package com.example.administrator.neteasemusic.ui;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.neteasemusic.R;

import butterknife.ButterKnife;

/**
 * Created by bijingcun
 * on 2017/10/29.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //所有的界面保证为竖屏界面，不可变为横屏；
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }

    /**
     * 打开新activity的方法
     */
    protected void startActivity(Class activity) {
        Intent intent = new Intent();
        intent.setClass(this,activity);
        startActivity(intent);
    }
}
