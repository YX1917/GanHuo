package com.yx.personal.ganhuo.fragment;

import com.yx.personal.ganhuo.bean.AndroidInfoBean;

/**
 * Created by YX on 16/7/21.
 */
public interface FragmentTwoView {
    void setRefreshing(boolean isRefresh);
    void showData(AndroidInfoBean androidInfoBean);
}
