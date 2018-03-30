package com.xhs.backupforcesmarthome.custom;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.xhs.backupforcesmarthome.R;
import com.xhs.backupforcesmarthome.utils.MyPhoneUtil;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.operators.observable.ObservableTimer;
import io.reactivex.schedulers.Schedulers;


/**
 * @author zx
 * @date 2018/3/29 17:09
 * email 1058083107@qq.com
 * description
 */
public class BubbleBackgroundView extends ViewGroup {
    private boolean isFirst = true;
    private Context context;
    private boolean running = true;
    private Rect rect;

    public BubbleBackgroundView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        rect = new Rect(0, 0, MyPhoneUtil.getScreenWidth((Activity) context), MyPhoneUtil.getScreenHeight((Activity) context));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        Consumer consumer = new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                if (getChildCount() >= 10) {
                    removeView(getChildAt(0));
                }
                if (getChildCount() < 10) {
                    ImageView imageView = new ImageView(context);
                    imageView.setImageResource(R.drawable.bubble);
                    imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    addView(imageView);
                    setChildLayout(imageView);
                    //setAnimation(imageView);
                }
            }
        };
        Observable timer = Observable.timer(1, TimeUnit.SECONDS);

        timer.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(consumer);

    }

    private void setChildLayout(ImageView imageView) {
        int left = new Random().nextInt(getWidth());
        int top = new Random().nextInt(getHeight());
        int test = new Random().nextInt(4);
        long a = new Random().nextInt(4000) + 10000;
        long b1 = new Random().nextInt(4000) + 10000;
        int c = new Random().nextInt(2);
        int f = new Random().nextInt(2);
        int d = 1;
        int e = 1;
        if (c == 0) {
            d = -1;
        }
        if (f == 0) {
            e = -1;
        }
        switch (test) {
            case 0:
                imageView.layout(0, 0, left, 50);
                Log.e("创建成功", "------------0");
                e = 1;
                break;
            case 1:
                imageView.layout(0, 0, 50, top);
                Log.e("创建成功", "------------1");
                d = 1;
                break;
            case 2:
                imageView.layout(getWidth() - left, getHeight() - 50, getWidth(), getHeight());
                Log.e("创建成功", "------------2");
                e = -1;
                break;
            case 3:
                imageView.layout(getWidth() - 50, getHeight() - top, getWidth(), getHeight());
                Log.e("创建成功", "------------3");
                d = -1;
                break;
            default:
        }
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView, "translationX", 0, d * (getWidth() + 100));
        animator.setDuration(a);
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(imageView, "translationY", 0, e * (getHeight() + 100));
        animator1.setDuration(b1);
        animator.start();
        animator1.start();
    }


    private void setAnimation(ImageView imageView) {
        long a = new Random().nextInt(4000) + 9000;
        long b1 = new Random().nextInt(4000) + 9000;
        int c = new Random().nextInt(2);
        int f = new Random().nextInt(2);
        int d = 1;
        int e = 1;
        if (c == 0) {
            d = -1;
        }
        if (f == 0) {
            e = -1;
        }
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView, "translationX", 0, d * (getWidth() + 100));
        animator.setDuration(a);
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(imageView, "translationY", 0, e * (getHeight() + 100));
        animator1.setDuration(b1);
        animator.start();
        animator1.start();
    }

    public void stopRunning() {
        running = false;
    }
}
