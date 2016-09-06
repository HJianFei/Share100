package com.loosoo100.share100.model.message;

import android.os.Handler;

/**
 * 创建时间： 2016/9/6.
 * 作者：HJianFei
 * 功能描述：
 */

public class MsgIndicatorImpl implements MsgIndicator {


    @Override
    public void loadDatas(final OnMsgFinishListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                listener.onFirstSuccess();
            }
        }, 2000);
    }
}
