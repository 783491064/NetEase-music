package com.example.administrator.neteasemusic.ui;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.administrator.neteasemusic.R;
import com.example.administrator.neteasemusic.data.Song;
import com.example.administrator.neteasemusic.service.MusicPlayerManager;
import com.example.administrator.neteasemusic.service.OnSongChangedListener;
import com.example.administrator.neteasemusic.ui.adapter.OnItemClickListener;
import com.example.administrator.neteasemusic.ui.adapter.PlayListAdapter;
import com.lb.materialdesigndialog.base.DialogBase;
import com.lb.materialdesigndialog.base.DialogWithTitle;
import com.lb.materialdesigndialog.impl.MaterialDialogNormal;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 作者：bjc on 2017/11/30 11:07
 * 邮箱：783491064@qq.com
 * QQ ：783491064
 * 类描述：
 */
public class PlayQueueFragment extends DialogFragment implements OnSongChangedListener {

    @InjectView(R.id.playlist_addto)
    TextView playlistAddto;
    @InjectView(R.id.play_list_number)
    TextView playListNumber;
    @InjectView(R.id.playlist_clear_all)
    TextView playlistClearAll;
    @InjectView(R.id.play_list)
    RecyclerView playList;
    private Context mContext;
    private PlayListAdapter playListAdapter;
    public PlayQueueFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置样式
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.CustomDatePickerDialog);
        mContext =getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //设置无标题
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置从底部弹出
        WindowManager.LayoutParams params = getDialog().getWindow()
                .getAttributes();
        params.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setAttributes(params);
        View view = inflater.inflate(R.layout.fragment_queue, container);
        MusicPlayerManager.get().registerListener(this);
        ButterKnife.inject(this, view);
        initRecyclerView(view);
        return view;
    }

    private void initRecyclerView(View view) {
        playListAdapter = new PlayListAdapter(mContext);
        playList.setLayoutManager(new LinearLayoutManager(mContext));
        playList.setHasFixedSize(true);
        playList.setAdapter(playListAdapter);
        playList.setItemAnimator(new DefaultItemAnimator());
        if(MusicPlayerManager.get().getMusicPlaylist()!=null){
            playListAdapter.setSongs(MusicPlayerManager.get().getMusicPlaylist().getQueue());
        }
        playListAdapter.setSongClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(Object item, int position) {
                MusicPlayerManager.get().playQueueItem(position);
            }

            @Override
            public void onItemSettingClick(View view, Object item, int position) {

            }
        });

    }


    @Override
    public void onSongChanged(Song song) {
        if(MusicPlayerManager.get().getMusicPlaylist()!=null){
            playListAdapter.setSongs(MusicPlayerManager.get().getMusicPlaylist().getQueue());
        }
    }

    @Override
    public void onPlayBackStateChanged(PlaybackStateCompat state) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.playlist_addto, R.id.playlist_clear_all})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.playlist_addto:

                break;
            case R.id.playlist_clear_all:
                showBasicDialog();
                break;
        }
    }

    private void showBasicDialog() {
        MaterialDialogNormal materialDialogNormal = new MaterialDialogNormal(getActivity());
        materialDialogNormal.setMessage("确定要清空列表吗?");
        materialDialogNormal.setNegativeButton("取消", new DialogWithTitle.OnClickListener() {
            @Override
            public void click(DialogBase dialog, View view) {
                dialog.dismiss();
            }
        });
        materialDialogNormal.setPositiveButton("确定", new DialogWithTitle.OnClickListener() {
            @Override
            public void click(DialogBase dialog, View view) {

            }
        });
        materialDialogNormal.setTitle("清空列表");
    }

    @Override
    public void onStart() {
        super.onStart();
        //设置fragment的高度，宽度
       int dialogHeight= (int)(mContext.getResources().getDisplayMetrics().heightPixels*0.6);
        getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,dialogHeight);
        getDialog().setCanceledOnTouchOutside(true);
    }
}
