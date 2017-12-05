package com.example.administrator.neteasemusic.db.dao;


import android.util.Log;

import com.example.administrator.neteasemusic.data.CollectionBean;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 作者：bjc on 2017/12/4 17:36
 * 邮箱：783491064@qq.com
 * QQ ：783491064
 * 类描述：
 */
public class CollectionManager {
    private static final String TAG = CollectionManager.class.getSimpleName();
    private static CollectionManager instance;
    private final CollectionDao collectionDao;
    private final CollectionShipDao collectionShipDao;
    private List<CollectionBean> collectionList;

    public static CollectionManager getInstance() {
        if (instance == null) {
            instance = new CollectionManager();
        }
        return instance;
    }

    public CollectionManager() {
        collectionDao = new CollectionDao();
        collectionShipDao = new CollectionShipDao();
        getAllCollectionsFromDao();
    }

    /**
     * 预先异步重数据库获取数据
     */
    private void getAllCollectionsFromDao() {
        Observable.create(new Observable.OnSubscribe<List<CollectionBean>>() {
            @Override
            public void call(Subscriber<? super List<CollectionBean>> subscriber) {
                List<CollectionBean> collectionBeanList = collectionDao.queryAll();
                subscriber.onNext(collectionBeanList);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<CollectionBean>>() {
                    @Override
                    public void call(List<CollectionBean> collectionBeen) {
                        collectionList = collectionBeen;
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e(TAG, throwable.toString());
                    }
                });

    }

    /**
     * 设置收藏夹
     */
    public void setCollection(CollectionBean bean) {
        //先判断数据录是否包含这个收藏夹；
         int index=containCollection(bean);
        if(index<0){
            //如果没有就直接插入；
            collectionDao.insertCollection(bean);
            getAllCollections();
        }else{
            //如果有该收藏夹，更新内容；
            collectionDao.updateCollection(bean);
            getAllCollections().set(index,bean);
        }
    }

    /**
     * 判断是否包含当前文件夹，包含返回位置，不包含返回-1；
     * @param bean
     * @return
     */
    private int containCollection(CollectionBean bean) {
        for (CollectionBean collectionBean: getAllCollections()){
            if(bean.getId()==collectionBean.getId()){
                int index=getAllCollections().indexOf(bean);
                return index;
            }
        }
        return -1;
    }

    private List<CollectionBean> getAllCollections() {
        if(collectionList!=null){
            collectionList=collectionDao.queryAll();
        }
        if(collectionList==null){
            collectionList=new ArrayList<>();
        }
        return collectionList;
    }
}
