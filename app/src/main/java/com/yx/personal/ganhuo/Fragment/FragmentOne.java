package com.yx.personal.ganhuo.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;
import com.squareup.okhttp.Response;
import com.yx.personal.ganhuo.Adapter.FragmentAdapter;
import com.yx.personal.ganhuo.Bean.WelfareBean;
import com.yx.personal.ganhuo.NetWork.ApiCall;
import com.yx.personal.ganhuo.NetWork.OkHttpCallback;
import com.yx.personal.ganhuo.R;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by YX on 16/4/12.
 */
public class FragmentOne extends Fragment {
    private RecyclerView mRecyclerView;
    private WelfareBean welfareBean = new WelfareBean();
    private OkHttpCallback okHttpCallback;
    private FragmentAdapter fragmentAdapter;
    private SwipeRefreshLayout mSwipeRefreshWidget;
    private GridLayoutManager mGridLayoutManager;
    private int lastVisibleItem;
    private int page = 1;//初始请求页码


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fuli, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Fresco.initialize(getActivity());
        initOkHttpCall();
        initView(view);
        ApiCall.RequestWelfare(page).enqueue(okHttpCallback);


    }

    /**
     * 初始化控件
     * @param view
     */
    private void initView(View view) {
        welfareBean = new WelfareBean();
        mGridLayoutManager = new GridLayoutManager(getActivity(), 1);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.id_recyclerview);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        //上拉加载更多，滑动监听，当滑动到最后一项可见项时请求下一页数据
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView,
                                             int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == fragmentAdapter.getItemCount()) {
                    mSwipeRefreshWidget.setRefreshing(true);
                    ApiCall.RequestWelfare(page).enqueue(okHttpCallback);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = mGridLayoutManager.findLastVisibleItemPosition();
            }
        });

        mSwipeRefreshWidget = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_widget);
        setRefreshing(mSwipeRefreshWidget, true, true);

        /**
         * 下拉刷新
         */
        mSwipeRefreshWidget.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ApiCall.RequestWelfare(1).enqueue(okHttpCallback);
            }
        });

    }


    private void initOkHttpCall() {
        okHttpCallback = new OkHttpCallback(getActivity()) {
            @Override
            protected void handleApiSuccess(Response response) throws IOException {
                Gson gson = new Gson();
                WelfareBean backBean = gson.fromJson(response.body().string(), WelfareBean.class);
                if (welfareBean.getResults() == null) {
                    welfareBean = backBean;
                    fragmentAdapter = new FragmentAdapter(getActivity(), welfareBean.getResults());
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mRecyclerView.setAdapter(fragmentAdapter);
                            mSwipeRefreshWidget.setRefreshing(false);
                            page++;
                        }
                    });
                } else {
                    welfareBean.getResults().addAll(backBean.getResults());
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            fragmentAdapter.notifyDataSetChanged();
                            mSwipeRefreshWidget.setRefreshing(false);
                            page++;
                        }
                    });
                }

            }
        };
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
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }


}
