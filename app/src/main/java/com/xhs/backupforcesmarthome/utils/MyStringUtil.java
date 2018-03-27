package com.xhs.backupforcesmarthome.utils;

import com.xhs.backupforce.constants.CustomString;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * @author zdl
 * @date 2018/3/3 11:01
 * email zdl328465042@163.com
 * explain 字符串工具类
 */

public class MyStringUtil {
    /**
     * 判断字符串是否为空
     *
     * @param str 要判断的字符串
     * @return true:为空  false:不为空
     */
    public static boolean isEmpty(String str) {
        return null == str || str.equals(CustomString.EMPTY_1) || str.equals(CustomString.EMPTY_2);
    }

    /**
     * 除去字符串中的空格、回车
     *
     * @param str
     * @return
     */
    public static String handleEnterAndBlank(String str) {
        String result = str.trim();
        if (!isEmpty(str)) {
            if (str.contains(CustomString.ENTER)) {
                result = str.trim().replace(CustomString.ENTER, "");
            }
        }
        return result;
    }

    /**
     * list的深拷贝
     *
     * @param src 源list
     * @param <T> 泛型
     * @return list
     * @throws IOException            IOException
     * @throws ClassNotFoundException ClassNotFoundException
     */
    public static <T> List<T> deepCopy(List<T> src) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(src);

        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        @SuppressWarnings("unchecked")
        List<T> dest = (List<T>) in.readObject();
        return dest;
    }
}
