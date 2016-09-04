package com.loosoo100.share100.presenter.register;

/**
 * 创建时间： 2016/9/3.
 * 作者：loosoo100
 * 功能描述：
 */

public interface RegisterPresenter {
    //注册
    void Register(String userName, String userPassword);

    //销毁
    void onDestroy();

}
