package com.example.administrator.neteasemusic.ui.widget.lrcview;

import android.content.Context;

/**
 * 作者：bjc on 2017/11/27 10:34
 * 邮箱：783491064@qq.com
 * QQ ：783491064
 * 类描述：歌曲的歌词的处理
 */
public class LrcUtils {
    public static int dp2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int sp2px(Context context, float spValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
