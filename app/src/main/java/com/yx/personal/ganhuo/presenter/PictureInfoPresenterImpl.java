package com.yx.personal.ganhuo.presenter;
import com.yx.personal.ganhuo.GetDataListener;
import com.yx.personal.ganhuo.bean.DataInfoBean;
import com.yx.personal.ganhuo.contract.PictureInfoContract;
import com.yx.personal.ganhuo.model.PictureInfoModelImpl;

/**
* Created by YX on 2016/12/05
*/

public class PictureInfoPresenterImpl implements PictureInfoContract.Presenter{
    private PictureInfoModelImpl pictureInfoModel;
    private PictureInfoContract.View view;

    public PictureInfoPresenterImpl(PictureInfoContract.View view) {
        pictureInfoModel = new PictureInfoModelImpl();
        this.view = view;
    }

    @Override
    public void getDataFromNet(int page) {
        view.showExceptionView(false);
        view.setRefresh(page==1);
        pictureInfoModel.getPictureInfo(page, new GetDataListener() {
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