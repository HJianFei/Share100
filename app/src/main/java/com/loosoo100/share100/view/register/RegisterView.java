package com.loosoo100.share100.view.register;

import com.loosoo100.share100.view.base.BaseView;

/**
 * 创建时间： 2016/9/3.
 * 作者：loosoo100
 * 功能描述：
 */

public interface RegisterView extends BaseView {

    //注册失败
    void failToRegister();

    //注册成功
    void successToRegister();
}
