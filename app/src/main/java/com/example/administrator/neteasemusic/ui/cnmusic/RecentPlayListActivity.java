package com.example.administrator.neteasemusic.ui.cnmusic;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.neteasemusic.R;
import com.example.administrator.neteasemusic.data.Song;
import com.example.administrator.neteasemusic.music.MusicPlaylist;
import com.example.administrator.neteasemusic.music.MusicRecentPlaylist;
import com.example.administrator.neteasemusic.service.MusicPlayerManager;
import com.example.administrator.neteasemusic.service.OnSongChangedListener;
import com.example.administrator.neteasemusic.ui.BaseActivity;
import com.example.administrator.neteasemusic.ui.adapter.OnItemClickListener;
import com.example.administrator.neteasemusic.ui.adapter.RecentPlayAdapter;
import com.example.administrator.neteasemusic.ui.play.PlayingActivity;
import com.lb.materialdesigndialog.base.DialogBase;
import com.lb.materialdesigndialog.base.DialogWithTitle;
import com.lb.materialdesigndialog.impl.MaterialDialogNormal;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class RecentPlayListActivity extends AppCompatActivity implements OnSongChangedListener {

    @InjectView(R.id.btnRight)
    Button btnRight;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.recycler_view)
    RecyclerView recyclerView;
    private ActionBar ab;
    private MusicPlaylist musicPlaylist;
    private RecentPlayAdapter recentPlayAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_play_list);
        ButterKnife.inject(this);
        setToolBar();
        MusicPlayerManager.get().registerListener(this);
        initRecyclerView();

    }
    //初始化ToolBar
    private void setToolBar() {
        setSupportActionBar(toolbar);
        ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeAsUpIndicator(R.drawable.actionbar_back);
        ab.setTitle("最近播放");
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        btnRight.setText("清空");

        recentPlayAdapter = new RecentPlayAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recentPlayAdapter);
        ArrayList<Song> queue = MusicRecentPlaylist.getInstance().getQueue();
        recentPlayAdapter.setData(queue);
        musicPlaylist = new MusicPlaylist(MusicRecentPlaylist.getInstance().getQueue());
        recentPlayAdapter.setOnItemClickListener(new OnItemClickListener<Song>() {
            @Override
            public void onItemClick(Song song, int position) {
                MusicPlayerManager.get().playQueue(musicPlaylist, position);
                gotoSongPlayerActivity();
            }

            @Override
            public void onItemSettingClick(View view, Song song, int position) {
                showPopupMenu(view, song, position);
            }
        });

    }

    /**
     * 最近播放列表条目右侧的设置按钮的点击事件；
     * @param view
     * @param song
     * @param position
     */
    private void showPopupMenu(View view, final Song song, final int position) {
        final PopupMenu menu =new PopupMenu(this,view);
        menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.popup_song_play://播放
                        MusicPlayerManager.get().playQueue(musicPlaylist,position);
                        gotoSongPlayerActivity();
                        break;
                    case R.id.popup_song_addto_playlist://添加到播放列表
                        MusicPlaylist mp = MusicPlayerManager.get().getMusicPlaylist();
                        if (mp == null) {
                            mp = new MusicPlaylist();
                            MusicPlayerManager.get().setMusicPlaylist(mp);
                        }
                        mp.addSong(song);
                        break;
                    case R.id.popup_song_fav://添加到收藏列表
                        break;
                    case R.id.popup_song_download://下载歌曲；
                        break;
                }
                return false;
            }
        });
        menu.inflate(R.menu.popup_recently_playlist_setting);
        menu.show();
    }

    @Override
    public void onSongChanged(Song song) {

    }

    @Override
    public void onPlayBackStateChanged(PlaybackStateCompat state) {

    }

    /**
     * 跳转到音乐播放界面
     *
     * @return
     */
    public boolean gotoSongPlayerActivity() {
        if (MusicPlayerManager.get().getPlayingSong() == null) {
            Toast.makeText(RecentPlayListActivity.this, R.string.music_playing_none, Toast.LENGTH_SHORT).show();
            return false;
        }
        PlayingActivity.open(this);
        return true;
    }

    @OnClick(R.id.btnRight)
    public void onViewClicked() {
        MaterialDialogNormal d=new MaterialDialogNormal(this);
        d.setTitle("");
        d.setMessage("清空全部所有最近播放记录？");
        d.setNegativeButton("清除", new DialogWithTitle.OnClickListener() {
            @Override
            public void click(DialogBase dialog, View view) {
                MusicRecentPlaylist.getInstance().clearRecentPlaylist();
                recentPlayAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        d.setPositiveButton("取消", new DialogWithTitle.OnClickListener() {
            @Override
            public void click(DialogBase dialog, View view) {
                dialog.dismiss();
            }
        });
    }

    /**
     * 打开最近播放的界面；
     * @param context
     */
    public static void open(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, RecentPlayListActivity.class);
        context.startActivity(intent);
    }
}
