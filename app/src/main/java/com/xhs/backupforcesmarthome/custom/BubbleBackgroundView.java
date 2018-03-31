package com.xhs.backupforcesmarthome.custom;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.xhs.backupforcesmarthome.R;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * @author zx
 * @date 2018/3/29 17:09
 * email 1058083107@qq.com
 * description
 */
public class BubbleBackgroundView extends ViewGroup {
    private Context context;
    private Bitmap bitmap;
    private ScheduledExecutorService scheduledThreadPool;
    private Thread myThread;
    private boolean flag = false;

    public BubbleBackgroundView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        //初始化泡泡
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bubble);
        //初始化线程池
        scheduledThreadPool = Executors.newScheduledThreadPool(1);
        //初始化线程
        myThread = new MyThread();
    }

    /**
     * 设置父控件宽高
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 设置子控件位置
     * @param b
     * @param i
     * @param i1
     * @param i2
     * @param i3
     */
    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        scheduledThreadPool.schedule(myThread, 2000, TimeUnit.MILLISECONDS);
    }

    /**
     * 线程回调到主线程
     */
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                if (getChildCount() >= 20) {
                    removeView(getChildAt(0));
                }
                if (getChildCount() < 20) {
                    ImageView imageView = new ImageView(context);
                    imageView.setImageBitmap(bitmap);
                    imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    addView(imageView);
                    setChildLayout(imageView);
                }
            }
        }
    };


    /**
     * 子线程，在线程中进行泡泡生成与添加
     */
    private class MyThread extends Thread{
        @Override
        public void run() {
            super.run();
            handler.sendEmptyMessage(1);
        }
    }

    /**
     * 设置子控件的位置
     * @param imageView 子控件
     */
    private void setChildLayout(ImageView imageView) {
        //随机距离左面的距离，控制从控件上面出来还是下面出来
        int left = new Random().nextInt(getWidth() - bitmap.getWidth()) + bitmap.getWidth();
        //随机距离顶部的距离，控制从左面和右面出来
        int top = new Random().nextInt(getHeight() - bitmap.getHeight()) + bitmap.getHeight();
        //随机四个方位
        int direction = new Random().nextInt(4);
        //随机泡泡x、y轴的运动时间
        long a = new Random().nextInt(8000) + 15000;
        long b1 = new Random().nextInt(8000) + 15000;
        //随机参数控制是往左飘还是往右飘
        int c = new Random().nextInt(2);
        //随机参数控制是往上飘还是往下飘
        int f = new Random().nextInt(2);
        int d = 1;
        int e = 1;
        if (c == 0) {
            d = -1;
        }
        if (f == 0) {
            e = -1;
        }
        //方位设置
        switch (direction) {
            case 0:
                //从上面出来的泡泡
                imageView.layout(left, 0, left + bitmap.getWidth(), bitmap.getHeight());
                //控制在y轴上只能往下飘
                e = 1;
                break;
            case 1:
                //从下面出来的泡泡
                imageView.layout(left, getHeight() - bitmap.getHeight(), left + bitmap.getWidth(), getHeight());
                //控制在y轴上只能往上飘
                e = -1;
                break;
            case 2:
                //从左面出来的泡泡
                imageView.layout(0, top, bitmap.getWidth(), bitmap.getHeight() + top);
                //控制在x轴上只能往右飘
                d = 1;
                break;
            case 3:
                //从右面出来的泡泡
                imageView.layout(getWidth() - bitmap.getWidth(), top, getWidth(), bitmap.getHeight() + top);
                //控制在x轴上只能往左飘
                d = -1;
                break;
            default:
        }
        //设置动画
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView, "translationX", 0, d * (getWidth() + bitmap.getWidth()));
        animator.setDuration(a);
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(imageView, "translationY", 0, e * (getHeight() + bitmap.getHeight()));
        animator1.setDuration(b1);
        animator.start();
        animator1.start();
    }

}
