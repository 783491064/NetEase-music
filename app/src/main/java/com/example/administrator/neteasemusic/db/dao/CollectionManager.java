package com.example.administrator.neteasemusic.db.dao;

/**
 * 作者：bjc on 2017/12/4 17:36
 * 邮箱：783491064@qq.com
 * QQ ：783491064
 * 类描述：
 */
public class CollectionManager {
    private static CollectionManager instance;
    public static CollectionManager getInstance(){
        if(instance==null){
            instance=new CollectionManager();
        }
        return instance;
    }
    public CollectionManager(){
        CollectionDao collectionDao=new CollectionDao();
        CollectionShipDao collectionShipDao=new CollectionShipDao();
        getAllCollectionsFromDao();
    }

    /**
     * 预先异步重数据库获取数据
     */
    private void getAllCollectionsFromDao() {


    }

}
