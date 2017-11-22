package com.example.administrator.neteasemusic.ui.local;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.administrator.neteasemusic.ui.cnmusic.LocalMusicActivity;

/**
 * 作者：bjc on 2017/11/22 15:37
 * 邮箱：783491064@qq.com
 * QQ ：783491064
 * 类描述:本地音乐界面的ViewPager适配；
 */

public class LocalMusicAdapter extends FragmentPagerAdapter {
    private String TAG = "LocalMusicActivity";
    private String[] tabs = new String[]{"单曲", "歌手", "专辑"};
    private LocalMusicFragment localMusicFragment;
    private ArtistFragment artistFragment;
    private LocalAlbumFragmnet localAlbumFragmnet;

    public LocalMusicAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                if (localMusicFragment == null) {
                    localMusicFragment = LocalMusicFragment.newInstance();
                }
                return localMusicFragment;
            case 1:
                if (artistFragment == null) {
                    artistFragment = ArtistFragment.newInstance();
                }
                return artistFragment;
            case 2:
                if (localAlbumFragmnet == null) {
                    localAlbumFragmnet = LocalAlbumFragmnet.newInstance();
                }
                return localAlbumFragmnet;
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
