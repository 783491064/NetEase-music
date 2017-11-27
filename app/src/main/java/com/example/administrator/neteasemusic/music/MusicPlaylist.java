package com.example.administrator.neteasemusic.music;

import com.example.administrator.neteasemusic.data.Song;
import com.example.administrator.neteasemusic.service.MusicPlayerManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 作者：bjc on 2017/11/27 15:06
 * 邮箱：783491064@qq.com
 * QQ ：783491064
 * 类描述：对歌曲的存放，查找
 */
public class MusicPlaylist {
    private List<Song> queue;
    private int currentPlay;
    private Song curSong;
    private String title;
    private long albumID;

    /**
     * 空参构造 初始化一个集合列表；
     */
    public MusicPlaylist(){
        queue = new ArrayList<>();
    }

    /**
     * 有参构造就直接赋值；
     * @param queue
     */
    public MusicPlaylist(List<Song> queue) {
        setQueue(queue);
    }

    /***
     * 设置队列
     * @param queue
     */
    public void setQueue(List<Song> queue) {
        this.queue = queue;
        setCurrentPlay(0);
    }

    /**
     * 设置当前播放的歌曲；
     *
     * @param position
     * @return
     */
    public long setCurrentPlay(int position) {
        if (queue.size() > position && position >= 0) {
            curSong = queue.get(position);
            return curSong.getId();
        }
        return -1;
    }

    /**
     * 获取正在播放的歌曲；
     *
     * @return
     */
    public Song getCurrentPlay() {
        if (curSong == null) {
            setCurrentPlay(0);
        }
        return curSong;
    }

    public List<Song> getQueue() {
        return queue;
    }

    /**
     * 添加歌曲；
     *
     * @param song
     */
    public void addSong(Song song) {
        queue.add(song);
    }

    public void addQueue(List<Song> songs, boolean head) {
        if (!head) {
            queue.addAll(songs);
        } else {
            queue.addAll(0, songs);
        }
    }

    /**
     * 带位置的添加歌曲；
     *
     * @param song
     * @param position
     */
    public void addSong(Song song, int position) {
        queue.add(position, song);
    }

    /**
     * 播放列表的标题
     *
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置播放列表的标题；
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取唱片的ID
     *
     * @return
     */
    public long getAlbumID() {
        return albumID;
    }

    /**
     * 设置唱片的ID
     *
     * @param albumID
     */
    public void setAlbumID(long albumID) {
        this.albumID = albumID;
    }

    /**
     * 获取对列的上一首歌曲
     */
    public Song getPreSong() {
        int currentPos = queue.indexOf(curSong);//歌曲当前位置
        int mode = MusicPlayerManager.get().getPlayMode();//歌曲的当前播放模式；
        if (mode == MusicPlayerManager.SINGLETYPE || mode == MusicPlayerManager.CYCLETYPE) {
            if (--currentPos < 0) {
                currentPos = 0;
            }
        } else {
            currentPos = new Random().nextInt(queue.size());
        }
        curSong = queue.get(currentPos);
        return curSong;
    }

    /**
     * 获取队列的下一首歌曲
     */
    public Song getNextSong(){
        int currentPos = queue.indexOf(curSong);//歌曲当前位置
        int mode = MusicPlayerManager.get().getPlayMode();//歌曲的当前播放模式；
        if (mode == MusicPlayerManager.SINGLETYPE || mode == MusicPlayerManager.CYCLETYPE) {
            if (++currentPos >queue.size()-1) {
                currentPos = 0;
            }
        } else {
            currentPos = new Random().nextInt(queue.size());
        }
        curSong = queue.get(currentPos);
        return curSong;
    }

    /**
     * 清楚列表和当前的歌曲
     */
    public void clear(){
        queue.clear();
        curSong=null;
    }


}
