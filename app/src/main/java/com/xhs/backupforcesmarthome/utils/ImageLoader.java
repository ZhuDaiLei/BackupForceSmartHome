package com.xhs.backupforcesmarthome.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * @author zdl
 * @date 2018/3/14 13:32
 * email zdl328465042@163.com
 * explain 在第三方库的基础上再封装一层，以便可以很方便的替换成其他库
 */

public class ImageLoader {

    public static void load(Context context, Object source, ImageView imageView){
        Glide.with(context)
                .load(source)
                .centerCrop()
                .into(imageView);
    }
}
