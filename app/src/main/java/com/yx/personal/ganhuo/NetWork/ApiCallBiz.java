package com.yx.personal.ganhuo.netWork;

import com.yx.personal.ganhuo.bean.AndroidInfoBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by YX on 16/7/14.
 */
public interface ApiCallBiz {
    @GET("data/Android/{number}/{page}")
    Observable<AndroidInfoBean> getAndroidInfo(@Path("number") int number, @Path("page") int page);
}
