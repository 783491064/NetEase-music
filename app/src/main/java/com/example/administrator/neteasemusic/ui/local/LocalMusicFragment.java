package com.example.administrator.neteasemusic.ui.local;


import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.neteasemusic.R;
import com.example.administrator.neteasemusic.data.Album;
import com.example.administrator.neteasemusic.data.Artist;
import com.example.administrator.neteasemusic.data.Song;
import com.example.administrator.neteasemusic.modle.LocalView;
import com.example.administrator.neteasemusic.music.MusicPlaylist;
import com.example.administrator.neteasemusic.service.MusicPlayerManager;
import com.example.administrator.neteasemusic.ui.BaseFragment;
import com.example.administrator.neteasemusic.ui.adapter.LocalRecyclerAdapter;
import com.example.administrator.neteasemusic.ui.adapter.OnItemClickListener;
import com.example.administrator.neteasemusic.ui.presenter.LocalLibraryPresenter;

import java.util.List;

import kr.co.namee.permissiongen.PermissionGen;

/**
 * 作者：bjc on 2017/11/22 12:30
 * 邮箱：783491064@qq.com
 * QQ ：783491064
 * 类描述：单曲片段
 */
public class LocalMusicFragment extends BaseFragment implements LocalView.LocalMusic{

    private LocalLibraryPresenter localLibraryPresenter;
    private RecyclerView recyclerView;
    private View view;
    private LocalRecyclerAdapter mRecyclerAdapter;
    private MusicPlaylist musicPlayList;

    public LocalMusicFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        localLibraryPresenter = new LocalLibraryPresenter(this,getActivity());
        musicPlayList=new MusicPlaylist();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_local_music, container, false);
        initRecyclerView();
        return view;
    }

    /**
     * 初始化recyclerView
     */
    private void initRecyclerView() {
        recyclerView = (RecyclerView)view.findViewById(R.id.rcv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerAdapter = new LocalRecyclerAdapter(getActivity());
        recyclerView.setAdapter(mRecyclerAdapter);
        mRecyclerAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(Object item, int position) {
                //点击条目开始播放音乐
                MusicPlayerManager.get().playQueue(musicPlayList,position);
                gotoSongPlayerActivity();
            }

            @Override
            public void onItemSettingClick(View view, Object item, int position) {

            }
        });
        localLibraryPresenter.requestMusic();

    }

    public static LocalMusicFragment newInstance() {
        LocalMusicFragment localMusicFragment = new LocalMusicFragment();
        return localMusicFragment;
    }


    @Override
    public void getLocalMusicSuccess(List<Song> songs) {
        musicPlayList.setQueue(songs);
        musicPlayList.setTitle("本地歌曲");
        mRecyclerAdapter.setData(songs);
    }

    @Override
    public void getLocalMusicFail(Throwable throwable) {

    }

    @Override
    public void onResume() {
        super.onResume();
        mRecyclerAdapter.notifyDataSetChanged();
    }
}
