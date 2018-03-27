package com.xhs.backupforcesmarthome.utils;

import java.math.BigDecimal;

/**
 * @author zdl
 * @date 2018/3/3 11:50
 * email zdl328465042@163.com
 * explain 封装基本数据类相关的工具类
 */

public class MyIntegerUtil {

    /**
     * double类型，保留几位小数
     *
     * @param num  double类型的数据源
     * @param size 保留几位小数
     * @return
     */
    public static double doubleReserveDecimal(double num, int size) {
        double result = 0.0;
        if (0.0 != num) {
            BigDecimal bd = new BigDecimal(num);
            result = bd.setScale(size, BigDecimal.ROUND_HALF_UP).doubleValue();
        }
        return result;
    }

    /**
     * float转int，四舍五入
     *
     * @param num
     * @return
     */
    public static int float2int(float num) {
        int result = 0;
        float zero = 0.0F;
        if (zero != num) {
            BigDecimal bd = new BigDecimal(num);
            result = bd.setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
        }
        return result;
    }
}
