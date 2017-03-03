package com.yx.personal.ganhuo.model;

import android.util.Log;

import com.yx.personal.ganhuo.GetDataListener;
import com.yx.personal.ganhuo.bean.DataInfoBean;
import com.yx.personal.ganhuo.contract.AndroidInfoContract;
import com.yx.personal.ganhuo.netWork.RetrofitManger;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.content.ContentValues.TAG;

/**
 * Created by YX on 2016/11/29
 */

public class AndroidInfoModelImpl implements AndroidInfoContract.Model {
    private DataInfoBean mDataInfoBean;


    @Override
    public void getAndroidInfo(final int page, final GetDataListener getDataListener) {
        //网络请求
        RetrofitManger.getInstance("http://gank.io/api/")
                .getAndroidInfo(10, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DataInfoBean>() {
                    @Override
                    public void onCompleted() {
                        Log.e(TAG, "onCompleted: " + "");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                        getDataListener.failed(e);
                    }

                    @Override
                    public void onNext(DataInfoBean dataInfoBean) {
                        Log.e(TAG, "onNext: " + "");
                        if (mDataInfoBean == null) {
                            mDataInfoBean = dataInfoBean;
                        } else {
                            if (page==1){
                                if (mDataInfoBean.getResults().subList(0,9).containsAll(dataInfoBean.getResults())){
                                    for (int i = dataInfoBean.getResults().size(); i<0; i--){
                                        if (!mDataInfoBean.getResults().contains(dataInfoBean.getResults().get(i))){
                                            mDataInfoBean.getResults().add(0, dataInfoBean.getResults().get(i));
                                        }
                                    }

                                }
                            }else {
                                mDataInfoBean.getResults().addAll(dataInfoBean.getResults());
                            }

                        }
                        getDataListener.success(mDataInfoBean);
                    }
                });
    }
}