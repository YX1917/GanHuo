package com.yx.personal.ganhuo.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.yx.personal.ganhuo.MyApplication;

/**
 * Created by YX on 16/7/14.
 */
public class NetUtil {
    public static boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) MyApplication.getContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info != null && info.isAvailable()){
            //do something
            //能联网
            return true;
        }else{
            //do something
            //不能联网
            return false;
        }


    }
}
