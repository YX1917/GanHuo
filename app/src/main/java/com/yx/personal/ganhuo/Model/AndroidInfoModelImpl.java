package com.yx.personal.ganhuo.model;

import com.yx.personal.ganhuo.GetDataListener;
import com.yx.personal.ganhuo.bean.AndroidInfoBean;
import com.yx.personal.ganhuo.contract.AndroidInfoContract;
import com.yx.personal.ganhuo.netWork.RetrofitManger;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by YX on 2016/11/29
 */

public class AndroidInfoModelImpl implements AndroidInfoContract.Model {
    private AndroidInfoBean mAndroidInfoBean;


    @Override
    public void getAndroidInfo(int page, final GetDataListener getDataListener) {
        //网络请求
        RetrofitManger.builder()
                .getAndroidInfo(10, page).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<AndroidInfoBean>() {
                    @Override
                    public void call(AndroidInfoBean androidInfoBean) {
                        if (mAndroidInfoBean == null) {
                            mAndroidInfoBean = androidInfoBean;
                        } else {
                            mAndroidInfoBean.getResults().addAll(androidInfoBean.getResults());
                        }
                        getDataListener.success(mAndroidInfoBean);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        getDataListener.failed(throwable);
                    }
                });
    }
}