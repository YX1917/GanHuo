package com.yx.personal.ganhuo.presenter;

import com.yx.personal.ganhuo.GetDataListener;
import com.yx.personal.ganhuo.bean.DataInfoBean;
import com.yx.personal.ganhuo.contract.AndroidInfoContract;
import com.yx.personal.ganhuo.model.AndroidInfoModelImpl;

/**
 * Created by YX on 2016/11/29
 */

public class AndroidInfoPresenterImpl implements AndroidInfoContract.Presenter {
    private static final String TAG = AndroidInfoPresenterImpl.class.getSimpleName();
    private AndroidInfoModelImpl androidInfoModel;
    private AndroidInfoContract.View view;

    public AndroidInfoPresenterImpl(AndroidInfoContract.View view) {
        this.view = view;
        androidInfoModel = new AndroidInfoModelImpl();
    }

    @Override
    public void getDataFromNet(int page) {
        view.showExceptionView(false);
        view.setRefresh(page==1);
        androidInfoModel.getAndroidInfo(page, new GetDataListener() {
            @Override
            public void success(Object object) {
                view.setRefresh(false);
                view.showData((DataInfoBean) object);
            }

            @Override
            public void failed(Throwable throwable) {
                view.setRefresh(false);
                view.showExceptionView(true);
            }
        });
    }
}