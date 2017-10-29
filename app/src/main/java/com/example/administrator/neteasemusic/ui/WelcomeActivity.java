package com.example.administrator.neteasemusic.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.res.ObbScanner;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import com.example.administrator.neteasemusic.R;
import com.example.administrator.neteasemusic.ui.music.BaseActivity;
import com.example.administrator.neteasemusic.ui.music.MainActivity;

public class WelcomeActivity extends BaseActivity {
    private ImageView iv_welcome;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        iv_welcome=(ImageView)findViewById(R.id.iv_welcome);
        startAnimation();

    }

    /***
     * 开启动画的方法
     */
    private void startAnimation() {
        ObjectAnimator scaleX=ObjectAnimator.ofFloat(iv_welcome,"scaleX",0f,1f);
        ObjectAnimator scaleY=ObjectAnimator.ofFloat(iv_welcome,"scaleY",0f,1f);
        ObjectAnimator alpha=ObjectAnimator.ofFloat(iv_welcome,"alpha",0f,1f);
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.play(scaleX).with(scaleY).with(alpha);
        animatorSet.setDuration(1500);
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSet.start();
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                startActivity(MainActivity.class);
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            return true;
        }
        return false;
    }
}
