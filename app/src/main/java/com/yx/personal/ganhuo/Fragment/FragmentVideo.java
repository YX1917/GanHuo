package com.yx.personal.ganhuo.Fragment;

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
import android.widget.Button;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.internal.spdy.FrameReader;
import com.yx.personal.ganhuo.Adapter.VideoAdapter;
import com.yx.personal.ganhuo.Bean.DailyPicksBean;
import com.yx.personal.ganhuo.NetWork.ApiCall;
import com.yx.personal.ganhuo.NetWork.OkHttpCallback;
import com.yx.personal.ganhuo.R;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Created by YX on 16/6/29.
 */
public class FragmentVideo extends Fragment {
    private OkHttpCallback mOkHttpCallback;
    private RecyclerView mRecyclerView;
    private GridLayoutManager mGridLayoutManager;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private Handler mHandler;
    private VideoAdapter mVideoAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_video, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initOkHttpCall();
        initHandler();
    }

    private void initHandler() {
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 1:
                        mRecyclerView.setAdapter(mVideoAdapter);
                        break;
                }
            }
        };
    }

    private void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycle_video);
        mGridLayoutManager = new GridLayoutManager(getActivity(),1);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_video_refresh);
        setRefreshing(mSwipeRefreshLayout, true, true);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ApiCall.RequestMainVideo().enqueue(mOkHttpCallback);
            }
        });
    }

    private void initOkHttpCall() {
        mOkHttpCallback = new OkHttpCallback(getActivity()) {
            @Override
            protected void handleApiSuccess(Response response) throws IOException {
                Gson gson = new Gson();
                DailyPicksBean dailyPicksBean = gson.fromJson(response.body().string(), DailyPicksBean.class);
                Log.e("FragmentVideo",(dailyPicksBean.getIssueList().get(0).getItemList().get(0).getData().getItemList().get(0).getData().getImage()));
                mVideoAdapter = new VideoAdapter(getActivity(),dailyPicksBean);
                mHandler.sendEmptyMessage(1);
            }

            @Override
            public void onFailure(Request request, IOException e) {
                super.onFailure(request, e);
                Log.e("FragmentVideo","失败");
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
