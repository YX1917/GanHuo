package com.yx.personal.ganhuo;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;


/**
 * @author YX.
 * @time 16/4/12 上午10:16.
 */
public class MyApplication extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        LeakCanary.install(this);

    }

    private static Context getContext(){
        return mContext;
    }
}
