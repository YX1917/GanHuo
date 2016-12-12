package com.yx.personal.ganhuo.contract;

import com.yx.personal.ganhuo.GetDataListener;
import com.yx.personal.ganhuo.bean.DailyPicksBean;

/**
 * Created by YX on 2016/12/12.
 */

public class VideoInfoContract {
public interface View{
    void setRefresh(boolean isRefresh);
    void showData(DailyPicksBean dailyPicksBean);
}

public interface Presenter{
   void getDataFromNet();
}

public interface Model{
   void getVideoInfo(GetDataListener getDataListener);
}


}