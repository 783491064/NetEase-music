package com.example.administrator.neteasemusic.ui.guide;


import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.neteasemusic.R;

/**
 * create by bijingcun 2017.11.5
 *
 * 实现Fragment 懒加载的父类
 *
 */
public abstract class LoadFragment extends Fragment {
    /**
     * 片段的view初始化完成
     */
    protected boolean isInitView = false;
    /**
     * 片段可以加载数据了
     */
    protected boolean canLoadDate = false;
    protected View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(setContentView(), container, false);
        isInitView = true;
        isCanLoadDate();
        return view;
    }

    /**
     * 懒加载数据   思想就是 当界面可见后再去加载改片段的数据
     */
    protected void isCanLoadDate() {
        if (!isInitView) {
            return;
        }
        if (getUserVisibleHint()) {
            lazyLoadDate();
            canLoadDate = true;
        } else {
            if (canLoadDate) {
                stopLoadDate();
            }
        }

    }

    /**
     * 系统告知界面可见了
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isCanLoadDate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isInitView=false;
        canLoadDate=false;
    }

    /**
     * 获取子类的布局view
     * @return
     */
    protected View getContentView(){
        return view;
    }

    /**
     * 根据子类的 布局id 获取到子view；
     * @param id
     * @param <T>
     * @return
     */
    protected <T extends View> T getViewById(int id){
        return (T)getContentView().findViewById(id);
    }

    /**
     * 停止加载数据
     */
    protected void stopLoadDate(){
    };

    /**
     * 子类懒加载数据
     */
    protected abstract void lazyLoadDate();

    /**
     * 子类实现自己的布局
     */
    protected abstract int setContentView();
}
