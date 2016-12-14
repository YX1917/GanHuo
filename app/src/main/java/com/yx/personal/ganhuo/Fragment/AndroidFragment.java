package com.yx.personal.ganhuo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by YX on 2016/11/28.
 */

public class AndroidFragment extends BaseFragment implements AndroidInfoContract.View {
    private static final String TAG = AndroidFragment.class.getSimpleName();

    @BindView(R.id.recycle_android)
    RecyclerView recycleAndroid;
    @BindView(R.id.swipe_android_refresh)
    SwipeRefreshLayout swipeAndroidRefresh;
    private AndroidInfoPresenterImpl androidInfoPresenter;
    private LinearLayoutManager layoutManager;
    private int firstVisibleItem;

    private CommonAdapter<DataInfoBean.ResultsBean> commonAdapter;
    private LoadMoreWrapper mLoadMoreWrapper;
    private HeaderAndFooterWrapper mHeaderAndFooterWrapper;
    private DataInfoBean dataInfoBeen;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_android, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void init() {
        androidInfoPresenter = new AndroidInfoPresenterImpl(this);

        layoutManager = new LinearLayoutManager(getActivity());
        recycleAndroid.setLayoutManager(layoutManager);
        swipeAndroidRefresh.setColorSchemeColors(ContextCompat.getColor(getActivity(),R.color.colorPrimary));
        swipeAndroidRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                androidInfoPresenter.getDataFromNet(1);
            }
        });

    }


    @Override
    protected void loadData() {
        if (dataInfoBeen == null) {
            androidInfoPresenter.getDataFromNet(1);
        } else {
            initRecycleAdapter(dataInfoBeen.getResults());
            recycleAndroid.scrollToPosition(firstVisibleItem);
        }
    }


    @Override
    public void setRefresh(boolean isRefresh) {
        swipeAndroidRefresh.setRefreshing(isRefresh);
    }

    @Override
    public void showData(final DataInfoBean dataInfoBean) {
        this.dataInfoBeen = dataInfoBean;
        if (commonAdapter == null) {
            initRecycleAdapter(dataInfoBean.getResults());
            firstVisibleItem = layoutManager.findFirstVisibleItemPosition();
        } else {
            mLoadMoreWrapper.notifyDataSetChanged();
        }

    }

    @Override
    public void showExceptionView(boolean isShow) {

    }


    private void initRecycleAdapter(List<DataInfoBean.ResultsBean> results) {
        commonAdapter = new CommonAdapter<DataInfoBean.ResultsBean>(getContext(), R.layout.item_android, results) {
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
                androidInfoPresenter.getDataFromNet((mLoadMoreWrapper.getItemCount() - 1) / 10 + 1);
            }
        });
        recycleAndroid.setAdapter(mLoadMoreWrapper);
    }

}