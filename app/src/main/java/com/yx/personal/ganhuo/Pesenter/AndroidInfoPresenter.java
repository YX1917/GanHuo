package com.yx.personal.ganhuo.pesenter;

import com.yx.personal.ganhuo.bean.AndroidInfoBean;
import com.yx.personal.ganhuo.fragment.FragmentTwoView;
import com.yx.personal.ganhuo.model.AndroidInfoModelImpl;

/**
 * Created by YX on 16/7/20.
 */
public class AndroidInfoPresenter {
    private AndroidInfoModelImpl mAndroidInfoModelImpl;
    private FragmentTwoView mFragmentTwoView;

    public AndroidInfoPresenter(FragmentTwoView mFragmentTwoView) {
        this.mFragmentTwoView = mFragmentTwoView;
        this.mAndroidInfoModelImpl = new AndroidInfoModelImpl();
    }

    public void show(int num, boolean isFirst) {
        mFragmentTwoView.setRefreshing(true);
        mAndroidInfoModelImpl.getAndroidInfo(num, isFirst, new AndroidInfoModelImpl.AndroidInfoOnListener() {
            @Override
            public void onSuccess(AndroidInfoBean androidInfoBean) {
                mFragmentTwoView.setRefreshing(false);
                mFragmentTwoView.showData(androidInfoBean);
            }
            @Override
            public void onFailure(Throwable e) {
                mFragmentTwoView.setRefreshing(false);
            }
        });
    }
}
