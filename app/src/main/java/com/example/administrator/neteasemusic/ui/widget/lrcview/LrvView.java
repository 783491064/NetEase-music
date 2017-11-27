package com.example.administrator.neteasemusic.ui.widget.lrcview;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Looper;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.animation.LinearInterpolator;

import com.example.administrator.neteasemusic.R;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 作者：bjc on 2017/11/27 10:32
 * 邮箱：783491064@qq.com
 * QQ ：783491064
 * 类描述：播放歌词的控件；
 */
public class LrvView extends SurfaceView {
    private List<LrcEntry> mLrcEntryList = new ArrayList<>();
    private TextPaint mPaint = new TextPaint();
    private float mTextSize;
    private float mDividerHeight;
    private long mAnimationDuration;
    private int mNormalColor;
    private int mCurrentColor;
    private String mLabel;
    private float mLrcPadding;
    private ValueAnimator mAnimator;
    private float mAnimateOffset;
    private long mNextTime = 0L;
    private int mCurrentLine = 0;
    private Object mFlag;

    public LrvView(Context context) {
        this(context, null);
    }

    public LrvView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LrvView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
//        setBackgroundResource(R.drawable.play_disc_halo);
        setBackgroundColor(getResources().getColor(R.color.color_transparent));
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.LrcView);
        mTextSize = ta.getDimension(R.styleable.LrcView_lrcTextSize, LrcUtils.sp2px(getContext(), 12));
        mDividerHeight = ta.getDimension(R.styleable.LrcView_lrcDividerHeight, LrcUtils.dp2px(getContext(), 16));
        mAnimationDuration = ta.getInt(R.styleable.LrcView_lrcAnimationDuration, 1000);
        mAnimationDuration = (mAnimationDuration < 0) ? 1000 : mAnimationDuration;
        mNormalColor = ta.getColor(R.styleable.LrcView_lrcNormalTextColor, 0xFFFFFFFF);
        mCurrentColor = ta.getColor(R.styleable.LrcView_lrcCurrentTextColor, 0xFFFF4081);
        mLabel = ta.getString(R.styleable.LrcView_lrcLabel);
        mLabel = TextUtils.isEmpty(mLabel) ? "暂无歌词" : mLabel;
        mLrcPadding = ta.getDimension(R.styleable.LrcView_lrcPadding, 0);
        ta.recycle();

        mPaint.setAntiAlias(true);
        mPaint.setTextSize(mTextSize);
        mPaint.setTextAlign(Paint.Align.LEFT);

    }
}
