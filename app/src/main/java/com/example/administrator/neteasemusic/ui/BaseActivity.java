package com.example.administrator.neteasemusic.ui;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.neteasemusic.R;
import com.example.administrator.neteasemusic.service.MusicPlayerManager;

import butterknife.ButterKnife;

/**
 * 作者：bjc on 2017/11/22 12:30
 * 邮箱：783491064@qq.com
 * QQ ：783491064
 * 类描述：activity基类
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //所有的界面保证为竖屏界面，不可变为横屏；
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //启动音乐播放的服务
        MusicPlayerManager.startServiceIfNecessary(getApplicationContext());
    }

    /**
     * 打开新activity的方法
     */
    protected void startActivity(Class activity) {
        Intent intent = new Intent();
        intent.setClass(this, activity);
        startActivity(intent);
    }
    private BottomFragment fragment; //底部播放控制栏
    /**
     * @param show 显示或关闭底部播放控制栏
     */
    public void showQuickControl(boolean show){
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        if(show){
            if(fragment==null){
                fragment=BottomFragment.newInstance();
                ft.add(R.id.bottom_container,fragment).commitAllowingStateLoss();
            }else{
                ft.show(fragment).commitAllowingStateLoss();
            }
        }else{
            if(fragment!=null)
                ft.hide(fragment).commitAllowingStateLoss();
        }
    }
}
