package com.xhs.backupforcesmarthome.custom;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

/**
 * @author zdl
 * @date 2018/3/20 14:10
 * email zdl328465042@163.com
 * description 前端随机生成验证码
 */

public class VerificationCode {

    /**
     * 随机数组
     */
    private static final char[] CHARS = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
    };

    private static VerificationCode bmpCode;

    public static VerificationCode getInstance() {
        if (null == bmpCode) {
            bmpCode = new VerificationCode();
        }
        return bmpCode;
    }


    /* ******************** default settings ******************** */

    /**
     * 默认验证码个数
     */
    private static final int DEFAULT_CODE_LENGTH = 4;

    /**
     * 默认字体大小
     */
    private static final int DEFAULT_FONT_SIZE = 24;

    /**
     * 默认干扰线条条数
     */
    private static final int DEFAULT_LINE_NUMBER = 3;

    /**
     * 默认干扰点个数
     */
    private static final int DEFAULT_DOT_NUMBER = 10;

    /**
     * padding值
     */
    private static final int BASE_PADDING_LEFT = 10, RANGE_PADDING_LEFT = 15, BASE_PADDING_TOP = 15, RANGE_PADDING_TOP = 20;

    /**
     * 验证码的默认宽高
     */
    private static final int DEFAULT_WIDTH = 100, DEFAULT_HEIGHT = 40;

    private int width = DEFAULT_WIDTH, height = DEFAULT_HEIGHT;
    private int basePaddingLeft = BASE_PADDING_LEFT, rangePaddingLeft = RANGE_PADDING_LEFT,
            basePaddingTop = BASE_PADDING_TOP, rangePaddingTop = RANGE_PADDING_TOP;
    private int codeLength = DEFAULT_CODE_LENGTH, fontSize = DEFAULT_FONT_SIZE, lineNumber = DEFAULT_LINE_NUMBER, dotNumber = DEFAULT_DOT_NUMBER;

    private String code;
    private int padding_left, padding_top;
    private Random random = new Random();

    /**
     * 生成验证码图片
     *
     * @return 验证码图片
     */
    public Bitmap createBitmap() {
        padding_left = 0;

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);

        code = createCode();

        canvas.drawColor(randomColor());
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(fontSize);
        //画验证码
        for (int i = 0; i < codeLength; i++) {
            randomTextStyle(paint);
            randomPadding();
            canvas.drawText(code.charAt(i) + "", padding_left, padding_top, paint);
        }
        //画线条
        for (int i = 0; i < lineNumber; i++) {
            drawLine(canvas, paint);
        }
        //画点
        for (int i = 0; i < dotNumber; i++) {
            drawDot(canvas, paint);
        }
        //保存
        canvas.save();
        canvas.restore();
        return bitmap;
    }

    /**
     * 生成验证码
     *
     * @return 验证码
     */
    private String createCode() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < codeLength; i++) {
            sb.append(CHARS[random.nextInt(CHARS.length)]);
        }
        return sb.toString();
    }

    /**
     * 生成随机字体样式，颜色、粗细、倾斜度
     * @param paint paint
     */
    private void randomTextStyle(Paint paint){
        int color = randomColor();
        paint.setColor(color);
        //true为粗体，false为非粗体
        paint.setFakeBoldText(random.nextBoolean());
        float skewX = random.nextInt(11)/10;
        skewX = random.nextBoolean() ? skewX : -skewX;
        //float类型参数，负数表示右斜，整数左斜
        paint.setTextSkewX(skewX);
        //true为下划线，false为非下划线
        //paint.setUnderlineText(true);
        //true为删除线，false为非删除线
        //paint.setStrikeThruText(true);
    }

    /**
     * 随机生成padding值
     */
    private void randomPadding(){
        padding_left += basePaddingLeft + random.nextInt(rangePaddingLeft);
        padding_top = basePaddingTop + random.nextInt(rangePaddingTop);
    }

    /**
     * 画干扰线
     * @param canvas canvas
     * @param paint paint
     */
    private void drawLine(Canvas canvas, Paint paint){
        int color = randomColor();
        int startX = random.nextInt(width);
        int startY = random.nextInt(height);
        int stopX = random.nextInt(width);
        int stopY = random.nextInt(height);
        paint.setStrokeWidth(1);
        paint.setColor(color);
        canvas.drawLine(startX, startY, stopX, stopY, paint);
    }

    /**
     * 画干扰点
     * @param canvas canvas
     * @param paint paint
     */
    private void drawDot(Canvas canvas, Paint paint){
        int color = randomColor();
        int dotX = random.nextInt(width);
        int dotY = random.nextInt(height);
        paint.setStrokeWidth(1);
        paint.setColor(color);
        canvas.drawCircle(dotX, dotY, 1, paint);
    }

    /**
     * 生成随机颜色
     * @return color
     */
    private int randomColor(){
        return randomColor(1);
    }

    /**
     * 随机颜色
     * @param rate 比率
     * @return color
     */
    private int randomColor(int rate){
        int red = random.nextInt(256)/rate;
        int green = random.nextInt(256)/rate;
        int blue = random.nextInt(256)/rate;
        return Color.rgb(red, green, blue);
    }


    /* ******************** 提供的接口 ******************** */

    /**
     * 生成的验证码
     * @return 验证码
     */
    public String getCode() {
        return code;
    }
}
