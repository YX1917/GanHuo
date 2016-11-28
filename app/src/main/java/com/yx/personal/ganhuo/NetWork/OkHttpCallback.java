package com.yx.personal.ganhuo.netWork;

import android.app.Activity;
import android.util.Log;



import java.io.IOException;
import java.net.SocketTimeoutException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;


/**
 * 请求回调
 * @author yx
 */
public abstract class OkHttpCallback implements Callback {

    private String TAG = OkHttpCallback.class.getName();
    private Activity mActivity;

    public OkHttpCallback(Activity activity) {
        this.mActivity = activity;
    }

    // [+] Callback of OkHttp

    @Override
    public void onFailure(final Call call, final IOException e) {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                handleApiFailure(call.request(), e);
            }
        });
    }


    @Override
    public void onResponse(final Call call, final Response response) throws IOException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    handleApiSuccess(response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    // [-] Callback of OkHttp

    protected void handlePreResponse(Response response) {}

    protected abstract void handleApiSuccess(Response response) throws IOException;

    protected void handleApiFailure(Request request, IOException e) {
        if (e instanceof SocketTimeoutException) {
            Log.e(TAG, "失败3");
        } else {
            Log.e(TAG, "失败4");
        }
    }

}
