package com.example.administrator.neteasemusic.ui.alum;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.neteasemusic.R;

/**
 * 作者：bjc on 2017/11/22 12:30
 * 邮箱：783491064@qq.com
 * QQ ：783491064
 * 类描述：新唱片片段
 */
public class NewAlumFragment extends Fragment {
    public NewAlumFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_alum, container, false);
    }

    public static NewAlumFragment newInstance() {
        NewAlumFragment newAlumFragment = new NewAlumFragment();
        return newAlumFragment;
    }
}
