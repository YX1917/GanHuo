package com.yx.personal.ganhuo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yx.personal.ganhuo.R;
import com.yx.personal.ganhuo.contract.AndroidInfoContract;
import com.yx.personal.ganhuo.cudtomView.ExceptionView;
import com.yx.personal.ganhuo.presenter.AndroidInfoPresenterImpl;

import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by YX on 2016/11/28.
 */

public class AndroidFragment extends Fragment implements AndroidInfoContract.View {
    private View view;
    private AndroidInfoPresenterImpl androidInfoPresenter;
    private LinearLayoutManager layoutManager;
    private int lastVisibleItem;

    @BindView(R.id.recycle_android)
    RecyclerView recycleAndroid;
    @BindView(R.id.swipe_android_refresh)
    SwipeRefreshLayout swipeAndroidRefresh;
    @BindView(R.id.epv_android)
    ExceptionView epvAndroid;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_android, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initView();
        androidInfoPresenter = new AndroidInfoPresenterImpl(this);
    }

    private void initView() {

        layoutManager = new LinearLayoutManager(getActivity());
        recycleAndroid.setLayoutManager(layoutManager);
        recycleAndroid.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                }
            }
        });


        swipeAndroidRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                androidInfoPresenter.getDataFromNet(1);
            }
        });

//        setRefreshing(swipeAndroidRefresh,true,true);
        swipeAndroidRefresh.setRefreshing(true);
    }

    /**
     * 利用反射使SwipeRefreshLayout进入界面就处于刷新状态
     *
     * @param refreshLayout
     * @param refreshing
     * @param notify
     */
    public void setRefreshing(SwipeRefreshLayout refreshLayout, boolean refreshing, boolean notify) {
        Class<? extends SwipeRefreshLayout> refreshLayoutClass = refreshLayout.getClass();
        if (refreshLayoutClass != null) {

            try {
                Method setRefreshing = refreshLayoutClass.getDeclaredMethod("setRefreshing", boolean.class, boolean.class);
                setRefreshing.setAccessible(true);
                setRefreshing.invoke(refreshLayout, refreshing, notify);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setRefresh(boolean isRefresh) {

    }

    @Override
    public void showData(Object o) {

    }
}
