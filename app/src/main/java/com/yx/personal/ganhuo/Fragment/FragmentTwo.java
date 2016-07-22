package com.yx.personal.ganhuo.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yx.personal.ganhuo.Activity.WebActivity;
import com.yx.personal.ganhuo.Adapter.AndroidAdapter;
import com.yx.personal.ganhuo.Bean.AndroidInfoBean;
import com.yx.personal.ganhuo.Pesenter.AndroidInfoPresenter;
import com.yx.personal.ganhuo.R;

import java.lang.reflect.Method;

/**
 * Created by YX on 16/4/15.
 */
public class FragmentTwo extends Fragment implements FragmentTwoView{
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshWidget;
    private GridLayoutManager mGridLayoutManager;
    private AndroidAdapter androidAdapter;
    private int lastVisibleItem;


    private AndroidInfoPresenter mAndroidInfoPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_android, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        mAndroidInfoPresenter = new AndroidInfoPresenter(this);
    }


    private void initView(View view) {
        mGridLayoutManager = new GridLayoutManager(getActivity(), 1);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycle_android);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        //上拉加载更多，滑动监听，当滑动到最后一项可见项时请求下一页数据
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView,
                                             int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == androidAdapter.getItemCount()) {
                    mAndroidInfoPresenter.show(10, false);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = mGridLayoutManager.findLastVisibleItemPosition();
            }
        });


        mSwipeRefreshWidget = (SwipeRefreshLayout) view.findViewById(R.id.swipe_android_refresh);
        setRefreshing(mSwipeRefreshWidget, true, true);

        /**
         * 下拉刷新
         */
        mSwipeRefreshWidget.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mAndroidInfoPresenter.show(10, true);
            }

        });
    }


    private void setOnclickListener(AndroidAdapter androidAdapter) {
        androidAdapter.setOnItemClickLitener(new AndroidAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), WebActivity.class);
                getActivity().startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(getActivity(), "长按", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 利用反射使SwipeRefreshLayout进入界面就处于刷新状态
     *
     * @param refreshLayout
     * @param refreshing
     * @param notify
     */
    public static void setRefreshing(SwipeRefreshLayout refreshLayout, boolean refreshing, boolean notify) {
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
    public void setRefreshing(boolean isRefresh) {
        mSwipeRefreshWidget.setRefreshing(isRefresh);
    }

    @Override
    public void showData(AndroidInfoBean androidInfoBean) {
        if (androidInfoBean.getResults().size() == 10) {
            androidAdapter = new AndroidAdapter(getActivity(), androidInfoBean.getResults());
            mRecyclerView.setAdapter(androidAdapter);
        } else {
            androidAdapter.notifyDataSetChanged();
        }
        setOnclickListener(androidAdapter);
    }

}
