package com.example.administrator.neteasemusic.ui;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.example.administrator.neteasemusic.ui.play.PlayingActivity;
import com.example.administrator.neteasemusic.ui.presenter.LocalLibraryPresenter;

import kr.co.namee.permissiongen.PermissionGen;

/**
 * 作者：bjc on 2017/11/23 13:35
 * 邮箱：783491064@qq.com
 * QQ ：783491064
 * 类描述：
 */
public class BaseFragment extends Fragment {
    private LocalLibraryPresenter mLibraryPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PermissionGen.needPermission(BaseFragment.this, 100,
                new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_CONTACTS
                }
        );
    }

    public void showToast(int toastRes) {
        Toast.makeText(getActivity(), getString(toastRes), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }
    public boolean gotoSongPlayerActivity(){
        PlayingActivity.open(getActivity());
        return true;
    }
}
