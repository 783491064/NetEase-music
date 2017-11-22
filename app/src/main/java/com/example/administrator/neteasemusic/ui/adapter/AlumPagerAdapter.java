package com.example.administrator.neteasemusic.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by bjc on 2017/11/8.
 *
 * 唱片 片段的适配器
 *
 */

public class AlumPagerAdapter extends FragmentPagerAdapter {
    private final String[] titles=new String[]{};

    public AlumPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
