package com.yx.personal.ganhuo.contract;

import com.yx.personal.ganhuo.GetDataListener;

/**
 * Created by YX on 2016/11/29.
 */

public class AndroidInfoContract {
public interface View{
    void setRefresh(boolean isRefresh);
    void showData(Object o);

}

public interface Presenter{
    void getDataFromNet(int page);
}

public interface Model{
    void getAndroidInfo(int page,GetDataListener getDataListener);
}


}