package com.example.administrator.neteasemusic.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.administrator.neteasemusic.R;
import com.example.administrator.neteasemusic.data.Song;
import com.example.administrator.neteasemusic.service.MusicPlayerManager;
import com.example.administrator.neteasemusic.service.OnSongChangedListener;
import com.example.administrator.neteasemusic.ui.play.PlayingActivity;
import com.example.administrator.neteasemusic.utils.ImageUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 作者：bjc on 2017/11/30 10:13
 * 邮箱：783491064@qq.com
 * QQ ：783491064
 * 类描述：界面底部的播放音乐的控件
 */
public class BottomFragment extends BaseFragment implements OnSongChangedListener {


    @InjectView(R.id.playbar_img)
    ImageView playbarImg;
    @InjectView(R.id.playbar_info)
    TextView playbarInfo;
    @InjectView(R.id.playbar_singer)
    TextView playbarSinger;
    @InjectView(R.id.play_list)
    ImageView playList;
    @InjectView(R.id.control)
    ImageView control;
    @InjectView(R.id.play_next)
    ImageView playNext;
    @InjectView(R.id.linear)
    LinearLayout linear;
    @InjectView(R.id.song_progress_normal)
    SeekBar songProgressNormal;
    @InjectView(R.id.nav_play)
    LinearLayout navPlay;
    private View rootView;
    private Song song;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.bottom_nav, container, false);
        ButterKnife.inject(this, rootView);
        //音乐信息的更新的监听注册；
        MusicPlayerManager.get().registerListener(this);
        //点击底部播放的条目，进入播放界面；
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PlayingActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().startActivity(intent);
            }
        });
        return rootView;
    }

    public static BottomFragment newInstance() {
        return new BottomFragment();
    }

    @Override
    public void onSongChanged(Song song) {
        this.song = song;
        updataSong(song);
    }

    private void updataSong(Song song) {
        //歌曲的封面；
        String coverUrl = song.getCoverUrl();
        ImageUtils.GlideWith(getActivity(), coverUrl, R.drawable.ah1, playbarImg);
        //设置标题
        if (!TextUtils.isEmpty(song.getAlbumName())) {
            String title = song.getAlbumName();
            playbarInfo.setText(title);
        }

        //设置歌手
        if (!TextUtils.isEmpty(song.getArtistName())) {
            String title = song.getAlbumName();
            playbarSinger.setText(title);
        }

        if (MusicPlayerManager.get().getPlayingSong() != null) {
            control.setImageResource(R.drawable.playbar_btn_pause);
        }
    }

    @Override
    public void onPlayBackStateChanged(PlaybackStateCompat state) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
        MusicPlayerManager.get().unregisterListener(this);
    }

    @OnClick({R.id.play_list, R.id.control, R.id.play_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.play_list:
                //点击播放列表
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        PlayQueueFragment playQueueFragment = new PlayQueueFragment();
                        playQueueFragment.show(getFragmentManager(), "playqueuefragment");
                    }
                }, 60);
                break;
            case R.id.control:
                //点击暂停 播放按钮
                if (MusicPlayerManager.get().getState() == PlaybackStateCompat.STATE_PLAYING) {
                    MusicPlayerManager.get().pause();
                    control.setImageResource(R.drawable.playbar_btn_pause);

                } else if (MusicPlayerManager.get().getState() == PlaybackStateCompat.STATE_PAUSED) {
                    MusicPlayerManager.get().play();
                    control.setImageResource(R.drawable.playbar_btn_play);
                }
                break;
            case R.id.play_next:
                //播放下一个按钮
                MusicPlayerManager.get().playNext();
                break;
        }
    }
}
