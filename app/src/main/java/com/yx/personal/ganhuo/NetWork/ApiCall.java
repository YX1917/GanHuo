package com.yx.personal.ganhuo.NetWork;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;


/**
 * http请求封装 Created by YX on 2015/12/21.
 */
public class ApiCall {

    private static OkHttpClient client;

    private static OkHttpClient getInstance() {
        if (null == client) {
            synchronized (ApiCall.class) {
                if (client == null) {
                    client = new OkHttpClient();
                }
            }
        }
        return client;
    }


    public static Call RequestWelfare(int page) {

        Request request = new Request.Builder().url("http://gank.io/api/data/福利/10/" + page)
                .build();

        return getInstance().newCall(request);
    }

    public static Call RequestAndroid(int page) {

        Request request = new Request.Builder().url("http://gank.io/api/data/Android/10/" + page)
                .build();

        return getInstance().newCall(request);
    }

    public static Call RequestMainVideo() {

        Request request = new Request.Builder().url("http://baobab.wandoujia.com/api/v2/feed?num=2&udid=2708dd82544940279875e5e17622910789ea9491&vc=109&vn=2.1.1&deviceModel=Google%20Nexus%205%20-%206.0.0%20-%20API%2023%20-%201080x1920&first_channel=eyepetizer_xiaomi_market&last_channel=eyepetizer_xiaomi_market")
                .build();

        return getInstance().newCall(request);
    }


}
