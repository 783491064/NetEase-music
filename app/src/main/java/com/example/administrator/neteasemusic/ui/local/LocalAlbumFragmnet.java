package com.example.administrator.neteasemusic.ui.local;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.neteasemusic.R;

/**
 * 作者：bjc on 2017/11/22 15:37
 * 邮箱：783491064@qq.com
 * QQ ：783491064
 * 类描述:专辑片段；
 */
public class LocalAlbumFragmnet extends Fragment {


    public LocalAlbumFragmnet() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_local_album_fragmnet, container, false);
    }

    public static LocalAlbumFragmnet newInstance() {
        LocalAlbumFragmnet localAlbumFragmnet = new LocalAlbumFragmnet();
        return localAlbumFragmnet;
    }
}
