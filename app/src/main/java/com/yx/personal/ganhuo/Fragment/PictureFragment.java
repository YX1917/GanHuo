package com.yx.personal.ganhuo.fragment;

import android.content.Intent;
import android.net.Uri;
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
import com.yx.personal.ganhuo.activity.PictureActivity;
import com.yx.personal.ganhuo.bean.DataInfoBean;
import com.yx.personal.ganhuo.contract.PictureInfoContract;
import com.yx.personal.ganhuo.presenter.PictureInfoPresenterImpl;
import com.yx.personal.ganhuo.recyclerview.CommonAdapter;
import com.yx.personal.ganhuo.recyclerview.base.ViewHolder;
import com.yx.personal.ganhuo.recyclerview.wrapper.HeaderAndFooterWrapper;
import com.yx.personal.ganhuo.recyclerview.wrapper.LoadMoreWrapper;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by YX on 2016/11/28.
 */

public class PictureFragment extends Fragment implements PictureInfoContract.View {
    private static final String TAG = PictureFragment.class.getSimpleName();
    private PictureInfoPresenterImpl pictureInfoPresenter;
    private View view;
    private LinearLayoutManager layoutManager;

    private CommonAdapter<DataInfoBean.ResultsBean> commonAdapter;
    private LoadMoreWrapper mLoadMoreWrapper;
    private HeaderAndFooterWrapper mHeaderAndFooterWrapper;

    @BindView(R.id.recycle_picture)
    RecyclerView recyclePicture;
    @BindView(R.id.swipe_picture)
    SwipeRefreshLayout swipePicture;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_picture, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        initView();
    }

    private void initView() {
        layoutManager = new LinearLayoutManager(getActivity());
        recyclePicture.setLayoutManager(layoutManager);
        swipePicture.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pictureInfoPresenter.getDataFromNet(1);
            }
        });

    }

    private void init() {
        pictureInfoPresenter = new PictureInfoPresenterImpl(this);
        pictureInfoPresenter.getDataFromNet(1);
    }

    @Override
    public void setRefresh(boolean isRefresh) {
        swipePicture.setRefreshing(isRefresh);
    }

    @Override
    public void showData(DataInfoBean dataInfoBean) {
        if (commonAdapter==null) {
            commonAdapter = new CommonAdapter<DataInfoBean.ResultsBean>(getActivity(),R.layout.item_fuli,dataInfoBean.getResults()) {
                @Override
                protected void convert(ViewHolder holder, final DataInfoBean.ResultsBean resultsBean, int position) {
                    holder.setText(R.id.id_num,resultsBean.getCreatedAt());
                    holder.setImageURI(R.id.my_image_view, Uri.parse(resultsBean.getUrl()));
                    holder.setOnClickListener(R.id.my_image_view, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), PictureActivity.class);
                            intent.putExtra("url",resultsBean.getUrl());
                            startActivity(intent);
                        }
                    });
                }
            } ;

            mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(commonAdapter);
            mLoadMoreWrapper = new LoadMoreWrapper(mHeaderAndFooterWrapper);
            mLoadMoreWrapper.setLoadMoreView(R.layout.default_loading);
            mLoadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    Log.e(TAG, "onLoadMoreRequested: " + mLoadMoreWrapper.getItemCount());
                    pictureInfoPresenter.getDataFromNet((mLoadMoreWrapper.getItemCount()-1)/10+1);
                }
            });
            recyclePicture.setAdapter(mLoadMoreWrapper);

        } else {
            mLoadMoreWrapper.notifyDataSetChanged();
        }

    }

    @Override
    public void showExceptionView(boolean isShow) {

    }
}
