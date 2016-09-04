package com.loosoo100.share100.model.login;

import android.os.Handler;

/**
 * 创建时间： 2016/9/3.
 * 作者：loosoo100
 * 功能描述：登录操作的逻辑处理
 */

public class LoginIndicatorImpl implements LoginIndicator {
    @Override
    public void login(final String userName, final String userPassword, final OnLoginFinishListener loginFinishListener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loginFinishListener.onSuccess();
            }
        }, 2000);
    }
}