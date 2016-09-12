package com.loosoo100.share100.model.home;

import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建时间： 2016/9/2.
 * 作者：loosoo100
 * 功能描述：
 */

public class HomeIndicatorImpl implements HomeIndicator {

    @Override
    public void firstInitHomeView(final onHomeFinishListener listener) {
        final List<String> lists = new ArrayList<>();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    lists.add("item:" + i);
                }
                listener.onFirstSuccess(lists);
            }
        }, 2000);

    }

    @Override
    public void refreshHomeView(final onHomeFinishListener listener) {
        final List<String> lists = new ArrayList<>();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    lists.add("item:" + i);
                }
                listener.onRefreshSuccess(lists);
            }
        }, 2000);

    }

    @Override
    public void loadMoreHomeView(final onHomeFinishListener listener) {
        final List<String> lists = new ArrayList<>();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    lists.add("item:" + i);
                }
                listener.onLoadMoreSuccess(lists);
            }
        }, 2000);

    }
}
