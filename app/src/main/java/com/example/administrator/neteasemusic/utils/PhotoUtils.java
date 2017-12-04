package com.example.administrator.neteasemusic.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import com.example.administrator.neteasemusic.ui.colllection.CollectionCreateActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 作者：bjc on 2017/12/4 13:42
 * 邮箱：783491064@qq.com
 * QQ ：783491064
 * 类描述：图片选取帮助类；
 */
public class PhotoUtils {
    public static final int TAKE_PHOTO = 1;//拍照的时候的请求标记；
    public static final int CHOOSE_PICTURE = 2; //当选中照片的时候回调的标记；
    public static final int CROP_PICTURE = 3;//当裁剪完后的照片回调的标记；
    private Activity activity;
    private Uri imageUri;
    private String mPhotoPath;
    private File file;

    public PhotoUtils(Activity context) {
        this.activity=context;
        String fileName=getPhotoFileName();
        initFile(fileName);
    }

    /**
     * 照片文件的初始化；
     * @param fileName
     */
    private void initFile(String fileName) {
        String path=FileUtils.getAppPictureDir(activity.getApplicationContext());
        File dir=new File(path);
        if(!dir.exists()){
            dir.mkdirs();
        }
        file = new File(path,fileName);
        imageUri = Uri.fromFile(file);
    }


    /**
     * 获取图片存储的文件名；
     * @return
     */
    private String getPhotoFileName() {
        Date date=new Date(System.currentTimeMillis());
        SimpleDateFormat format=new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss");
        return format.format(date)+".jpg";
    }

    /**
     * 用相机功去拍照获取照片
     */
    public void takePhoto() {
        mPhotoPath = null;
        String status = Environment.getExternalStorageState();
        //检查SD卡是否可用；
        if(!status.equals(Environment.MEDIA_MOUNTED)){
            Log.e("user",mPhotoPath);
            Log.i("TestFile","不能拍照，因为无法存储");
            return;
        }
        Intent intent=new Intent();
        intent.setAction("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
        activity.startActivityForResult(intent,TAKE_PHOTO);


    }

    /**
     * 直接在图片库中选取已有照片
     */
    public void picPhoto() {

    }

    /**
     * 裁剪的代码
     * @param uri
     * @param outputX
     * @param outputY
     * @param data
     * @param requestCode
     */
    public void cropImageUri(Uri uri,int outputX,int outputY,boolean data,int requestCode){
        Intent intent=new Intent("com.android.camera.action.CROP");// 开始切割
        intent.setDataAndType(uri,"image/*");
        intent.putExtra("copy","true");
        intent.putExtra("aspectX",1);// 裁剪框比例
        intent.putExtra("aspectY",1);
        intent.putExtra("outputX", outputX);// 输出图片大小
        intent.putExtra("outputY", outputY);
        intent.putExtra("scale", true);
        intent.putExtra("scaleUpIfNeeded", true);//黑边
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri); // 返回一个文件
        intent.putExtra("return-data", data);// 不直接返回数据
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection
        activity.startActivityForResult(intent, requestCode);
    }

    public Uri getUri() {
        Uri uri=Uri.fromFile(file);
        return uri;
    }

    /**
     * 通过URI来获取图片
     * @param uri
     * @return
     */
    public Bitmap decodeUriAsBitmap(Uri uri) {
        Bitmap bitmap=null;
        try {
            bitmap = BitmapFactory.decodeStream(activity.getContentResolver().openInputStream(uri));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return bitmap;
    }
}
