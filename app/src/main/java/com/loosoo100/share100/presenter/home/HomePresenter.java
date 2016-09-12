package com.loosoo100.share100.presenter.home;

/**
 * 创建时间： 2016/9/2.
 * 作者：loosoo100
 * 功能描述：
 */

public interface HomePresenter {

    //页面第一次可见，页面数据的加载
    void firstInitHomeView();

    //下拉刷新页面
    void refreshHomeView();

    //上拉加载更多
    void loadMoreHomeView();

    //页面销毁
    void onHomeViewDestroy();
}
