package com.example.administrator.neteasemusic.ui.adapter;

import android.text.TextUtils;

/**
 * Created by bjc on 2017/11/7.
 *
 * 侧滑菜单 每一选项条目的对象；
 */

public class LvLeftItem {
    private static final int NO_ICON = 0;
    public static final int TYPE_NORMAL = 0;
    public static final int TYPE_NO_ICON = 1;
    public static final int TYPE_SEPARATOR = 2;
    public String mName;
    public int mIcon;
    public int type;

    public LvLeftItem(String name) {
        this(NO_ICON, name);
    }

    public LvLeftItem(int icon, String name) {
        this.mIcon = icon;
        this.mName = name;

        if (icon == NO_ICON && TextUtils.isEmpty(name)) {
            type = TYPE_SEPARATOR;
        } else if (icon == NO_ICON) {
            type = TYPE_NO_ICON;
        } else {
            type = TYPE_NORMAL;
        }

    }


}
