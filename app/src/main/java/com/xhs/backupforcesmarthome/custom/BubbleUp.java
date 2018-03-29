package com.xhs.backupforcesmarthome.custom;

import android.animation.PointFEvaluator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.xhs.backupforcesmarthome.R;

import java.util.Random;

/**
 * @author zdl
 * @date 2018/3/29 10:56
 * email zdl328465042@163.com
 * description
 */
public class BubbleUp extends View {

    private int width;
    private int height;
    private Paint paint;
    private PointF currentPoint;
    private Random random;
    private Bitmap bgBitmap;
    private Bitmap bubbleBitmap;

    public BubbleUp(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(bgBitmap, 0, 0, paint);

        if (null == currentPoint) {
            currentPoint = new PointF(bubbleBitmap.getWidth()/2, bubbleBitmap.getHeight()/2);
            canvas.drawBitmap(bubbleBitmap, currentPoint.x, currentPoint.y, paint);
            startAnimation();
        }else {
            canvas.drawBitmap(bubbleBitmap, currentPoint.x, currentPoint.y, paint);
        }
    }

    /**
     * 初始化控件
     */
    private void init() {
        paint = new Paint();
        random = new Random();
        bgBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
        bubbleBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bubble);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void startAnimation(){
        final PointF startPoint = new PointF(bubbleBitmap.getWidth()/2, bubbleBitmap.getHeight()/2);
        final PointF endPoint = new PointF(bgBitmap.getWidth()-bubbleBitmap.getWidth()/2, bgBitmap.getHeight()-bubbleBitmap.getHeight()/2);
        ValueAnimator animator = ValueAnimator.ofObject(new PointFEvaluator(), startPoint, endPoint);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentPoint = (PointF) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.setDuration(10 * 1000);
        animator.start();
    }

    private Bitmap createBubble() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bubble);
        return bitmap;
    }


    /* ******************** 提供的接口 ******************** */

    public void bubbleUpDestory() {
        bgBitmap.recycle();
    }
}
