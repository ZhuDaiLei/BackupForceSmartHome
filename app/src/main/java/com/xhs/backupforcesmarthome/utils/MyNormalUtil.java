package com.xhs.backupforcesmarthome.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * @author zdl
 * @date 2018/3/3 10:59
 * email zdl328465042@163.com
 * explain 一些常用的工具类
 */

public class MyNormalUtil {

    /**
     * toast提示
     *
     * @param context 上下文
     * @param msg     消息内容
     */
    public static void mToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 判断是否为视频格式
     *
     * @param url url地址
     * @return true:是       false:不是
     */
    public static boolean isVideo(String url) {
        String[] urlEnd = {".rm", ".rmvb", ".mp4", ".mov", ".mtv", ".dat", ".wmv", ".avi", ".3gp", ".amv", ".dmv"};
        for (String str : urlEnd) {
            if (url.endsWith(str)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 判断是否为图片格式
     *
     * @param url url地址
     * @return true:是       false:不是
     */
    public static boolean isImage(String url) {
        String[] urlEnd = {".jpg", ".jpeg", ".png", ".bmp"};
        for (String str : urlEnd) {
            if (url.endsWith(str)) {
                return true;
            }
        }

        return false;
    }
}
