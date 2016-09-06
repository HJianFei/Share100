package com.loosoo100.share100.presenter.message;


import com.loosoo100.share100.model.message.MsgIndicator;
import com.loosoo100.share100.model.message.MsgIndicatorImpl;
import com.loosoo100.share100.view.message.MsgView;

/**
 * 创建时间： 2016/9/6.
 * 作者：HJianFei
 * 功能描述：
 */

public class MsgPresenterImpl implements MsgPresenter, MsgIndicator.OnMsgFinishListener {

    private MsgView mMsgView;
    private MsgIndicator mMsgIndicator;

    public MsgPresenterImpl(MsgView msgView) {
        mMsgView = msgView;
        mMsgIndicator = new MsgIndicatorImpl();
    }

    @Override
    public void onStart() {
        if (null != mMsgIndicator) {
            mMsgView.showProgress();
        }
        mMsgIndicator.loadDatas(this);

    }

    @Override
    public void onDestroy() {
        if (null != mMsgView) {
            mMsgView = null;
        }

    }

    @Override
    public void onLoadDatas() {
        if (null != mMsgView) {
            mMsgView.showProgress();
        }
        mMsgIndicator.loadDatas(this);

    }

    @Override
    public void onError() {
        if (null != mMsgView) {
            mMsgView.hideProgress();
        }
        mMsgView.onError();

    }

    @Override
    public void onFirstSuccess() {
        if (null != mMsgView) {
            mMsgView.hideProgress();
        }
        mMsgView.onFirstVisible();

    }

    @Override
    public void onRefreshSuccess() {
        if (null != mMsgView) {
            mMsgView.hideProgress();
        }
        mMsgView.Refreshing();

    }

    @Override
    public void onLoadMoreSuccess() {
        if (null != mMsgView) {
            mMsgView.hideProgress();
        }
        mMsgView.LoadingMore();

    }
}
