package com.example.administrator.neteasemusic.ui.guide;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.administrator.neteasemusic.R;
import com.example.administrator.neteasemusic.ui.music.BaseActivity;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class GuideActivity extends BaseActivity {
    @InjectView(R.id.viewpager)
    ViewPager viewpager;
    @InjectView(R.id.iv_dao_1)
    ImageView ivDao1;
    @InjectView(R.id.iv_dao_2)
    ImageView ivDao2;
    @InjectView(R.id.iv_dao_3)
    ImageView ivDao3;
    @InjectView(R.id.bt_start)
    Button btStart;
    @InjectView(R.id.activity_guide)
    RelativeLayout activityGuide;
    private ArrayList<Fragment> fragments;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.inject(this);
        initData();

    }

    /**
     * 加载数据
     */
    private void initData() {
        fragments = new ArrayList<>();

        /**
         * 播放的三个界面；
         */
        GuidFragment guidFragment1 = new GuidFragment();
        Bundle bundle1 = new Bundle();
        bundle1.putInt("Index", 1);
        guidFragment1.setArguments(bundle1);
        fragments.add(guidFragment1);

        GuidFragment guidFragment2 = new GuidFragment();
        Bundle bundle2 = new Bundle();
        bundle1.putInt("Index", 2);
        guidFragment1.setArguments(bundle2);
        fragments.add(guidFragment2);

        GuidFragment guidFragment3 = new GuidFragment();
        Bundle bundle3 = new Bundle();
        bundle1.putInt("Index", 3);
        guidFragment1.setArguments(bundle3);
        fragments.add(guidFragment3);
        initViewPager();

    }

    /**
     * ViePager的加载；
     */
    private void initViewPager() {
        viewpager.setOffscreenPageLimit(3);//设置缓存3页
        viewpager.setAdapter(new MyPagerAdapter(this.getSupportFragmentManager()));
        viewpager.addOnPageChangeListener(new MyPagerListener());
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

    protected class MyPagerListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            btStart.setVisibility(View.GONE);
            ivDao1.setBackgroundResource(R.mipmap.dot_normal);
            ivDao2.setBackgroundResource(R.mipmap.dot_normal);
            ivDao3.setBackgroundResource(R.mipmap.dot_normal);

            if (position == 0) {
                ivDao1.setBackgroundResource(R.mipmap.dot_focus);
            } else if (position == 1) {
                ivDao2.setBackgroundResource(R.mipmap.dot_focus);
            } else {
                ivDao3.setBackgroundResource(R.mipmap.dot_focus);
                btStart.setVisibility(View.VISIBLE);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }


}
