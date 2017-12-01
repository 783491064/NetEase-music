package com.example.administrator.neteasemusic.db.dao;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.administrator.neteasemusic.data.CollectionBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：bjc on 2017/12/1 16:36
 * 邮箱：783491064@qq.com
 * QQ ：783491064
 * 类描述：
 */
public class CollectionDao extends BaseDao {

    private static final String TABLE = "COLLECTION";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_COVER_URL = "cover_url";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_COUNT = "count";

    /**
     * 建表sql
     *
     * @return sql
     */
    public static String createTable() {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE IF NOT EXISTS " + TABLE + "(");
        sb.append(COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,");
        sb.append(COLUMN_TITLE + " varchar(100),");
        sb.append(COLUMN_COVER_URL + " varchar(200),");
        sb.append(COLUMN_DESCRIPTION + " TEXT,");
        sb.append(COLUMN_COUNT + " INTEGER");
        sb.append(");");
        return sb.toString();
    }

    /**
     * 查询所有的收藏夹
     *
     * @return
     */
    public List<CollectionBean> queryAll() {
        List<CollectionBean> collectionList = new ArrayList<>();
        Cursor cursor = query(TABLE, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            collectionList.add(getCollection(cursor));
        }
        cursor.close();
        return collectionList;
    }

    /**
     * 查找指定的收藏夹
     *
     * @param id
     * @return
     */
    public CollectionBean query(int id) {
        CollectionBean bean = null;
        String selection = COLUMN_ID + "=?";
        String[] selectionArgs = new String[]{String.valueOf(id)};
        List<CollectionBean> collectionList = new ArrayList<>();
        Cursor cursor = query(TABLE, null, selection, selectionArgs, null, null, null);
        if (cursor.moveToNext()) {
            bean = getCollection(cursor);
        }
        cursor.close();
        return bean;
    }

    /**
     * 插入一条收藏夹记录
     *
     * @param collectionBean
     */
    public long insertCollection(CollectionBean collectionBean) {
        return insert(TABLE, null, getCollectionContent(collectionBean));
    }

    /**
     * 更新一条收藏夹信息
     *
     * @param collectionBean
     */
    public int updateCollection(CollectionBean collectionBean) {
        String whereClause = COLUMN_ID + "=?";
        String[] whereArgs = new String[]{collectionBean.getId() + ""};
        return update(TABLE, getCollectionContent(collectionBean), whereClause, whereArgs);
    }

    /**
     * 删除一条收藏夹信息
     *
     * @param collectionBean
     */
    public void deleteCollection(CollectionBean collectionBean) {
        String whereClause = COLUMN_ID + "=?";
        String[] whereArgs = new String[]{collectionBean.getId() + ""};
        delete(TABLE, whereClause, whereArgs);
    }


    private CollectionBean getCollection(Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
        String title = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE));
        String description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));
        String coverUrl = cursor.getString(cursor.getColumnIndex(COLUMN_COVER_URL));
        int count = cursor.getInt(cursor.getColumnIndex(COLUMN_COUNT));
        CollectionBean collection = new CollectionBean(id, title, coverUrl, count, description);
        return collection;
    }

    public ContentValues getCollectionContent(CollectionBean collection) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, collection.getTitle());
        values.put(COLUMN_COVER_URL, collection.getCoverUrl());
        values.put(COLUMN_DESCRIPTION, collection.getDescription());
        values.put(COLUMN_COUNT, collection.getCount());
        return values;
    }

}
