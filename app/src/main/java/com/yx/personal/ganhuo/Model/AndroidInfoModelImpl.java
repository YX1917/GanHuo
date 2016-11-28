package com.yx.personal.ganhuo.model;

import com.yx.personal.ganhuo.bean.AndroidInfoBean;
import com.yx.personal.ganhuo.netWork.RetrofitManger;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by YX on 16/7/20.
 */
public class AndroidInfoModelImpl implements AndroidInfoModel {
    private int page = 1;
    private AndroidInfoBean mAndroidInfoBean;


    @Override
    public void getAndroidInfo(int number, boolean isFirst, final AndroidInfoOnListener androidInfoOnListener) {
        page = isFirst?(page=1):++page;
        RetrofitManger.builder().getAndroidInfo(number,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<AndroidInfoBean>() {
                    @Override
                    public void call(AndroidInfoBean androidInfoBean) {
                       if(mAndroidInfoBean==null){
                           mAndroidInfoBean = androidInfoBean;
                       }else{
                           mAndroidInfoBean.getResults().addAll(androidInfoBean.getResults());
                       }
                        androidInfoOnListener.onSuccess(mAndroidInfoBean);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        androidInfoOnListener.onFailure(throwable);
                    }
                });
    }


    /**
     *回调接口
     */
    public interface AndroidInfoOnListener{
        void onSuccess(AndroidInfoBean androidInfoBean);
        void onFailure(Throwable e);
    }
}
