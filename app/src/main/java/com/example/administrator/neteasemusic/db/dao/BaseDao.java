package com.example.administrator.neteasemusic.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.neteasemusic.MusicApplication;
import com.example.administrator.neteasemusic.db.DBHelper;

/**
 * 作者：bjc on 2017/12/1 14:30
 * 邮箱：783491064@qq.com
 * QQ ：783491064
 * 类描述：基础  dao 层
 */
public abstract class BaseDao {

    private final DBHelper dh;
    private final SQLiteDatabase db;

    public BaseDao() {
        dh = new DBHelper(MusicApplication.getInstance().getApplicationContext());
        db = dh.getWritableDatabase();
    }

    public void close() {
        db.close();
        dh.close();
    }

    /**
     * 查询
     *
     * @param table         表明
     * @param columns       列名
     * @param selection     查询条件
     * @param selectionArgs 查询条件赋值
     * @param groupBy       分组
     * @param having        语法
     * @param orderBy       排序
     * @return
     */
    public Cursor query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        Cursor c = db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
        return c;
    }

    /**
     * 插入
     *
     * @param table          表明
     * @param nullColumnHack 一行中的字段
     * @param values         各个字段名的值
     * @return 返回新插入行的ID 如果失败返回-1；
     */
    public long insert(String table, String nullColumnHack, ContentValues values) {
        return db.insert(table, nullColumnHack, values);
    }

    /**
     * 删除某一行
     *
     * @param table       表名
     * @param whereClause 删除的条件
     * @param whereArgs   条件赋值
     * @return
     */
    public int delete(String table, String whereClause, String[] whereArgs) {
        return db.delete(table, whereClause, whereArgs);
    }

    /**
     * 更新；
     *
     * @param table       表名
     * @param values      map 键值对（更改该后的值）
     * @param whereClause 更新的条件
     * @param whereArgs   更新条件的赋值；
     * @return
     */
    public int update(String table, ContentValues values, String whereClause, String[] whereArgs) {
        return db.update(table, values, whereClause, whereArgs);
    }

    /**
     * 数据的替换  原理是先删除存在的整行数据后重新插入
     * 需要先指定索引才能使用；
     *
     * @param table
     * @param nullColumnHack
     * @param initialValues
     * @return
     */
    public long replace(String table, String nullColumnHack, ContentValues initialValues) {
        return db.replace(table, nullColumnHack, initialValues);
    }

}
