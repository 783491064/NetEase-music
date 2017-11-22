package com.example.administrator.neteasemusic.ui.cnmusic;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.administrator.neteasemusic.R;
import com.example.administrator.neteasemusic.ui.BaseActivity;
import com.example.administrator.neteasemusic.ui.local.LocalMusicAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 作者：bjc on 2017/11/22 12:30
 * 邮箱：783491064@qq.com
 * QQ ：783491064
 * 类描述：本地音乐；
 */
public class LocalMusicActivity extends BaseActivity {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.tab_layout)
    TabLayout tabLayout;
    @InjectView(R.id.viewpager)
    ViewPager viewpager;
    @InjectView(R.id.bottom_container)
    FrameLayout bottomContainer;
    private String TAG = "LocalMusicActivity";
    private ActionBar supportActionBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_music);
        ButterKnife.inject(this);
        setToobar();
        LocalMusicAdapter localMusicAdapter = new LocalMusicAdapter(getSupportFragmentManager());
        viewpager.setAdapter(localMusicAdapter);
        viewpager.setOffscreenPageLimit(localMusicAdapter.getCount());
        viewpager.setCurrentItem(0);
        tabLayout.setupWithViewPager(viewpager);
    }

    /**
     * 设置Toolbar
     */
    private void setToobar() {
        setSupportActionBar(toolbar);
        supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setHomeAsUpIndicator(R.drawable.actionbar_back);
        supportActionBar.setTitle("本地音乐");
    }


    /**
     * 打开本地音乐界面的方法；
     *
     * @param context
     */
    public static void open(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, LocalMusicActivity.class);
        context.startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
