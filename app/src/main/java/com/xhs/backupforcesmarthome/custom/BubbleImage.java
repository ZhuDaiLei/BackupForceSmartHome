package com.xhs.backupforcesmarthome.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.xhs.backupforcesmarthome.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author zdl
 * @date 2018/3/28 13:59
 * email zdl328465042@163.com
 * description 气泡上升效果
 */
public class BubbleImage extends View {
    private List<Bubble> bubbles = new ArrayList<Bubble>();
    private Random random = new Random();
    private int width, height;
    private boolean starting = false;
    private boolean isPause = false;

    public BubbleImage(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public BubbleImage(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public BubbleImage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        isPause = false;
        width = getWidth();
        height = getHeight();
        if (!starting) {
            starting = true;
            new Thread() {
                @Override
                public void run() {
                    while (true) {
                        if(isPause){
                            continue;
                        }
                        try {
                            Thread.sleep(random.nextInt(3) * 1000);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        Bubble bubble = new Bubble();
                        int radius = random.nextInt(30);
                        while (radius < 15) {
                            radius = random.nextInt(30);
                        }

                        float speedY = random.nextFloat()*3;
                        while (speedY < 1) {
                            speedY = random.nextFloat()*5;
                        }
                        bubble.setRadius(radius);
                        bubble.setSpeedY(speedY);
                        bubble.setX(width / 2);
                        bubble.setY(height);
                        float speedX = random.nextFloat()-0.5f;
                        while (speedX == 0) {
                            speedX = random.nextFloat()-0.5f;
                        }
                        bubble.setSpeedX(speedX*2);
                        bubbles.add(bubble);
                    }
                };
            }.start();
        }
        Paint paint = new Paint();
        // 绘制渐变正方形
        Shader shader = new LinearGradient(0, 0, 0, height, new int[] {
                getResources().getColor(R.color.colorAccent),
                getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.colorPrimaryDark) },
                null, Shader.TileMode.REPEAT);
        paint.setShader(shader);
        canvas.drawRect(0, 0, width, height, paint);
        paint.reset();
        paint.setColor(Color.WHITE);
        paint.setAlpha(60);
        List<Bubble> list = new ArrayList<Bubble>(bubbles);
        for (Bubble bubble : list) {
            if (bubble.getY() - bubble.getSpeedY() <= 0) {
                bubbles.remove(bubble);
            } else {
                int i = bubbles.indexOf(bubble);
                if (bubble.getX() + bubble.getSpeedX() <= bubble.getRadius()) {
                    bubble.setX(bubble.getRadius());
                } else if (bubble.getX() + bubble.getSpeedX() >= width
                        - bubble.getRadius()) {
                    bubble.setX(width - bubble.getRadius());
                } else {
                    bubble.setX(bubble.getX() + bubble.getSpeedX());
                }
                bubble.setY(bubble.getY() - bubble.getSpeedY());
                bubbles.set(i, bubble);
                canvas.drawCircle(bubble.getX(), bubble.getY(),
                        bubble.getRadius(), paint);
            }
        }
        invalidate();
    }
    @Override
    public void invalidate() {
        // TODO Auto-generated method stub
        super.invalidate();
        isPause = true;
    }

    private class Bubble {
        /** 气泡半径 */
        private int radius;
        /** 上升速度 */
        private float speedY;
        /** 平移速度 */
        private float speedX;
        /** 气泡x坐标 */
        private float x;
        /** 气泡y坐标 */
        private float y;

        /**
         * @return the radius
         */
        public int getRadius() {
            return radius;
        }

        /**
         * @param radius
         *            the radius to set
         */
        public void setRadius(int radius) {
            this.radius = radius;
        }

        /**
         * @return the x
         */
        public float getX() {
            return x;
        }

        /**
         * @param x
         *            the x to set
         */
        public void setX(float x) {
            this.x = x;
        }

        /**
         * @return the y
         */
        public float getY() {
            return y;
        }

        /**
         * @param y
         *            the y to set
         */
        public void setY(float y) {
            this.y = y;
        }

        /**
         * @return the speedY
         */
        public float getSpeedY() {
            return speedY;
        }

        /**
         * @param speedY
         *            the speedY to set
         */
        public void setSpeedY(float speedY) {
            this.speedY = speedY;
        }

        /**
         * @return the speedX
         */
        public float getSpeedX() {
            return speedX;
        }

        /**
         * @param speedX
         *            the speedX to set
         */
        public void setSpeedX(float speedX) {
            this.speedX = speedX;
        }

    }
}
