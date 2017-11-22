package com.example.administrator.neteasemusic.ui.alum;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.neteasemusic.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 作者：bjc on 2017/11/22 12:30
 * 邮箱：783491064@qq.com
 * QQ ：783491064
 * 类描述：唱片片段（包括新歌，MV,排行榜）
 */
public class AlbumFragmnet extends Fragment {
    @InjectView(R.id.tab_layout)
    TabLayout tabLayout;
    @InjectView(R.id.local_vp)
    ViewPager local_viewpager;

    public AlbumFragmnet() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_album_fragmnet, container, false);
        ButterKnife.inject(this, view);
        initViewpager();
        return view;
    }

    private void initViewpager() {
        TabAlumAdapter tabAlumAdapter = new TabAlumAdapter(getFragmentManager());
        local_viewpager.setAdapter(tabAlumAdapter);
        local_viewpager.setCurrentItem(0);
        local_viewpager.setOffscreenPageLimit(tabAlumAdapter.getCount());
        tabLayout.setupWithViewPager(local_viewpager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
