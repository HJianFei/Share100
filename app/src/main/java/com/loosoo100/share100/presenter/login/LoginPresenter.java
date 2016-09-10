package com.loosoo100.share100.presenter.login;

/**
 * 创建时间： 2016/9/3.
 * 作者：loosoo100
 * 功能描述：负责完成登录页面与于业务逻辑间的交互接口
 */

public interface LoginPresenter {

    //验证登录
    void validateCredentials(String userName, String userPassword);

    //页面销毁
    void onDestroy();
}
