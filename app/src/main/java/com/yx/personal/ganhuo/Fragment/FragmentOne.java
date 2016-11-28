package com.yx.personal.ganhuo.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.yx.personal.ganhuo.adapter.FuLiAdapter;
import com.yx.personal.ganhuo.bean.WelfareBean;
import com.yx.personal.ganhuo.netWork.ApiCall;
import com.yx.personal.ganhuo.netWork.OkHttpCallback;
import com.yx.personal.ganhuo.R;

import java.io.IOException;
import java.lang.reflect.Method;

import okhttp3.Response;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by YX on 16/4/12.
 */
public class FragmentOne extends Fragment {
    private RecyclerView mRecyclerView;
    private WelfareBean welfareBean = new WelfareBean();
    private OkHttpCallback okHttpCallback;
    private FuLiAdapter  fuLiAdapter;
    private SwipeRefreshLayout mSwipeRefreshWidget;
    private GridLayoutManager mGridLayoutManager;
    private int lastVisibleItem;
    private int page = 1;//初始请求页码

    private static Handler myHandler;
    Observer<String> observer = new Observer<String>() {
        @Override
        public void onNext(String s) {
            Log.e("TAG", "Item: " + s);
        }

        @Override
        public void onCompleted() {
            Log.e("TAG", "Completed!");
        }

        @Override
        public void onError(Throwable e) {
            Log.e("TAG", "Error!");
        }
    };

    Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
        @Override
        public void call(Subscriber<? super String> subscriber) {
            subscriber.onNext("Hello");
            subscriber.onNext("Hi");
            subscriber.onNext("Aloha");
            subscriber.onCompleted();
        }
    });



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fuli, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initHandler();
        initOkHttpCall();
        initView(view);
        observable.subscribe(observer);



    }


    private void initHandler() {
        myHandler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        mRecyclerView.setAdapter(fuLiAdapter);
                        mSwipeRefreshWidget.setRefreshing(false);
                        page++;
                        Log.e("TAG", "1信息" + page);
                        break;
                    case 2:
                        fuLiAdapter.notifyDataSetChanged();
                        mSwipeRefreshWidget.setRefreshing(false);
                        page++;
                        Log.e("TAG", "2信息" + page);
                        break;
                }
                super.handleMessage(msg);
            }
        };
    }

    /**
     * 初始化控件
     *
     * @param view
     */
    private void initView(View view) {
        welfareBean = new WelfareBean();
        mGridLayoutManager = new GridLayoutManager(getActivity(), 1);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycle_fuLi);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        //上拉加载更多，滑动监听，当滑动到最后一项可见项时请求下一页数据
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView,
                                             int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == fuLiAdapter.getItemCount()) {
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

        mSwipeRefreshWidget = (SwipeRefreshLayout) view.findViewById(R.id.swipe_fuLi_refresh);
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
                    fuLiAdapter = new FuLiAdapter(getActivity(), welfareBean.getResults());
//                    myHandler.sendEmptyMessage(1);
                    Observable.just("Hello")
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<String>() {
                                @Override
                                public void onCompleted() {
                                    Log.e("TAG", "完成");
                                }

                                @Override
                                public void onError(Throwable e) {

                                }

                                @Override
                                public void onNext(String s) {
                                    mRecyclerView.setAdapter(fuLiAdapter);
                                    mSwipeRefreshWidget.setRefreshing(false);
                                    page++;
                                    Log.e("TAG", "1信息" + page);
                                }
                            });
                    Log.e("TAG", "welfareBean.getResults() == null");
                } else {
                    welfareBean.getResults().addAll(backBean.getResults());
                    myHandler.sendEmptyMessage(2);
                    Log.e("TAG", "welfareBean.getResults() != null");
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
