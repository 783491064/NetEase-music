package com.example.administrator.neteasemusic.ui.presenter;


import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.example.administrator.neteasemusic.data.Song;
import com.example.administrator.neteasemusic.modle.LocalView;
import com.example.administrator.neteasemusic.utils.LocalMusicLibrary;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 作者：bjc on 2017/11/23 13:39
 * 邮箱：783491064@qq.com
 * QQ ：783491064
 * 类描述：获取本地音乐的presenter
 */
public class LocalLibraryPresenter {
    private LocalView.LocalMusic localMusic;
    private Context context;

    public LocalLibraryPresenter(LocalView.LocalMusic localMusic, Context context) {
        this.context = context;
        this.localMusic = localMusic;
    }



    public void requestMusic() {
        Observable.create(new Observable.OnSubscribe<List<Song>>() {
            @Override
            public void call(Subscriber<? super List<Song>> subscriber) {
                List<Song> songs = LocalMusicLibrary.getAllSong(context);
                subscriber.onNext(songs);
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Song>>() {
                    @Override
                    public void call(List<Song> songs) {
                        //获取歌曲成功
                        if (localMusic != null) {
                            localMusic.getLocalMusicSuccess(songs);
                        }

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        //获取本地歌曲失败；
                        if (localMusic != null) {
                            localMusic.getLocalMusicFail(throwable);
                        }
                    }
                });
    }
}
