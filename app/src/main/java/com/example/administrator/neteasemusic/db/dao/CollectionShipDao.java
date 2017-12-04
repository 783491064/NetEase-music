package com.example.administrator.neteasemusic.db.dao;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.administrator.neteasemusic.data.CollectionBean;
import com.example.administrator.neteasemusic.data.CollectionShipBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：bjc on 2017/12/4 10:06
 * 邮箱：783491064@qq.com
 * QQ ：783491064
 * 类描述：收藏夹歌曲关系表；
 */
public class CollectionShipDao extends BaseDao {

    private static final String TABLE = "COLLECTIONSHIP";

    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_CID = "cid";
    private static final String COLUMN_SID = "sid";


    public static String createTable() {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE IF NOT EXISTS " + TABLE + "(");
        sb.append(COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,");
        sb.append(COLUMN_CID + " INTEGER,");
        sb.append(COLUMN_SID + " INTEGER,");
        sb.append("unique (" + COLUMN_CID + "," + COLUMN_SID + ")");
        sb.append(");");
        return sb.toString();
    }

    /**
     * 获取收藏夹上的所有歌曲(sid)；
     *
     * @param cid
     * @return
     */
    public List<CollectionShipBean> queryByCid(int cid) {
        String selection = COLUMN_CID + "=?";
        String[] selectionArgs = new String[]{String.valueOf(cid)};
        List<CollectionShipBean> collectionList = new ArrayList<>();
        Cursor cursor = query(TABLE, null, selection, selectionArgs, null, null, null);
        while (cursor.moveToNext()) {
            collectionList.add(getCollectionShip(cursor));
        }
        cursor.close();
        return collectionList;

    }

    /**
     * 根据收藏夹ID和歌曲ID查找歌曲；
     *
     * @param cid
     * @param sid
     * @return
     */
    public CollectionShipBean queryByCidAndSid(int cid, int sid) {
        String selections = COLUMN_CID + "=? and " + COLUMN_SID + "=?";
        String[] selectionArgs = new String[]{String.valueOf(cid), String.valueOf(sid)};
        Cursor cursor = query(TABLE, null, selections, selectionArgs, null, null, null);
        CollectionShipBean bean = null;
        if (cursor.moveToNext()) {
            bean = getCollectionShip(cursor);
        }
        cursor.close();
        return bean;
    }

    /**
     * 插入一条收藏夹关联记录
     */
    public long insertCollectionShip(CollectionShipBean collectionShipBean) {
        return insert(TABLE, null, getCollectionContent(collectionShipBean));
    }

    /**
     * 更新一条收藏夹中的关联数据；
     *
     * @param collectionBean
     * @return
     */
    public long updateCollection(CollectionShipBean collectionBean) {
        String whereClause = COLUMN_ID + "=?";
        String[] whereArgs = new String[]{collectionBean.getId() + ""};
        return update(TABLE, getCollectionContent(collectionBean), whereClause, whereArgs);
    }

    /**
     * 删除一条收藏夹关联信息
     *
     * @param id
     */
    public int deleteCollection(int id) {
        String whereClause = COLUMN_ID + "=?";
        String[] whereArgs = new String[]{id + ""};
        return delete(TABLE, whereClause, whereArgs);
    }

    /**
     * 获取要被插入的收藏夹歌曲的相关数据
     *
     * @param collectionShipBean
     * @return
     */
    private ContentValues getCollectionContent(CollectionShipBean collectionShipBean) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_CID, collectionShipBean.getCid());
        values.put(COLUMN_SID, collectionShipBean.getSid());
        return values;
    }

    private CollectionShipBean getCollectionShip(Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
        int cid = cursor.getInt(cursor.getColumnIndex(COLUMN_CID));
        int sid = cursor.getInt(cursor.getColumnIndex(COLUMN_SID));

        CollectionShipBean collectionShipBean = new CollectionShipBean(id, cid, sid);
        return collectionShipBean;
    }

}
