package com.loosoo100.share100.presenter.register;

import com.loosoo100.share100.model.register.RegisterIndicator;
import com.loosoo100.share100.model.register.RegisterIndicatorImpl;
import com.loosoo100.share100.view.register.RegisterView;

/**
 * 创建时间： 2016/9/3.
 * 作者：loosoo100
 * 功能描述：
 */

public class RegisterPresenterImpl implements RegisterPresenter, RegisterIndicator.OnRegisterFinishListener {
    private RegisterView mRegisterView;
    private RegisterIndicator mRegisterIndicator;

    public RegisterPresenterImpl(RegisterView registerView) {
        mRegisterView = registerView;
        mRegisterIndicator = new RegisterIndicatorImpl();
    }

    @Override
    public void Register(String userName, String userPassword) {
        if (null != mRegisterView) {
            mRegisterView.showProgress();
            mRegisterIndicator.Register(userName, userPassword, this);
        }

    }

    @Override
    public void onDestroy() {
        if (null != mRegisterView) {
            mRegisterView = null;
        }

    }

    @Override
    public void onFail() {
        if (null != mRegisterView) {
            mRegisterView.hideProgress();
            mRegisterView.failToRegister();
        }

    }

    @Override
    public void onSuccess() {
        if (null != mRegisterView) {
            mRegisterView.hideProgress();
            mRegisterView.successToRegister();
        }

    }
}
