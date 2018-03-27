package com.xhs.backupforcesmarthome.base;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zdl
 * @date 2018/2/26 14:03
 * email zdl328465042@163.com
 * explain 方便快捷实现退出整个APP
 */

public class ActivityCollector {

    /**
     * 新建一个list来暂存activity
     */
    private static List<Activity> activities = new ArrayList<>();

    /**
     * 向list中添加activity
     * @param activity 要添加的activity
     */
    public static void addActivity(Activity activity){
        activities.add(activity);
    }

    /**
     * 从list中移除activity
     * @param activity 要移除的activity
     */
    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }

    /**
     * 将list中存储的所有activity全部销毁
     */
    public static void finishAll(){
        for (Activity activity : activities){
            //判断activity是否存在，若存在则销毁改activity
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
        //销毁全部activity后清空list
        activities.clear();
        //杀死当前进程
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
