package com.xhs.backupforcesmarthome.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * @author zdl
 * @date 2018/3/16 17:03
 * email zdl328465042@163.com
 * description 所有fragment的基类
 */

public abstract class BaseFragment extends Fragment {

    /**
     * 贴附的activity
     */
    protected FragmentActivity activity;

    /**
     * 根View
     */
    protected View rootView;

    /**
     * 是否对用户可见
     */
    protected boolean isVisibleToUser;

    /**
     * 是否加载完成
     * 当执行完onCreateView，view的初始化方法后即为true
     */
    protected boolean isPrepare;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(setLayoutResourceId(), container, false);

        ButterKnife.bind(rootView);
        initData();
        isPrepare = true;
        setListener();
        requestData();

        return rootView;
    }

    /**
     * 设置根布局资源id
     * @return layoutId
     */
    protected abstract int setLayoutResourceId();

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
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        if (isVisibleToUser){
            //用户可见时执行的操作
            onVisibleToUser();
        }else {
            //用户不可见时执行的操作
            onGoneToUser();
        }
    }

    /**
     * 用户可见时执行的操作
     */
    protected void onVisibleToUser(){
        if (isPrepare && isVisibleToUser){
            onLazyLoad();
        }
    }

    /**
     * 用户不可见时执行的操作
     */
    protected void onGoneToUser(){}

    /**
     * 懒加载，仅当用户可见且view初始化结束后才会执行的操作
     */
    protected void onLazyLoad(){}
}
