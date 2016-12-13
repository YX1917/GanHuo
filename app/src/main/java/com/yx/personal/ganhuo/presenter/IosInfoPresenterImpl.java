package com.yx.personal.ganhuo.presenter;
import com.yx.personal.ganhuo.GetDataListener;
import com.yx.personal.ganhuo.bean.DataInfoBean;
import com.yx.personal.ganhuo.contract.IosInfoContract;
import com.yx.personal.ganhuo.model.IosInfoModelImpl;

/**
* Created by YX on 2016/12/05
*/

public class IosInfoPresenterImpl implements IosInfoContract.Presenter{
    private IosInfoModelImpl iosInfoModel;
    private IosInfoContract.View view;

    public IosInfoPresenterImpl(IosInfoContract.View view) {
        iosInfoModel = new IosInfoModelImpl();
        this.view = view;
    }

    @Override
    public void getDataFromNet(int page) {
        view.showExceptionView(false);
        view.setRefresh(page==1);
        iosInfoModel.getIosInfo(page, new GetDataListener() {
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