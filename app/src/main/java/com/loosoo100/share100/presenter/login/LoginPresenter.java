package com.loosoo100.share100.presenter.login;

/**
 * 创建时间： 2016/9/3.
 * 作者：loosoo100
 * 功能描述：
 */

public interface LoginPresenter {

    void validateCredentials(String userName, String userPassword);

    void onDestroy();
}