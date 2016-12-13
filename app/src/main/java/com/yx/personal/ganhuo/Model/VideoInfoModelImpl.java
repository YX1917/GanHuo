package com.yx.personal.ganhuo.model;

import com.yx.personal.ganhuo.GetDataListener;
import com.yx.personal.ganhuo.bean.DailyPicksBean;
import com.yx.personal.ganhuo.contract.VideoInfoContract;
import com.yx.personal.ganhuo.netWork.RetrofitManger;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
* Created by YX on 2016/12/12
*/

public class VideoInfoModelImpl implements VideoInfoContract.Model{


    @Override
    public void getVideoInfo(final GetDataListener getDataListener) {
        RetrofitManger.getInstance("http://baobab.wandoujia.com/api/")
                .getVideoInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<DailyPicksBean>() {
                    @Override
                    public void call(DailyPicksBean dailyPicksBean) {
                        getDataListener.success(dailyPicksBean);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        getDataListener.failed(throwable);
                    }
                });

    }
}