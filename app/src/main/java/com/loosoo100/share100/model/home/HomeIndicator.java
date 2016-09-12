package com.loosoo100.share100.model.home;

import java.util.List;

/**
 * 创建时间： 2016/9/2.
 * 作者：loosoo100
 * 功能描述：
 */

public interface HomeIndicator {

    interface onHomeFinishListener {
        //加载错误
        void onError();

        //页面第一次加载成功
        void onFirstSuccess(List<String> object3);

        //刷新成功
        void onRefreshSuccess(List<String> object);

        //加载成功
        void onLoadMoreSuccess(List<String> object);
    }

    //页面第一次可见的时候数据的加载
    void firstInitHomeView(onHomeFinishListener listener);

    //下拉刷新
    void refreshHomeView(onHomeFinishListener listener);

    //上拉加载更多
    void loadMoreHomeView(onHomeFinishListener listener);


}
