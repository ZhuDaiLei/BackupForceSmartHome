package com.xhs.backupforcesmarthome.base;

import android.app.Application;

import com.socks.library.KLog;
import com.xhs.backupforcesmarthome.config.InitConfig;

import org.litepal.LitePal;

/**
 * @author zdl
 * @date 2018/3/2 11:35
 * email zdl328465042@163.com
 * explain
 */

public class BackupForceSmartHomeApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initKLog();
        initLitePal();
    }

    /**
     * 初始化LitePal
     */
    private void initLitePal() {
        LitePal.initialize(this);
    }

    /**
     * 初始化KLog
     */
    private void initKLog() {
        KLog.init(InitConfig.LOG_DEBUG, "Backup Force");
    }
}
