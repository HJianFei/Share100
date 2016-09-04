package com.loosoo100.share100.view.base;

import android.support.v4.app.Fragment;

import rx.Subscription;

/**
 * 创建时间： 2016/9/2.
 * 作者：loosoo100
 * 功能描述：Fragment的基类
 */

public class BaseFragment extends Fragment {

    //RxJava的被观察者
    protected Subscription subscription;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //注销被观察者，防止内存泄漏
        unsubscribe();
    }

    /**
     * 注销被观察者
     */
    protected void unsubscribe() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
