package com.example.administrator.neteasemusic;

import android.app.Application;

/**
 * Created by bijingcuun
 * on 2017/10/29.
 */
public class MusicApplication extends Application {
    private static MusicApplication ourInstance = new MusicApplication();

    public static MusicApplication getInstance() {
        return ourInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
