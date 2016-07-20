package com.yx.personal.ganhuo.Utils;

import android.content.Context;
import android.net.ConnectivityManager;

import com.yx.personal.ganhuo.MyApplication;

/**
 * Created by YX on 16/7/14.
 */
public class NetUtil {
    public static boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) MyApplication.getContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo().isAvailable();


    }
}
