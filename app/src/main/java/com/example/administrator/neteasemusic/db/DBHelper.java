package com.example.administrator.neteasemusic.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.administrator.neteasemusic.db.dao.CollectionDao;
import com.example.administrator.neteasemusic.db.dao.SongDao;

/**
 * 作者：bjc on 2017/12/1 13:44
 * 邮箱：783491064@qq.com
 * QQ ：783491064
 * 类描述：数据库帮助类
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASENAME = "cainiaomusic.db";
    private static final int DATABASEVERSION = 1;

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBHelper(Context context) {
        super(context, DATABASENAME, null, DATABASEVERSION);
    }

    /**
     * 数据库第一次被创建的时候调用的方法；
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        //歌曲表
        db.execSQL(SongDao.createTable());
        db.execSQL(SongDao.createIndex());
        //收藏夹表
        db.execSQL(CollectionDao.createTable());

        //收藏夹关联表
    }

    /**
     * 当传入的数据库版本号与当前的版本号不同时才调用该方法，更新数据库；
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
