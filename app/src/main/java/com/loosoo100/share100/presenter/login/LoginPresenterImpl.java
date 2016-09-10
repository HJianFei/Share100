package com.loosoo100.share100.presenter.login;

import com.loosoo100.share100.model.login.LoginIndicator;
import com.loosoo100.share100.model.login.LoginIndicatorImpl;
import com.loosoo100.share100.view.login.LoginView;

/**
 * 创建时间： 2016/9/3.
 * 作者：loosoo100
 * 功能描述：负责完成LoginActivity页面与LoginIndicatorImpl业务逻辑间的交互接口实现
 */

public class LoginPresenterImpl implements LoginPresenter, LoginIndicator.OnLoginFinishListener {

    private LoginView mLoginView;
    private LoginIndicator mLoginIndicator;

    public LoginPresenterImpl(LoginView loginView) {
        mLoginView = loginView;
        mLoginIndicator = new LoginIndicatorImpl();
    }

    @Override
    public void validateCredentials(String userName, String userPassword) {
        if (null != mLoginView) {
            mLoginView.showProgress();
        }
        mLoginIndicator.login(userName, userPassword, this);

    }

    @Override
    public void onDestroy() {
        if (null != mLoginView) {
            mLoginView = null;
        }

    }


    @Override
    public void onError() {
        if (null != mLoginView) {
            mLoginView.failToLogin();
//            mLoginView.hideProgress();
        }
    }

    @Override
    public void onSuccess() {
        if (null != mLoginView) {
            mLoginView.hideProgress();
            mLoginView.navicateToHome();
        }

    }
}
