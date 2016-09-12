package com.loosoo100.share100.view.base;

/**
 * 创建时间： 2016/9/2.
 * 作者：loosoo100
 * 功能描述：所有view的积累接口，主要实现页面常见的方法
 */

public interface BaseView {
    //显示进度条
    void showProgress();

    //隐藏进度条
    void hideProgress();

    //显示加载错误
    void showError();

    //显示数据为空
    void showEmpty();

}
