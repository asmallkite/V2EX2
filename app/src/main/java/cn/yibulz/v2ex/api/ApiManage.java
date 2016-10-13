package cn.yibulz.v2ex.api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 10648 on 2016/10/13 0013.
 *
 */

public class ApiManage {
    private static ApiManage mApiManage;
    public static ApiManage getInstance() {
        //因为可能会有多个线程一起进入同步块外的 if，
        // 如果在同步块内不进行二次检验的话就会生成多个实例了
        if (mApiManage == null) {
            synchronized (ApiManage.class) {
                if (mApiManage == null) {
                    mApiManage = new ApiManage();
                }
            }
        }
        return mApiManage;
    }

    private HotLatestApi mHotLatestApi;
    public HotLatestApi getHotLatestApi() {
        if (mHotLatestApi == null) {
            synchronized (new Object()) {
                if (mHotLatestApi == null) {
                    mHotLatestApi = new Retrofit.Builder()
                            .baseUrl("https://www.v2ex.com")
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                            .create(HotLatestApi.class);
                }
            }
        }
        return mHotLatestApi;
    }
}
