package com.xhs.backupforcesmarthome.entity;

/**
 * @author zdl
 * @date 2018/3/28 14:46
 * email zdl328465042@163.com
 * description 气泡实体类
 */
public class Bubble {

    /**
     * 气泡半径
     */
    private int radius;

    /**
     * 上升速度
     */
    private float speedY;

    /**
     * 平移速度
     */
    private float speedX;

    /**
     * 气泡x坐标
     */
    private float x;

    /**
     * 气泡y坐标
     */
    private float y;

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public float getSpeedY() {
        return speedY;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

    public float getSpeedX() {
        return speedX;
    }

    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
