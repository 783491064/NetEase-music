package com.example.administrator.neteasemusic.ui.play;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.administrator.neteasemusic.R;
import com.example.administrator.neteasemusic.ui.widget.CustomViewPager;
import com.example.administrator.neteasemusic.ui.widget.PlayerSeekBar;
import com.example.administrator.neteasemusic.ui.widget.lrcview.LrvView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class PlayingActivity extends AppCompatActivity {

    @InjectView(R.id.albumArt)
    ImageView albumArt;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing);
        ButterKnife.inject(this);

    }

    public static void open(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, PlayingActivity.class);
        context.startActivity(intent);
    }
}
