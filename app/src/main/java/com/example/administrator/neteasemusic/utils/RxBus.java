package com.example.administrator.neteasemusic.utils;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * 作者：bjc on 2017/12/5 10:28
 * 邮箱：783491064@qq.com
 * QQ ：783491064
 * 类描述：事件总线
 */
public class RxBus {
    private static volatile RxBus instance;
    private final Subject<Object,Object> bus;
    public RxBus(){
        bus=new SerializedSubject<>(PublishSubject.create());
    }
    public static RxBus getInstance(){
        if(instance==null){
            synchronized (RxBus.class){
                if(instance==null){
                    instance=new RxBus();
                }
            }
        }
        return instance;
    }
    //发送一个事件
    public void post(Object o){
        bus.onNext(o);
    }

    public <T> Observable<T> tObservable(Class<T> eventType){
        return bus.ofType(eventType);
    }
}
