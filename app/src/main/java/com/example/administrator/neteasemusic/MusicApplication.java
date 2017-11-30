package com.example.administrator.neteasemusic;

import android.app.Application;

/**
 * 作者：bjc on 2017/11/22 12:30
 * 邮箱：783491064@qq.com
 * QQ ：783491064
 * 类描述：应用全局类
 */
public class MusicApplication extends Application {
    private static MusicApplication ourInstance;

    public static MusicApplication getInstance() {
        return ourInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ourInstance = this;
    }
}
