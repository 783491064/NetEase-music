package com.example.administrator.neteasemusic.music;

import com.example.administrator.neteasemusic.MusicApplication;
import com.example.administrator.neteasemusic.data.Song;
import com.example.administrator.neteasemusic.utils.ACache;

import java.util.ArrayList;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * 作者：bjc on 2017/11/29 10:42
 * 邮箱：783491064@qq.com
 * QQ ：783491064
 * 类描述：最近播放歌曲列表的列表
 */
public class MusicRecentPlaylist {
    private static final String TAG = MusicRecentPlaylist.class.getSimpleName();
    private static final String PLAY_QUEUE = "song_queue";
    private static final int RECENT_COUNT = 10;
    private ArrayList<Song> recentQueue;

    private MusicRecentPlaylist() {
        recentQueue = readQueueFromFileCache();

    }

    private static MusicRecentPlaylist instance;

    public static MusicRecentPlaylist getInstance() {
        if (instance == null) {
            instance = new MusicRecentPlaylist();
        }
        return instance;
    }

    /**
     * 添加歌曲到最近播放列表；
     */
    public void addRecentSong(Song song) {
        recentQueue.add(0, song);
        for (int i = recentQueue.size() - 1; i >0; i--) {
            //不能超过最大歌曲数量
            if (recentQueue.size() >= RECENT_COUNT) {
                recentQueue.remove(i);
                continue;
            }
            //1去除重复歌曲
            if (song.getId() == recentQueue.get(i).getId()) {
                recentQueue.remove(i);
                break;
            }
            //将该歌曲添加到缓存的文件中；
            Observable.create(new Observable.OnSubscribe<Object>() {
                @Override
                public void call(Subscriber<? super Object> subscriber) {
                    addQueueToFileCache();
                }
            }).subscribeOn(Schedulers.io()).subscribe();
        }

    }

    /**
     * 将列表缓存到文件中，以便下次使用的时候直接读取；
     */
    private void addQueueToFileCache() {
        ACache.get(MusicApplication.getInstance(), PLAY_QUEUE).put(PLAY_QUEUE, recentQueue);
    }

    /**
     * 返回最近播放的列表的歌曲；
     *
     * @return
     */
    public ArrayList<Song> getQueue() {
        return recentQueue;
    }

    /**
     * 从文件缓存中读取上一次的播放列表
     */
    private ArrayList<Song> readQueueFromFileCache() {
        Object serializable=ACache.get(MusicApplication.getInstance(),PLAY_QUEUE).getAsObject(PLAY_QUEUE);
        if(serializable!=null&&serializable instanceof ArrayList){
            return (ArrayList<Song>) serializable;
        }
        return new ArrayList<>();
    }

    /**
     * 清楚最近播放的列表数据；
     */
    public void clearRecentPlaylist() {
        recentQueue.clear();
    }
}