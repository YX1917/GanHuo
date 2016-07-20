package com.yx.personal.ganhuo.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
import com.yx.personal.ganhuo.NetWork.OkHttpCallback;
import com.yx.personal.ganhuo.NetWork.RetrofitManger;
import com.yx.personal.ganhuo.R;

import java.lang.reflect.Method;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by YX on 16/4/15.
 */
public class FragmentTwo extends Fragment {
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshWidget;
    private GridLayoutManager mGridLayoutManager;
    private AndroidAdapter androidAdapter;
    private AndroidInfoBean androidInfoBean;
    private int lastVisibleItem;
    private int page = 1;//初始请求页码

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_android, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }


    private void initView(View view) {
        androidInfoBean = new AndroidInfoBean();
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
                    mSwipeRefreshWidget.setRefreshing(true);
                    RetrofitManger.builder().getAndroidInfo(10,page)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Action1<AndroidInfoBean>() {
                                @Override
                                public void call(AndroidInfoBean androidInfoBean) {
                                    FragmentTwo.this.androidInfoBean.getResults().addAll(androidInfoBean.getResults());
                                    androidAdapter.notifyDataSetChanged();
                                    mSwipeRefreshWidget.setRefreshing(false);
                                    page++;
                                    setOnclickListener(androidAdapter);
                                }
                            });
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
//                ApiCall.RequestAndroid(1).enqueue(okHttpCallback);
               RetrofitManger.builder().getAndroidInfo(10, 1)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<AndroidInfoBean>() {
                            @Override
                            public void call(AndroidInfoBean androidInfoBean) {
                                FragmentTwo.this.androidInfoBean = androidInfoBean;
                                androidAdapter = new AndroidAdapter(getActivity(), androidInfoBean.getResults());
                                mRecyclerView.setAdapter(androidAdapter);
                                mSwipeRefreshWidget.setRefreshing(false);
                                page++;
                                setOnclickListener(androidAdapter);
                            }
                        });

            }

        });
    }


    private void setOnclickListener(AndroidAdapter androidAdapter) {
        androidAdapter.setOnItemClickLitener(new AndroidAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra("URL", androidInfoBean.getResults().get(position).getUrl());
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

}
