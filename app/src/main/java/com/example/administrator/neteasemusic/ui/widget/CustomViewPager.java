package com.example.administrator.neteasemusic.ui.widget;

import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by bjc on 2017/11/6.
 */

public class CustomViewPager extends ViewPager {
    PointF mPointF=new PointF();
    private OnSingleTouchListener onSingleTouchListener;

    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                mPointF.x=ev.getX();
                mPointF.y=ev.getY();
                if(this.getChildCount()>1){
                    getParent().requestDisallowInterceptTouchEvent(true);//请求父控件不要拦截；
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(this.getChildCount()>1){
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;
            case MotionEvent.ACTION_UP:
                if(PointF.length(ev.getX()-mPointF.x,ev.getY()-mPointF.y)<(float)5.0){
                    //单纯的点击
                    onSingleTouch(this);
                }
                break;
        }

        return super.onTouchEvent(ev);
    }

    private void onSingleTouch(View view) {
        if(onSingleTouchListener!=null){
            onSingleTouchListener.onSingleTouch();
        }
    }

    interface OnSingleTouchListener{
        void onSingleTouch();
    }

    public void setOnSingleTouchListener(OnSingleTouchListener onSingleTouchListener){
        this.onSingleTouchListener=onSingleTouchListener;
    }


}
