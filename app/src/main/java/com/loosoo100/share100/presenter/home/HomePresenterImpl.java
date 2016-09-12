package com.loosoo100.share100.presenter.home;

import com.loosoo100.share100.model.home.HomeIndicator;
import com.loosoo100.share100.model.home.HomeIndicatorImpl;
import com.loosoo100.share100.view.home.HomeView;

import java.util.List;

/**
 * 创建时间： 2016/9/2.
 * 作者：loosoo100
 * 功能描述：
 */

public class HomePresenterImpl implements HomePresenter, HomeIndicator.onHomeFinishListener {
    private HomeView mHomeView;
    private HomeIndicator mHomeIndicator;

    public HomePresenterImpl(HomeView homeView) {
        this.mHomeView = homeView;
        mHomeIndicator = new HomeIndicatorImpl();
    }

    @Override
    public void firstInitHomeView() {
        if (null != mHomeView) {
            mHomeView.showProgress();
        }
        mHomeIndicator.firstInitHomeView(this);

    }

    @Override
    public void refreshHomeView() {
        if (null != mHomeView) {
            mHomeView.showProgress();
        }
        mHomeIndicator.refreshHomeView(this);
    }

    @Override
    public void loadMoreHomeView() {
        if (null != mHomeView) {
            mHomeView.showProgress();
        }
        mHomeIndicator.loadMoreHomeView(this);

    }

    @Override
    public void onHomeViewDestroy() {

        if (null != mHomeView) {
            mHomeView = null;
        }
    }

    @Override
    public void onError() {
        if (null != mHomeView) {
            mHomeView.hideProgress();
        }
        mHomeView.showError();
    }

    @Override
    public void onFirstSuccess(List<String> object3) {
        mHomeView.initHomeViewPager();
        mHomeView.initHomeRecommend();
        mHomeView.initHomeRecyclerView(object3);
        if (null != mHomeView) {
            mHomeView.hideProgress();
        }

    }

    @Override
    public void onRefreshSuccess(List<String> object) {
        mHomeView.initHomeRecyclerView(object);
        if (null != mHomeView) {
            mHomeView.hideProgress();
        }

    }

    @Override
    public void onLoadMoreSuccess(List<String> object) {
        mHomeView.initHomeRecyclerView(object);
        if (null != mHomeView) {
            mHomeView.hideProgress();
        }

    }

}
