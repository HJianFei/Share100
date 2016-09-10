package com.loosoo100.share100.model.login;

/**
 * 创建时间： 2016/9/3.
 * 作者：loosoo100
 * 功能描述：登录操作的业务逻辑接口
 */

public interface LoginIndicator {

    interface OnLoginFinishListener {
        void onError();

        void onSuccess();
    }

    void login(String userName, String userPassword, OnLoginFinishListener loginFinishListener);
}
