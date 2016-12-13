package com.yx.personal.ganhuo.model;
import com.yx.personal.ganhuo.GetDataListener;
import com.yx.personal.ganhuo.bean.DataInfoBean;
import com.yx.personal.ganhuo.contract.IosInfoContract;
import com.yx.personal.ganhuo.netWork.RetrofitManger;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
* Created by YX on 2016/12/05
*/

public class IosInfoModelImpl implements IosInfoContract.Model{
    private DataInfoBean mDataInfoBean;

    @Override
    public void getIosInfo(final int page, final GetDataListener getDataListener) {
        //网络请求
        RetrofitManger.getInstance("http://gank.io/api/")
                .getIosInfo(10, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<DataInfoBean>() {
                    @Override
                    public void call(DataInfoBean dataInfoBean) {
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
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        getDataListener.failed(throwable);
                    }
                });
    }

}