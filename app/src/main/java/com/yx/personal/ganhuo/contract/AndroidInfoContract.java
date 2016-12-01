package com.yx.personal.ganhuo.contract;

import com.yx.personal.ganhuo.GetDataListener;
import com.yx.personal.ganhuo.bean.AndroidInfoBean;

/**
 * Created by YX on 2016/11/29.
 */

public class AndroidInfoContract {
public interface View{
    void setRefresh(boolean isRefresh);
    void showData(AndroidInfoBean androidInfoBean);
    void showExceptionView(boolean isShow);

}

public interface Presenter{
    void getDataFromNet(int page);
}

public interface Model{
    void getAndroidInfo(int page,GetDataListener getDataListener);
}


}