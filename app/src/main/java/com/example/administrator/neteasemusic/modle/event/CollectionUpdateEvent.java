package com.example.administrator.neteasemusic.modle.event;

/**
 * 作者：bjc on 2017/12/5 10:50
 * 邮箱：783491064@qq.com
 * QQ ：783491064
 * 类描述：更新收藏夹时，发送事件的实体类
 */
public class CollectionUpdateEvent {
    private boolean update;

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public CollectionUpdateEvent(boolean update) {
        this.update = update;
    }
}
