package com.example.administrator.neteasemusic.ui.cnmusic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.neteasemusic.R;
import com.example.administrator.neteasemusic.ui.BaseActivity;
import com.example.administrator.neteasemusic.ui.adapter.LvLeftmenuAdapter;
import com.example.administrator.neteasemusic.ui.alum.AlbumFragmnet;
import com.example.administrator.neteasemusic.ui.friends.FriendsFragment;
import com.example.administrator.neteasemusic.ui.local.LocalFragment;
import com.example.administrator.neteasemusic.ui.widget.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 作者：bjc on 2017/11/22 12:30
 * 邮箱：783491064@qq.com
 * QQ ：783491064
 * 类描述：主界面；
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {

    @InjectView(R.id.bar_search)
    ImageView barSearch;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.main_viewpager)
    CustomViewPager mainViewpager;
    @InjectView(R.id.id_lv_left_menu)
    ListView idLvLeftMenu;
    @InjectView(R.id.activity_main)
    DrawerLayout drawerLayout;
    private ActionBar ab;
    private ArrayList<ImageView> tabs = new ArrayList<>();
    private long time;
    private ImageView barnet;
    private ImageView barmusic;
    private ImageView barfriends;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        getWindow().setBackgroundDrawableResource(R.color.background_material_light);
        initListener();
        setToolBar();
        setCustomViewPager();
        setUpDrawer();
        showQuickControl(true);
    }

    private void initListener() {
        barnet = (ImageView) findViewById(R.id.bar_net);
        barmusic = (ImageView) findViewById(R.id.bar_music);
        barfriends = (ImageView) findViewById(R.id.bar_friends);
        barnet.setOnClickListener(this);
        barmusic.setOnClickListener(this);
        barfriends.setOnClickListener(this);
    }

    /**
     * toolBar 初始化
     */
    private void setToolBar() {
        setSupportActionBar(toolbar);
        ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);//设置左侧的打开侧滑的按钮可以点击
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);//设置这个按钮背景图片；
    }

    /**
     * viewpager 初始化
     */
    private void setCustomViewPager() {
        //添加标签
        tabs.add(barnet);
        tabs.add(barmusic);
        tabs.add(barfriends);
        final AlbumFragmnet albumsFragment = new AlbumFragmnet();
        final LocalFragment musicsFragment = new LocalFragment();
        final FriendsFragment friendsFragment = new FriendsFragment();

        MainPagerAdapter mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        mainPagerAdapter.addFragment(albumsFragment);
        mainPagerAdapter.addFragment(musicsFragment);
        mainPagerAdapter.addFragment(friendsFragment);

        mainViewpager.setAdapter(mainPagerAdapter);
        mainViewpager.setOffscreenPageLimit(3);
        mainViewpager.setCurrentItem(1);
        barmusic.setSelected(true);
        mainViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                changeTabs(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 根据选中的当前页面，tab标签显示选中状态；
     *
     * @param position
     */
    private void changeTabs(int position) {
        for (int i = 0; i < tabs.size(); i++) {
            if (position == i) {
                tabs.get(i).setSelected(true);
            } else {
                tabs.get(i).setSelected(false);
            }
        }

    }

    /**
     * 侧滑菜单的初始化
     */
    private void setUpDrawer() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View header = inflater.inflate(R.layout.header_main, idLvLeftMenu, false);
        idLvLeftMenu.addHeaderView(header);
        idLvLeftMenu.setAdapter(new LvLeftmenuAdapter(this));
        idLvLeftMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 1:
                        drawerLayout.closeDrawers();
                        break;
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bar_net:
                mainViewpager.setCurrentItem(0);
                break;
            case R.id.bar_music:
                mainViewpager.setCurrentItem(1);
                break;
            case R.id.bar_friends:
                mainViewpager.setCurrentItem(2);
                break;
        }
    }

    public class MainPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();

        public MainPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        public void addFragment(Fragment fragment) {
            mFragments.add(fragment);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(Gravity.LEFT);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    /**
     * 双击返回键 退出应用到主页面；
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - time > 1000) {
                Toast.makeText(this, "再按一次返回桌面", Toast.LENGTH_SHORT).show();
                time = System.currentTimeMillis();
            } else {
                Intent intent = new Intent();
                intent.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent);
            }
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }

    }
}