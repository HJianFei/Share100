package com.loosoo100.share100.view.login;

import com.loosoo100.share100.view.base.BaseView;

/**
 * 创建时间： 2016/9/3.
 * 作者：loosoo100
 * 功能描述：登录接口
 */

public interface LoginView extends BaseView {

    //验证失败
    void failToLogin();

    //验证成功，跳转主页面
    void navicateToHome();
}
