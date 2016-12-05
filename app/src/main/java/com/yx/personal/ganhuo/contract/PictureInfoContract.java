package com.yx.personal.ganhuo.contract;

import com.yx.personal.ganhuo.GetDataListener;
import com.yx.personal.ganhuo.bean.DataInfoBean;

/**
 * Created by YX on 2016/12/5.
 */

public interface PictureInfoContract {
    
public interface View{
    void setRefresh(boolean isRefresh);
    void showData(DataInfoBean dataInfoBean);
    void showExceptionView(boolean isShow);
}

public interface Presenter{
    void getDataFromNet(int page);
}

public interface Model{
    void getPictureInfo(int page,GetDataListener getDataListener);
}


}