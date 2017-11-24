package com.example.administrator.neteasemusic.modle;

import com.example.administrator.neteasemusic.data.Album;
import com.example.administrator.neteasemusic.data.Artist;
import com.example.administrator.neteasemusic.data.Song;

import java.util.List;

/**
 * 作者：bjc on 2017/11/23 15:06
 * 邮箱：783491064@qq.com
 * QQ ：783491064
 * 类描述：
 */
public interface LocalView {
    //获取本地音乐
    interface LocalMusic {
        void getLocalMusicSuccess(List<Song> songs);

        void getLocalMusicFail(Throwable throwable);
    }

    //获取本地歌手
    interface LocalAlbum {
        void getLocalAlbumSuccess(List<Album> albs);

        void getLocalAlbumFail(Throwable throwable);
    }

    //获取本地专辑
    interface LocalArtist {
        void getLocalArtistSuccess(List<Artist> artists);

        void getLocalArtistFail(Throwable throwable);
    }

}
