package com.loosoo100.share100.utils;

import com.loosoo100.share100.application.BaseApplication;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * 创建时间： 2016/9/2.
 * 作者：loosoo100
 * 功能描述：okhttp网络请求类
 */

public class OkHttpUtils {

    private static OkHttpClient mOkHttpClient;
    //设置缓存路径
    private static File cacheDirectory = new File(BaseApplication.getInstance().getApplicationContext().getFilesDir().getAbsolutePath(), "share100");
    private static Cache cache = new Cache(cacheDirectory, 10 * 1024 * 1024);

    /**
     * 获取OkHttpClient对象
     *
     * @return
     */
    public static OkHttpClient getmOkHttpClient() {
        if (null == mOkHttpClient) {
            //同样okhttp3后也使用build设计模式
            mOkHttpClient = new OkHttpClient.Builder()
                    //设置请求读写的超时时间
                    .connectTimeout(3, TimeUnit.SECONDS)
                    .writeTimeout(3, TimeUnit.SECONDS)
                    .readTimeout(3, TimeUnit.SECONDS)
                    .cache(cache)
                    .build();
        }
        return mOkHttpClient;
    }

}
