package com.xhs.backupforcesmarthome.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/**
 * @author zdl
 * @date 2018/3/2 10:09
 * email zdl328465042@163.com
 * explain 封装图片相关的工具
 */

public class MyImageUtil {

    /**
     * 根据图片分辨率以及我们实际需要展示的大小，计算压缩率
     *
     * @param options   BitmapFactory.Options
     * @param reqWidth  reqWidth
     * @param reqHeight reqHeight
     * @return int
     */
    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    /**
     * 如果是放大图片，filter决定是否平滑，如果是缩小图片，filter无影响
     *
     * @param src       Bitmap
     * @param dstWidth  dstWidth
     * @param dstHeight dstHeight
     * @return Bitmap
     */
    private static Bitmap createScaleBitmap(Bitmap src, int dstWidth, int dstHeight) {
        Bitmap dst = Bitmap.createScaledBitmap(src, dstWidth, dstHeight, false);
        //如果没有缩放，那么不回收
        if (src != dst) {
            //释放Bitmap的native像素数组
            src.recycle();
        }
        return dst;
    }

    /**
     * 从Resources中加载图片
     *
     * @param res       Resources
     * @param resId     resId
     * @param reqWidth  reqWidth
     * @param reqHeight reqHeight
     * @return Bitmap
     */
    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {
        //首先通过 options.inJustDecodeBounds = true 获得图片的尺寸
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        //然后根据图片分辨率以及我们实际需要展示的大小，计算压缩率
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        //设置压缩率，并解码
        options.inJustDecodeBounds = false;
        Bitmap src = BitmapFactory.decodeResource(res, resId, options);
        //返回目标大小的缩略图
        return createScaleBitmap(src, reqWidth, reqHeight);
    }

    /**
     * 从SDka中加载图片
     *
     * @param pathName  图片路径
     * @param reqWidth  reqWidth
     * @param reqHeight reqHeight
     * @return Bitmap
     */
    public static Bitmap decodeSampledBitmapFromSd(String pathName, int reqWidth, int reqHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(pathName, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        Bitmap src = BitmapFactory.decodeFile(pathName, options);
        return createScaleBitmap(src, reqWidth, reqHeight);
    }

    /**
     * Drawable 转 Bitmap
     *
     * @param drawable 源drawable
     * @return bitmap
     */
    public static Bitmap drawable2bitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bd = (BitmapDrawable) drawable;
            return bd.getBitmap();
        }
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);
        return bitmap;
    }
}
