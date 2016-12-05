package com.yx.personal.ganhuo.netWork;

import com.yx.personal.ganhuo.bean.DataInfoBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by YX on 16/7/14.
 */
public interface ApiCallBiz {
    @GET("data/Android/{number}/{page}")
    Observable<DataInfoBean> getAndroidInfo(@Path("number") int number, @Path("page") int page);

    @GET("data/iOS/{number}/{page}")
    Observable<DataInfoBean> getIosInfo(@Path("number") int number, @Path("page") int page);

    @GET("data/福利/{number}/{page}")
    Observable<DataInfoBean> getPictureInfo(@Path("number") int number, @Path("page") int page);
}
