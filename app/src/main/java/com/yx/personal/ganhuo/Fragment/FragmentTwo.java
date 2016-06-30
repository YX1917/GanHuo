package com.yx.personal.ganhuo.Fragment;

import android.content.Intent;
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
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.Response;
import com.yx.personal.ganhuo.Activity.WebActivity;
import com.yx.personal.ganhuo.Adapter.AndroidAdapter;
import com.yx.personal.ganhuo.Bean.AndroidInfoBean;
import com.yx.personal.ganhuo.NetWork.ApiCall;
import com.yx.personal.ganhuo.NetWork.OkHttpCallback;
import com.yx.personal.ganhuo.R;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Created by YX on 16/4/15.
 */
public class FragmentTwo extends Fragment {
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshWidget;
    private GridLayoutManager mGridLayoutManager;
    private OkHttpCallback okHttpCallback;
    private AndroidAdapter androidAdapter;
    private AndroidInfoBean androidInfoBean;
    private int lastVisibleItem;
    private int page = 1;//初始请求页码
    private static Handler myHandler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_android, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initHandler();
        initOkHttpCall();
        initView(view);
    }


    private void initHandler() {
        myHandler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        mRecyclerView.setAdapter(androidAdapter);
                        mSwipeRefreshWidget.setRefreshing(false);
                        page++;
                        Log.e("TAG", "1信息" + page);
                        break;
                    case 2:
                        androidAdapter.notifyDataSetChanged();
                        mSwipeRefreshWidget.setRefreshing(false);
                        page++;
                        Log.e("TAG", "2信息" + page);
                        break;
                }
                super.handleMessage(msg);
            }
        };
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
                    ApiCall.RequestAndroid(page).enqueue(okHttpCallback);
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
                ApiCall.RequestAndroid(1).enqueue(okHttpCallback);
            }
        });
    }

    private void initOkHttpCall() {
        okHttpCallback = new OkHttpCallback(getActivity()) {
            @Override
            protected void handleApiSuccess(Response response) throws IOException {
                Gson gson = new Gson();
                AndroidInfoBean backBean = gson.fromJson(response.body().string(), AndroidInfoBean.class);
                if (androidInfoBean.getResults() == null) {
                    androidInfoBean = backBean;

                    androidAdapter = new AndroidAdapter(getActivity(), androidInfoBean.getResults());
                    myHandler.sendEmptyMessage(1);
                    setOnclickListener(androidAdapter);
                    Log.e("TAG", "welfareBean.getResults() == null"+backBean.toString());
                } else {
                    androidInfoBean.getResults().addAll(backBean.getResults());
                    myHandler.sendEmptyMessage(2);
                    setOnclickListener(androidAdapter);
                    Log.e("TAG", "welfareBean.getResults() != null");
                }

            }
        };
    }

    private void setOnclickListener(AndroidAdapter androidAdapter) {
        androidAdapter.setOnItemClickLitener(new AndroidAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra("URL",androidInfoBean.getResults().get(position).getUrl());
                getActivity().startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(getActivity(),"长按",Toast.LENGTH_SHORT).show();
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
