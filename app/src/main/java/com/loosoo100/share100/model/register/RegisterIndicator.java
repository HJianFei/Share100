package com.loosoo100.share100.model.register;

/**
 * 创建时间： 2016/9/3.
 * 作者：loosoo100
 * 功能描述：
 */

public interface RegisterIndicator {
    interface OnRegisterFinishListener {

        //失败
        void onError();

        //成功
        void onSuccess();
    }

    void Register(String userName, String userPassword, OnRegisterFinishListener listener);
}
