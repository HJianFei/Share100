package com.loosoo100.share100.utils;

import com.loosoo100.share100.utils.Api.Api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.loosoo100.share100.constants.Constants.API_SERVER;

/**
 * 创建时间： 2016/9/2.
 * 作者：loosoo100
 * 功能描述：用Retrofit2.0+RxJava+OkHttp3.0封装网络加载工具类
 */

public class NetWorkUtils {
    private static Retrofit mRetrofit;
    private static OkHttpClient mOkHttpClient;
    private static Api api;

    /**
     * 获取Retrofit对象
     *
     * @return
     */
    public static Retrofit getRetrofit() {

        if (null == mRetrofit) {

            if (null == mOkHttpClient) {
                mOkHttpClient = OkHttpUtils.getmOkHttpClient();
            }

            //Retrofit2后使用build设计模式
            mRetrofit = new Retrofit.Builder()
                    //设置服务器路径
                    .baseUrl(API_SERVER)
                    //添加转化库，默认是Gson
                    .addConverterFactory(GsonConverterFactory.create())
                    //添加回调库，采用RxJava
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    //设置使用okhttp网络请求
                    .client(mOkHttpClient)
                    .build();
        }
        return mRetrofit;
    }

    /**
     * 返回Retrofit2 的实例对象
     *
     * @return
     */
    public static Api getApi() {
        if (api == null) {
            api = getRetrofit().create(Api.class);
        }
        return api;

    }
}
