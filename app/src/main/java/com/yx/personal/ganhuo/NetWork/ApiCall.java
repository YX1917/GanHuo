package com.yx.personal.ganhuo.NetWork;

import android.util.Log;

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


	public static Call RequestWelfare() {

		Request request = new Request.Builder().url("http://gank.io/api/data/福利/10/1")
				.build();

		return getInstance().newCall(request);
	}


}
