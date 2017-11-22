package com.example.administrator.neteasemusic.ui.guide;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * 作者：bjc on 22017/11/7. 12:30
 * 邮箱：783491064@qq.com
 * QQ ：783491064
 * 类描述：唱片片段ViewPager适配器；
 */
public class PlayVideoView extends VideoView {
    public PlayVideoView(Context context) {
        super(context);
    }

    public PlayVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PlayVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));
    }

    protected void palyVideo(Uri uri) {
        if (uri == null) {
            throw new IllegalArgumentException("uri cannot be null");
        }
        setVideoURI(uri);
        start();
        setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);//设置可以循环播放
            }
        });
        /**
         * 播放错误
         */
        setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                return true;
            }
        });
    }
}
