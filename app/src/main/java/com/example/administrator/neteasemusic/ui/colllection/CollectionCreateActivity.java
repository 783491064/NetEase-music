package com.example.administrator.neteasemusic.ui.colllection;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.administrator.neteasemusic.R;
import com.example.administrator.neteasemusic.data.CollectionBean;
import com.example.administrator.neteasemusic.utils.PhotoUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class CollectionCreateActivity extends AppCompatActivity {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.collection_cover)
    ImageView collectionCover;
    @InjectView(R.id.collection_title)
    TextView collectionTitle;
    @InjectView(R.id.collection_des)
    EditText collectionDes;
    private int cid;
    private PhotoUtils photoUtils;
    private static final String TAG = CollectionCreateActivity.class.getSimpleName();
    private boolean hasChange;
    private CollectionBean collectionBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_create);
        ButterKnife.inject(this);
        initToolBar();
    }

    private void initToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (getIntent() != null) {
            cid = getIntent().getIntExtra("cid", -1);
            if (cid == -1) {
                getSupportActionBar().setTitle(R.string.collection_create_title);
            }
        }
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        photoUtils = new PhotoUtils(this);//照片处理类工具；
        initData();
    }

    private void initData() {
        hasChange = false;
        if (cid != -1) {//之前已经有的收藏夹

        } else {//新建收藏夹
            collectionBean = new CollectionBean(-1, getString(R.string.collection_title_default), "", 0, "");
        }
        collectionDes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                hasChange = true;
                String s = editable.toString();
                collectionBean.setDescription(s);
            }
        });
    }

    public static void open(Context context) {
        open(context, -1);
    }

    private static void open(Context context, int cid) {
        Intent intent = new Intent();
        intent.setClass(context, CollectionCreateActivity.class);
        intent.putExtra("cid", cid);
        context.startActivity(intent);
    }

    @OnClick({R.id.collection_cover, R.id.collection_title, R.id.collection_des})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.collection_cover://点击了改变封面的额按钮；
                new MaterialDialog.Builder(this)
                        .title(R.string.collection_dialog_cover_title)
                        .items(R.array.collection_cover)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                switch (which) {
                                    case 0:
                                        photoUtils.takePhoto();
                                        break;
                                    case 1:
                                        photoUtils.picPhoto();
                                        break;
                                }
                            }
                        })
                        .show();
                break;
            case R.id.collection_title:
                break;
            case R.id.collection_des:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PhotoUtils.TAKE_PHOTO:
                //拍照回调
                if (resultCode == RESULT_OK)
                    photoUtils.cropImageUri(photoUtils.getUri(), 200, 200, false, PhotoUtils.CROP_PICTURE);
                break;
            case PhotoUtils.CHOOSE_PICTURE:
                break;
            case PhotoUtils.CROP_PICTURE:
                //拍照切图的回调：
                if (resultCode == RESULT_OK) {
                    Bitmap bitmap = photoUtils.decodeUriAsBitmap(photoUtils.getUri());
                    Drawable drawable = new BitmapDrawable(bitmap);
                    int w = drawable.getIntrinsicWidth();
                    int H = drawable.getIntrinsicWidth();
                    if (w < 50.0 || H < 50.0) {
                        //头像太小
                        Log.e(TAG, "头像太小");
                        return;
                    }
                    hasChange = true;
                    collectionCover.setImageBitmap(bitmap);
                    collectionBean.setCoverUrl(photoUtils.getUri().getPath());
                }
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_collection,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //退出的操作
        if(item.getItemId()==android.R.id.home){
            onBackPressed();
            return true;
        }
        //保存的操作；
        if(item.getItemId()==R.id.action_store){
//            CollectionManager.getInstance().setCollection(collectionBean);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        new MaterialDialog.Builder(this)
                .title(R.string.collection_dialog_update_title)
                .content(R.string.collection_dialog_update_content)
                .positiveText(R.string.confirm)
                .negativeText(R.string.cancel)
                .onPositive(new MaterialDialog.SingleButtonCallback(){

                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback(){

                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                        finish();
                    }
                }).show();
    }
}
