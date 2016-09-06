package com.loosoo100.share100.model.message;

/**
 * 创建时间： 2016/9/6.
 * 作者：HJianFei
 * 功能描述：
 */

public interface MsgIndicator {

    interface OnMsgFinishListener {

        void onError();

        void onFirstSuccess();

        void onRefreshSuccess();

        void onLoadMoreSuccess();
    }

    //加载数据
    void loadDatas(OnMsgFinishListener listener);


}
