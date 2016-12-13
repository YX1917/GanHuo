package com.yx.personal.ganhuo.presenter;
import com.yx.personal.ganhuo.GetDataListener;
import com.yx.personal.ganhuo.bean.DailyPicksBean;
import com.yx.personal.ganhuo.contract.VideoInfoContract;
import com.yx.personal.ganhuo.model.VideoInfoModelImpl;

/**
* Created by YX on 2016/12/12
*/

public class VideoInfoPresenterImpl implements VideoInfoContract.Presenter{
    private VideoInfoContract.View view;
    private VideoInfoModelImpl videoInfoModel;

    public VideoInfoPresenterImpl(VideoInfoContract.View view) {
        videoInfoModel = new VideoInfoModelImpl();
        this.view = view;
    }

    @Override
    public void getDataFromNet() {
        view.setRefresh(true);
        videoInfoModel.getVideoInfo(new GetDataListener() {
            @Override
            public void success(Object object) {
                view.setRefresh(false);
                view.showData((DailyPicksBean)object);
            }

            @Override
            public void failed(Throwable throwable) {
                view.setRefresh(false);
            }
        });
    }
}