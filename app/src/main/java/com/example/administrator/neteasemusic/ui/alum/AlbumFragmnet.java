package com.example.administrator.neteasemusic.ui.alum;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.neteasemusic.R;
import com.example.administrator.neteasemusic.ui.adapter.AlumPagerAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlbumFragmnet extends Fragment {


    @InjectView(R.id.tab_layout)
    TabLayout tabLayout;
    @InjectView(R.id.local_vp)
    ViewPager local_viewpager;

    public AlbumFragmnet() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_album_fragmnet, container, false);
        initViewpager();
        ButterKnife.inject(this, view);
        return view;
    }

    private void initViewpager() {
        AlumPagerAdapter alumPagerAdapter = new AlumPagerAdapter(getFragmentManager());
//        local_viewpager.setAdapter(alumPagerAdapter);
//        local_viewpager.setCurrentItem(0);
//        local_viewpager.setOffscreenPageLimit(alumPagerAdapter.getCount());
//        tabLayout.setupWithViewPager(local_viewpager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
