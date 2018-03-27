package com.xhs.backupforcesmarthome.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.socks.library.KLog;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author zdl
 * @date 2018/2/26 14:02
 * email zdl328465042@163.com
 * explain 所有activity的基类
 */

public abstract class BaseActivity extends AppCompatActivity  {
    private static final String TAG = "BaseActivity";

    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        KLog.e(TAG, "当前实例的类是：" + getClass().getSimpleName());
        ActivityCollector.addActivity(this);
        unbinder = ButterKnife.bind(this);

        initData();
        setListener();
        requestData();
    }

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 设置listener
     */
    protected abstract void setListener();

    /**
     * 从服务器请求数据
     */
    protected void requestData(){}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
        unbinder.unbind();
    }
}
