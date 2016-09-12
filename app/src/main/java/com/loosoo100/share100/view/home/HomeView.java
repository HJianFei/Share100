package com.loosoo100.share100.view.home;

import com.loosoo100.share100.view.base.BaseView;

import java.util.List;

/**
 * 创建时间： 2016/9/2.
 * 作者：loosoo100
 * 功能描述：
 */

public interface HomeView extends BaseView {

    //初始化home页面的广告轮播条
    void initHomeViewPager();

    //初始化home页面的商家推荐信息
    void initHomeRecommend();

    //初始化home页面的附近商家信息
    void initHomeRecyclerView(List<String> object);
}
