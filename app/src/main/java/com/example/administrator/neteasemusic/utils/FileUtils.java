package com.example.administrator.neteasemusic.utils;

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
}
