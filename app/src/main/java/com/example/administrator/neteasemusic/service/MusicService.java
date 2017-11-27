package com.example.administrator.neteasemusic.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v4.media.session.MediaButtonReceiver;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;

/**
 * 作者：bjc on 2017/11/27 11:26
 * 邮箱：783491064@qq.com
 * QQ ：783491064
 * 类描述：
 */
public class MusicService extends Service {
    public final Binder mBinder = new MyBinder();
    private MusicPlayerManager playerManager;
    private MediaSessionCompat mediaSession;
    private PlaybackStateCompat mState;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public class MyBinder extends Binder {
        public MusicService getMusicService() {
            return MusicService.this;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //服务的入口
        MediaButtonReceiver.handleIntent(mediaSession, intent);
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 初始化
     */
    public void setUp() {
        playerManager = MusicPlayerManager.from(this);
        setupMddiaSession();
    }

    /**
     * 线控
     * 使用 {@link MediaButtonReceiver} 来兼容 api21 之前的版本
     * 使用{@link MediaSessionCompat#setCallback}控制 api21 之后的版本
     */
    private void setupMddiaSession() {
        ComponentName componentName = new ComponentName(getPackageName(), MediaButtonReceiver.class.getName());
        mediaSession = new MediaSessionCompat(this, "fd", componentName, null);
        //处理media button 的FLAG
        mediaSession.setFlags(MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS | MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS);
        //设置回调
        mediaSession.setCallback(new MidiaSessionCallback());
        setState(PlaybackStateCompat.STATE_NONE);
    }

    /**
     * 设置播放状态；
     *
     * @param state
     */
    public void setState(int state) {
        mState = new PlaybackStateCompat.Builder()
                .setActions(
                        PlaybackStateCompat.ACTION_PLAY |
                                PlaybackStateCompat.ACTION_PLAY_PAUSE |
                                PlaybackStateCompat.ACTION_PAUSE |
                                PlaybackStateCompat.ACTION_SKIP_TO_NEXT |
                                PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS |
                                PlaybackStateCompat.ACTION_STOP |
                                PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID |
                                PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH |
                                PlaybackStateCompat.ACTION_SEEK_TO |
                                PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM
                )
                .setState(state, PlaybackStateCompat.PLAYBACK_POSITION_UNKNOWN, 1.0f, SystemClock.elapsedRealtime()).build();
        mediaSession.setPlaybackState(mState);
        mediaSession.setActive(state!=PlaybackStateCompat.STATE_NONE&&state!=PlaybackStateCompat.STATE_STOPPED);
    }

    /**
     * 播放的回调方法；
     */
    public class MidiaSessionCallback extends MediaSessionCompat.Callback {
        @Override
        public void onPlay() {
            super.onPlay();
        }

        @Override
        public void onPause() {
            super.onPause();
        }

        @Override
        public void onSkipToNext() {
            super.onSkipToNext();
        }

        @Override
        public void onSkipToPrevious() {
            super.onSkipToPrevious();
        }

        @Override
        public void onStop() {
            super.onStop();
        }

        @Override
        public void onSeekTo(long pos) {
            super.onSeekTo(pos);
        }
    }
    public int getState(){
        return mState.getState();
    }
    /**
     * 停止服务
     */
    public void stopService(){
        stopSelf();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaSession.release();
    }
}
