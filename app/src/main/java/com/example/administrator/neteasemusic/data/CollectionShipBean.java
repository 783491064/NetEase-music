package com.example.administrator.neteasemusic.data;

/**
 * 作者：bjc on 2017/12/4 10:25
 * 邮箱：783491064@qq.com
 * QQ ：783491064
 * 类描述：收藏夹实体
 */
public class CollectionShipBean {
    private int id;
    private int cid;//收藏夹ID
    private int sid;//歌曲ID

    public CollectionShipBean(int id,int cid,int sid){
        this.id=id;
        this.cid=cid;
        this.sid=sid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }
}
