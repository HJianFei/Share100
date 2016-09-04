package com.loosoo100.share100.model.register;

import android.os.Handler;

/**
 * 创建时间： 2016/9/3.
 * 作者：loosoo100
 * 功能描述：
 */

public class RegisterIndicatorImpl implements RegisterIndicator {
    @Override
    public void Register(String userName, String userPassword, final OnRegisterFinishListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                listener.onSuccess();
            }
        }, 2000);
    }
}
