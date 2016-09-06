package com.loosoo100.share100.view.message;

import com.loosoo100.share100.view.base.BaseView;

/**
 * 创建时间： 2016/9/6.
 * 作者：HJianFei
 * 功能描述：
 */

public interface MsgView extends BaseView {

    //第一次加载数据
    void onFirstVisible();

    //刷新数据
    void Refreshing();

    //加载更多
    void LoadingMore();

    //数据加载失败
    void onError();
}
