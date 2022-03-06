package com.example.retrofitrxjava.base;

import android.app.Application;

import com.example.retrofitrxjava.network.NetWorkManger;

/**
 * 初始化操作
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        NetWorkManger.getInstance().init();
    }
}
