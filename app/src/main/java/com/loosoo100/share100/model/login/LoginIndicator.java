package com.loosoo100.share100.model.login;

/**
 * 创建时间： 2016/9/3.
 * 作者：loosoo100
 * 功能描述：
 */

public interface LoginIndicator {

    interface OnLoginFinishListener {
        void onFail();

        void onSuccess();
    }

    void login(String userName, String userPassword, OnLoginFinishListener loginFinishListener);
}
