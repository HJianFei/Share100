package com.loosoo100.share100.application;

import android.app.Application;

/**
 * 创建时间： 2016/9/2.
 * 作者：loosoo100
 * 功能描述：
 */

public class BaseApplication extends Application {

    private static BaseApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        this.instance = this;
    }

    public static BaseApplication getInstance() {
        return instance;
    }
}
