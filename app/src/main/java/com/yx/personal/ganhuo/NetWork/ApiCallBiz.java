package com.yx.personal.ganhuo.netWork;

import com.yx.personal.ganhuo.bean.DailyPicksBean;
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

    @GET("v2/feed?num=2&udid=2708dd82544940279875e5e17622910789ea9491&vc=109&vn=2.1.1&deviceModel=Google%20Nexus%205%20-%206.0.0%20-%20API%2023%20-%201080x1920&first_channel=eyepetizer_xiaomi_market&last_channel=eyepetizer_xiaomi_market")
    Observable<DailyPicksBean> getVideoInfo();


}
