package com.yx.personal.ganhuo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yx.personal.ganhuo.R;
import com.yx.personal.ganhuo.activity.WebActivity;
import com.yx.personal.ganhuo.bean.DataInfoBean;
import com.yx.personal.ganhuo.contract.AndroidInfoContract;
import com.yx.personal.ganhuo.presenter.AndroidInfoPresenterImpl;
import com.yx.personal.ganhuo.recyclerview.CommonAdapter;
import com.yx.personal.ganhuo.recyclerview.MultiItemTypeAdapter;
import com.yx.personal.ganhuo.recyclerview.base.ViewHolder;
import com.yx.personal.ganhuo.recyclerview.wrapper.HeaderAndFooterWrapper;
import com.yx.personal.ganhuo.recyclerview.wrapper.LoadMoreWrapper;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by YX on 2016/11/28.
 */

public class AndroidFragment extends Fragment implements AndroidInfoContract.View {
    private static final String TAG = AndroidFragment.class.getSimpleName();
    private View view;
    private AndroidInfoPresenterImpl androidInfoPresenter;
    private LinearLayoutManager layoutManager;
    private int firstitem;

    private CommonAdapter<DataInfoBean.ResultsBean> commonAdapter;
    private LoadMoreWrapper mLoadMoreWrapper;
    private HeaderAndFooterWrapper mHeaderAndFooterWrapper;
    private DataInfoBean dataInfoBeen;

    @BindView(R.id.recycle_android)
    RecyclerView recycleAndroid;
    @BindView(R.id.swipe_android_refresh)
    SwipeRefreshLayout swipeAndroidRefresh;
//    @BindView(R.id.epv_android)
//    ExceptionView epvAndroid;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_android, container, false);
        ButterKnife.bind(this, view);
        Log.e(TAG, "onCreateView: " + "");
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable final Bundle savedInstanceState) {
        init();
        initView();
        if (dataInfoBeen!=null) {
            commonAdapter = new CommonAdapter<DataInfoBean.ResultsBean>(getContext(), R.layout.item_android,dataInfoBeen.getResults()) {
                @Override
                protected void convert(ViewHolder holder, DataInfoBean.ResultsBean resultsBean, int position) {
                    holder.setText(R.id.tv_android_title, resultsBean.getDesc());
                    holder.setText(R.id.tv_android_who, resultsBean.getWho());
                    holder.setText(R.id.tv_android_date, resultsBean.getCreatedAt().split("T")[0]);
                }
            };

            commonAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                    Intent intent = new Intent(getActivity(), WebActivity.class);
                    intent.putExtra("URL", dataInfoBeen.getResults().get(position).getUrl());
                    getActivity().startActivity(intent);
                }

                @Override
                public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                    return false;
                }
            });

            mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(commonAdapter);
            mLoadMoreWrapper = new LoadMoreWrapper(mHeaderAndFooterWrapper);
            mLoadMoreWrapper.setLoadMoreView(R.layout.default_loading);

            mLoadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    Log.e(TAG, "onLoadMoreRequested: " + mLoadMoreWrapper.getItemCount());
                    androidInfoPresenter.getDataFromNet((mLoadMoreWrapper.getItemCount() - 1) / 10 + 1);
                }
            });
            recycleAndroid.setAdapter(mLoadMoreWrapper);
            recycleAndroid.scrollToPosition(firstitem);
        }else{
            androidInfoPresenter.getDataFromNet(1);
        }

    }

    private void init() {
        androidInfoPresenter = new AndroidInfoPresenterImpl(this);

    }

    private void initView() {

        layoutManager = new LinearLayoutManager(getActivity());
        recycleAndroid.setLayoutManager(layoutManager);

        swipeAndroidRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                androidInfoPresenter.getDataFromNet(1);
            }
        });

//        epvAndroid.setAction(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                androidInfoPresenter.getDataFromNet((mLoadMoreWrapper.getItemCount()-1)/10+1);
//
//            }
//        });
    }

    @Override
    public void setRefresh(boolean isRefresh) {
        swipeAndroidRefresh.setRefreshing(isRefresh);
    }

    @Override
    public void showData(final DataInfoBean dataInfoBean) {
        this.dataInfoBeen = dataInfoBean;
        if (commonAdapter == null) {
            commonAdapter = new CommonAdapter<DataInfoBean.ResultsBean>(getContext(), R.layout.item_android, dataInfoBean.getResults()) {
                @Override
                protected void convert(ViewHolder holder, DataInfoBean.ResultsBean resultsBean, int position) {
                    holder.setText(R.id.tv_android_title, resultsBean.getDesc());
                    holder.setText(R.id.tv_android_who, resultsBean.getWho());
                    holder.setText(R.id.tv_android_date, resultsBean.getCreatedAt().split("T")[0]);
                }
            };

            commonAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                    Intent intent = new Intent(getActivity(), WebActivity.class);
                    intent.putExtra("URL", dataInfoBean.getResults().get(position).getUrl());
                    getActivity().startActivity(intent);
                }

                @Override
                public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                    return false;
                }
            });

            mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(commonAdapter);
            mLoadMoreWrapper = new LoadMoreWrapper(mHeaderAndFooterWrapper);
            mLoadMoreWrapper.setLoadMoreView(R.layout.default_loading);

            mLoadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    Log.e(TAG, "onLoadMoreRequested: " + mLoadMoreWrapper.getItemCount());
                    androidInfoPresenter.getDataFromNet((mLoadMoreWrapper.getItemCount() - 1) / 10 + 1);
                }
            });
            recycleAndroid.setAdapter(mLoadMoreWrapper);
            firstitem = layoutManager.findFirstVisibleItemPosition();

        } else {
            mLoadMoreWrapper.notifyDataSetChanged();
        }

    }

    @Override
    public void showExceptionView(boolean isShow) {
//        if (isShow) {
//            if (!epvAndroid.isShown()) {
//                epvAndroid.show();
//            }
//
//        } else {
//            if (epvAndroid.isShown()) {
//                epvAndroid.hide();
//            }

//        }
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(TAG, "onPause: " + "");
    }

}