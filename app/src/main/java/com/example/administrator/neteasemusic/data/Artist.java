package com.example.administrator.neteasemusic.data;

import java.io.Serializable;

/**
 * 作者：bjc on 2017/11/23 15:13
 * 邮箱：783491064@qq.com
 * QQ ：783491064
 * 类描述：
 */
public class Artist implements Serializable {
    public final int albumCount;
    public final long id;
    public final String name;
    public final int songCount;

    public Artist() {
        this.id = -1;
        this.name = "";
        this.songCount = -1;
        this.albumCount = -1;
    }

    public Artist(long _id, String _name, int _albumCount, int _songCount) {
        this.id = _id;
        this.name = _name;
        this.songCount = _songCount;
        this.albumCount = _albumCount;
    }
}
