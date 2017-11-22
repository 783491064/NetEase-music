package com.example.administrator.neteasemusic.ui.guide;

import android.net.Uri;
import android.os.Bundle;

import com.example.administrator.neteasemusic.R;
import com.example.administrator.neteasemusic.ui.widget.PlayVideoView;

/**
 * Created by bijingcun
 * on 2017/11/5.
 * 引导页的Fragment 实现播放mp4 的功能；
 */
public class GuidFragment extends LoadFragment {
    private PlayVideoView videoView;
    private Uri uri;

    @Override
    protected int setContentView() {
        return R.layout.fragment_guide;
    }

    @Override
    protected void lazyLoadDate() {
        videoView = getViewById(R.id.play_video);
        Bundle arguments = getArguments();
        int index = arguments.getInt("Index");
        if (index == 1) {
            uri = Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.guide_1);
        } else if (index == 2) {
            uri = Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.guide_2);
        } else {
            uri = Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.guide_3);
        }
        videoView.palyVideo(uri);

    }

    @Override
    protected void stopLoadDate() {
        super.stopLoadDate();
        if (videoView != null) {
            videoView.stopPlayback();
        }
    }

}
