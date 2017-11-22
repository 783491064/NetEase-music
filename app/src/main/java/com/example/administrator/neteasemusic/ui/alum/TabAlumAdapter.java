package com.example.administrator.neteasemusic.ui.alum;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * 作者：bjc on 2017/11/22 12:30
 * 邮箱：783491064@qq.com
 * QQ ：783491064
 * 类描述：唱片片段ViewPager适配器；
 */
public class TabAlumAdapter extends FragmentPagerAdapter {
    private String[] tabs = new String[]{"歌单", "MV", "排行榜"};
    private NewAlumFragment newAlumFragment;
    private MVFragment mvFragment;
    private RankingFragment rankingFragment;

    public TabAlumAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0://新唱片
                if (newAlumFragment == null) {
                    newAlumFragment = NewAlumFragment.newInstance();
                }
                return newAlumFragment;
            case 1://MV
                if (mvFragment == null) {
                    mvFragment = MVFragment.newInstance();
                }
                return mvFragment;
            case 2://排名榜
                if (rankingFragment == null) {
                    rankingFragment = RankingFragment.newInstance();
                }
                return rankingFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabs.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }
}
