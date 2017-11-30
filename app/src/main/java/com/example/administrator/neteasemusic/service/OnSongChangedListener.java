package com.example.administrator.neteasemusic.service;

import android.support.v4.media.session.PlaybackStateCompat;

import com.example.administrator.neteasemusic.data.Song;

/**
 * 作者：bjc on 2017/11/28 17:27
 * 邮箱：783491064@qq.com
 * QQ ：783491064
 * 类描述：切换歌曲时的界面切换接口
 */
public interface OnSongChangedListener {
    void onSongChanged(Song song);

    void onPlayBackStateChanged(PlaybackStateCompat state);
}
