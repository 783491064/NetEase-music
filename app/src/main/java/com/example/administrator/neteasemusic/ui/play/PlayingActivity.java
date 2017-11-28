package com.example.administrator.neteasemusic.ui.play;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.administrator.neteasemusic.R;
import com.example.administrator.neteasemusic.data.Song;
import com.example.administrator.neteasemusic.service.MusicPlayerManager;
import com.example.administrator.neteasemusic.ui.BaseActivity;
import com.example.administrator.neteasemusic.ui.widget.CustomViewPager;
import com.example.administrator.neteasemusic.ui.widget.PlayerSeekBar;
import com.example.administrator.neteasemusic.ui.widget.lrcview.LrvView;
import com.example.administrator.neteasemusic.utils.ImageUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class PlayingActivity extends BaseActivity {


    @InjectView(R.id.albumArt)
    ImageView albumArt;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.iv_cover)
    ImageView ivCover;
    @InjectView(R.id.view_pager)
    CustomViewPager viewPager;
    @InjectView(R.id.view_line)
    View viewLine;
    @InjectView(R.id.headerView)
    FrameLayout headerView;
    @InjectView(R.id.volume_seek)
    SeekBar volumeSeek;
    @InjectView(R.id.volume_layout)
    LinearLayout volumeLayout;
    @InjectView(R.id.lrc_small)
    LrvView lrcSmall;
    @InjectView(R.id.lrcviewContainer)
    RelativeLayout lrcviewContainer;
    @InjectView(R.id.playing_fav)
    ImageView playingFav;
    @InjectView(R.id.playing_down)
    ImageView playingDown;
    @InjectView(R.id.playing_cmt)
    ImageView playingCmt;
    @InjectView(R.id.playing_more)
    ImageView playingMore;
    @InjectView(R.id.music_tool)
    LinearLayout musicTool;
    @InjectView(R.id.music_duration_played)
    TextView musicDurationPlayed;
    @InjectView(R.id.play_seek)
    PlayerSeekBar playSeek;
    @InjectView(R.id.music_duration)
    TextView musicDuration;
    @InjectView(R.id.playing_mode)
    ImageView playingMode;
    @InjectView(R.id.playing_pre)
    ImageView playingPre;
    @InjectView(R.id.playing_play)
    ImageView playingPlay;
    @InjectView(R.id.playing_next)
    ImageView playingNext;
    @InjectView(R.id.playing_playlist)
    ImageView playingPlaylist;
    private Song song;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing);
        ButterKnife.inject(this);
        initData();
        updateData();
    }

    private void initData() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        song = MusicPlayerManager.get().getPlayingSong();

        //播放进度条的改变；
        playSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    MusicPlayerManager.get().seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    /**
     * 打开播放音乐界面的方法；
     *
     * @param context
     */
    public static void open(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, PlayingActivity.class);
        context.startActivity(intent);
    }

    @OnClick({R.id.playing_pre, R.id.playing_play, R.id.playing_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //播放上一首
            case R.id.playing_pre:
                MusicPlayerManager.get().playPre();
                break;
            //播放 暂停
            case R.id.playing_play:
                if (MusicPlayerManager.get().getState() == PlaybackStateCompat.STATE_PLAYING) {
                    MusicPlayerManager.get().pause();
                    playingPlay.setImageResource(R.drawable.play_rdi_btn_pause);
                } else if (MusicPlayerManager.get().getState() == PlaybackStateCompat.STATE_PAUSED) {
                    MusicPlayerManager.get().play();
                    playingPlay.setImageResource(R.drawable.play_rdi_btn_pause);
                }
                break;
            //播放下一首
            case R.id.playing_next:
                MusicPlayerManager.get().playNext();
                break;
        }
    }

    /**
     * 更新数据：封面，标题，图标；
     */
    private void updateData() {
        String coverUrl = song.getCoverUrl();
        ImageUtils.GlideWith(this, coverUrl, R.drawable.ah1, ivCover);

        //设置标题；
        if(!TextUtils.isEmpty(song.getAlbumName())){
            String title=song.getAlbumName();
            Spanned s = Html.fromHtml(title);
            getSupportActionBar().setTitle(s);
        }
        toolbar.setTitle(song.getTitle());
        if(MusicPlayerManager.get().getPlayingSong()!=null){
            playingPlay.setImageResource(R.drawable.play_rdi_btn_pause);
        }
    }
}
