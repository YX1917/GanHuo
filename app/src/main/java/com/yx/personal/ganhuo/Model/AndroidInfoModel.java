package com.yx.personal.ganhuo.Model;

/**
 * Created by YX on 16/7/20.
 */
public interface AndroidInfoModel {
    void getAndroidInfo(int number, boolean isFirst, AndroidInfoModelImpl.AndroidInfoOnListener androidInfoOnListener);
}
