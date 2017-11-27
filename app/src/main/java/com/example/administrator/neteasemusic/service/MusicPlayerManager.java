package com.example.administrator.neteasemusic.service;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.PowerManager;
import android.util.Log;

import com.example.administrator.neteasemusic.data.Song;
import com.example.administrator.neteasemusic.music.MusicPlaylist;

import java.io.IOException;
import java.util.IllegalFormatException;

import static android.support.v4.media.session.PlaybackStateCompat.STATE_PAUSED;
import static android.support.v4.media.session.PlaybackStateCompat.STATE_PLAYING;

/**
 * 作者：bjc on 2017/11/27 11:33
 * 邮箱：783491064@qq.com
 * QQ ：783491064
 * 类描述：播放音乐的管理类；
 */
public class MusicPlayerManager implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnSeekCompleteListener {
    private static String TAG = MusicPlayerManager.class.getSimpleName();
    private final static MusicPlayerManager instance = new MusicPlayerManager();
    private MusicService context;
    private MusicService service;
    private MediaPlayer mediaPlayer;
    private long currentMediaId = -1;
    private int currentProgress;
    /**
     * set the play mode
     */
    public final static int SINGLETYPE = 0;//单曲循环
    public final static int CYCLETYPE = 1;//列表循环
    public final static int RANDOMTYPE = 2;//随机播放
    private int currentPlayType = CYCLETYPE;
    private MusicPlaylist musicPlaylist;
    private int currentMaxDuration;

    /**
     * 单例实现；
     *
     * @return
     */
    public static MusicPlayerManager get() {
        return instance;
    }

    /**
     * 设置Context 和service;
     *
     * @param service
     * @return
     */
    public static MusicPlayerManager from(MusicService service) {
        return MusicPlayerManager.get().setContext(service).setService(service);
    }

    /**
     * 开启服务的方法；
     *
     * @param context
     */
    public static void startServiceIfNecessary(Context context) {
        if (get().service == null) {
            MusicServiceHelper.get(context).initService();
            get().service = MusicServiceHelper.get(context).getService();
        }

    }

    public MusicPlayerManager setContext(MusicService context) {
        this.context = context;
        return this;
    }

    public MusicPlayerManager setService(MusicService service) {
        this.service = service;
        return this;
    }

    /**
     * 创建一个新的播放列表
     */
    public void playQueue(MusicPlaylist musicPlayList, int position) {
        this.musicPlaylist = musicPlayList;
        musicPlayList.setCurrentPlay(position);
        play(musicPlayList.getCurrentPlay());
    }

    private void play(Song song) {
        if (song == null) return;
        boolean mediaHasChanged = !(song.getId() == currentMediaId);
        if (mediaHasChanged) {
            currentProgress = 0;
            currentMediaId = song.getId();
        }
        if (service.getState() == STATE_PAUSED && !mediaHasChanged && mediaPlayer != null) {
            configMediaPlayerState();
        } else {
            try {
                createMediaPlayerIfNeeded();
                service.setState(STATE_PLAYING);
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setDataSource(song.getPath());
                mediaPlayer.prepareAsync();
            } catch (IOException e) {
                Log.e(TAG, "PLAYING SONG:", e);
            }
        }

    }

    /**
     * 重新配置媒体播放器根据音频集中设置和启动/重新启动它。
     * 此方法启动/重新启动媒体播放器尊重当前音频焦点状态。
     * 如果我们有焦点,它将发挥正常,如果我们没有焦点,
     * 它将离开媒体播放器暂停或者将其设置为较低的体积,根据当前的焦点所允许的设置。
     * 该方法假设媒体播放器!=零,所以如果你调用它,你必须这样做。
     */
    private void configMediaPlayerState() {
        Log.e(TAG, "configMediaPlayerState:");
        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
            Log.d(TAG, "configMediaPlayerState startMediaPlayer. seeking to " + currentProgress);
            if(currentProgress==mediaPlayer.getCurrentPosition()){
                mediaPlayer.start();
                service.setState(STATE_PLAYING);
            }else{
                mediaPlayer.seekTo(currentProgress);
                mediaPlayer.start();
                service.setState(STATE_PLAYING);
            }

        }

    }

    private void createMediaPlayerIfNeeded() {
        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
//            播放过程不能黑屏锁住
            mediaPlayer.setWakeMode(context, PowerManager.PARTIAL_WAKE_LOCK);
            mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.setOnCompletionListener(this);
            mediaPlayer.setOnErrorListener(this);
            mediaPlayer.setOnSeekCompleteListener(this);
        } else {
            mediaPlayer.reset();
        }

    }

    /**
     * 设置播放的模式
     */
    public void setPlayMode(int type) {
        if (type < 0 || type > 2)
            throw new IllegalArgumentException("incorrect type");
        currentPlayType = type;
        if (type == SINGLETYPE) {

        } else {

        }
    }

    /**
     * 获取当前的额播放模式
     * 单曲循环0，全循环1（默认），随机播放2；
     */
    public int getPlayMode() {
        return currentPlayType;
    }

    /**
     * 切换播放的模式
     */
    public int switchPlayMode() {
        if (currentPlayType == CYCLETYPE) {
            setPlayMode(SINGLETYPE);
        } else if (currentPlayType == SINGLETYPE) {
            setPlayMode(RANDOMTYPE);
        } else if (currentPlayType == RANDOMTYPE) {
            setPlayMode(CYCLETYPE);
        }
        return currentPlayType;

    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        currentMaxDuration = mediaPlayer.getDuration();
        configMediaPlayerState();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {

    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public void onSeekComplete(MediaPlayer mp) {

    }
}
