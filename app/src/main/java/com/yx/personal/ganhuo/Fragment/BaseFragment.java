package com.yx.personal.ganhuo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by YX on 2016/12/6.
 */

public abstract class BaseFragment extends Fragment {
    private static final String TAG = BaseFragment.class.getSimpleName();
    /** Fragment当前状态是否可见 */
    private boolean isViewCreated;
    private boolean isLoadDataCompleted;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewCreated= true;
        init();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.e(TAG, "setUserVisibleHint: " +"isVisibleToUser:" +isVisibleToUser+"  isViewCreated:"+isViewCreated+"  isVisibleToUser"+isVisibleToUser);
        if (isVisibleToUser&&isViewCreated&&!isLoadDataCompleted) {
            loadData();
            isLoadDataCompleted = true;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getUserVisibleHint()) {
            loadData();
        }
    }

    /**
     * 延迟加载
     * 子类必须重写此方法
     */
    protected abstract void loadData();


    //c初始化控件
    protected abstract void init();


}
