package com.yx.personal.ganhuo;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;


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
        Fresco.initialize(this);

    }

    public static Context getContext(){
        return mContext;
    }
}
