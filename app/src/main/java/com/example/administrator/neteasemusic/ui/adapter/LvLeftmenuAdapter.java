package com.example.administrator.neteasemusic.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.TextViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.neteasemusic.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by bjc on 2017/11/7.
 * 侧滑菜单的listview 的适配器
 */

public class LvLeftmenuAdapter extends BaseAdapter {
    private Context mContext;
    private List<LvLeftItem> mItems = new ArrayList<>(
            Arrays.asList(
                    new LvLeftItem(R.mipmap.topmenu_icn_night, "夜间模式"),
                    new LvLeftItem(R.mipmap.topmenu_icn_skin, "主题换肤"),
                    new LvLeftItem(R.mipmap.topmenu_icn_time, "定时关闭音乐"),
                    new LvLeftItem(R.mipmap.topmenu_icn_vip, "清除缓存"),
                    new LvLeftItem(R.mipmap.topmenu_icn_exit, "退出")
            )
    );
    private LayoutInflater inflater;
    private int mIconSize;

    public LvLeftmenuAdapter(Context context) {
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
        mIconSize = context.getResources().getDimensionPixelSize(R.dimen.drawer_icon_size);
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LvLeftItem lvLeftItem = mItems.get(position);
        switch (lvLeftItem.type) {
            case LvLeftItem.TYPE_NORMAL:
                if (convertView == null) {
                    convertView = inflater.inflate(R.layout.desin_drawer_item, parent, false);
                }
                TextView itemView = (TextView) convertView;
                itemView.setText(lvLeftItem.mName);
                Drawable icon = mContext.getResources().getDrawable(lvLeftItem.mIcon);
                if (icon != null) {
                    icon.setBounds(0, 0, mIconSize, mIconSize);
                    TextViewCompat.setCompoundDrawablesRelative(itemView, icon, null, null, null);
                }
                break;
            case LvLeftItem.TYPE_NO_ICON:
                if(convertView==null){
                    convertView=inflater.inflate(R.layout.design_drawer_item_subheader,parent,false);
                }
                TextView subHeader = (TextView) convertView;
                subHeader.setText(lvLeftItem.mName);
                break;
            case LvLeftItem.TYPE_SEPARATOR:
                if(convertView==null){
                    convertView=inflater.inflate(R.layout.design_drawer_item_separator,parent,false);
                }
                break;
        }
        return convertView;
    }
}
