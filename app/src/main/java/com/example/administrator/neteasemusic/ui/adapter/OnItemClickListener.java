package com.example.administrator.neteasemusic.ui.adapter;

import android.view.View;

/**
 * 作者：bjc on 2017/11/23 16:16
 * 邮箱：783491064@qq.com
 * QQ ：783491064
 * 类描述：列表的点击接口
 */
public interface OnItemClickListener<T> {
    void onItemClick(T item, int position);

    void onItemSettingClick(View view, T item, int position);
}
