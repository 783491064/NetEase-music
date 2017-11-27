package com.example.administrator.neteasemusic.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

/**
 * 作者：bjc on 2017/11/27 13:13
 * 邮箱：783491064@qq.com
 * QQ ：783491064
 * 类描述：开启播放音乐的辅助类；
 */
public class MusicServiceHelper {
    public Context mContext;
    private static final MusicServiceHelper instance=new MusicServiceHelper();
    /**
     * 单例获取到对象；
     * @param context
     * @return
     */
    public static MusicServiceHelper get(Context context){
        instance.mContext=context;
        return instance;
    }

    /**
     * 单例前私有构造；
     */
    private MusicServiceHelper() {
    }


    /**
     * 初始化服务，开启服务；
     */
    public  MusicService mService;
    public void initService() {
        if(mService==null){
            Intent intent=new Intent(mContext,MusicService.class);
            ServiceConnection conn= new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    MusicService.MyBinder binder = (MusicService.MyBinder) service;
                    mService = binder.getMusicService();
                    mService.setUp();
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {
                    mService=null;
                }
            };
            mContext.startService(intent);
            mContext.bindService(intent,conn,Context.BIND_AUTO_CREATE);
        }
    }

    public MusicService getService() {
        return mService;
    }
}
