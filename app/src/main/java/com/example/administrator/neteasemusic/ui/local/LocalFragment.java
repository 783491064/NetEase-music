package com.example.administrator.neteasemusic.ui.local;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.neteasemusic.R;
import com.example.administrator.neteasemusic.ui.cnmusic.LocalMusicActivity;
import com.example.administrator.neteasemusic.ui.cnmusic.RecentPlayListActivity;
import com.example.administrator.neteasemusic.ui.colllection.CollectionCreateActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 作者：bjc on 2017/11/22 12:30
 * 邮箱：783491064@qq.com
 * QQ ：783491064
 * 类描述：本地音乐片段
 */
public class LocalFragment extends Fragment {

    @InjectView(R.id.recyle_view)
    RecyclerView recyleView;

    public LocalFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loccal, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.local_layout, R.id.recently_layout, R.id.download_layout, R.id.artist_layout, R.id.add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.local_layout://点击本地音乐
                LocalMusicActivity.open(getActivity());
                break;
            case R.id.recently_layout://点击最近播放
                RecentPlayListActivity.open(getActivity());
                break;
            case R.id.download_layout://点击下载管理
                break;
            case R.id.artist_layout://点击我的歌手
                break;
            case R.id.add://创建收藏夹；
                CollectionCreateActivity.open(getActivity());
                break;
        }
    }
}
