package com.example.administrator.neteasemusic.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * 作者：bjc on 2017/11/23 14:54
 * 邮箱：783491064@qq.com
 * QQ ：783491064
 * 类描述：文件管理
 */
public class FileUtils {

    /**
     * 根据文件路径判断文件是否存在
     */
    public static boolean existFile(String path) {
        File path1 = new File(path);
        return path1.exists();
    }

    /**
     * 应用关联的图片的存储空间；
     * getExternalFilesDir()  一般放一些长时间保存的数据
     * getExternalCacheDir() 一般放一些临时性保存的数据
     * 是获取的啥的 sd 卡外部的存储空间  files  cache
     * 当应用被卸载后，文件目录就会被删除，避免的垃圾文件的产生；
     */
    public static String getAppPictureDir(Context context) {
        File file=context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return file.getPath();
    }
}
