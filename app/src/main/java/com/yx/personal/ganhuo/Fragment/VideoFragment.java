package com.yx.personal.ganhuo.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yx.personal.ganhuo.R;
import com.yx.personal.ganhuo.bean.DailyPicksBean;
import com.yx.personal.ganhuo.contract.VideoInfoContract;
import com.yx.personal.ganhuo.presenter.VideoInfoPresenterImpl;
import com.yx.personal.ganhuo.recyclerview.CommonAdapter;
import com.yx.personal.ganhuo.recyclerview.MultiItemTypeAdapter;
import com.yx.personal.ganhuo.recyclerview.base.ViewHolder;
import com.yx.personal.ganhuo.utils.ToTimeString;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by YX on 2016/11/28.
 */

public class VideoFragment extends BaseFragment implements VideoInfoContract.View {
    private VideoInfoPresenterImpl videoInfoPresenter;
    private LinearLayoutManager layoutManager;

    private DailyPicksBean dailyPicksBean;
    private CommonAdapter<DailyPicksBean.IssueListBean.ItemListBean> commonAdapter;
    private MultiItemTypeAdapter<DailyPicksBean.IssueListBean.ItemListBean> typeAdapter;
    private int firstVisibleItem;


    @BindView(R.id.recycle_video)
    RecyclerView recycleVideo;
    @BindView(R.id.swipe_video_refresh)
    SwipeRefreshLayout swipeVideoRefresh;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void loadData() {

        if (dailyPicksBean==null) {
            videoInfoPresenter.getDataFromNet();
        }else{
            initRecycleAdapter(dailyPicksBean.getIssueList().get(0).getItemList());
            recycleVideo.scrollToPosition(firstVisibleItem);
        }
    }

    private void initRecycleAdapter(final List<DailyPicksBean.IssueListBean.ItemListBean> issueList) {
        commonAdapter = new CommonAdapter<DailyPicksBean.IssueListBean.ItemListBean>(getContext(),R.layout.item_video_detail,issueList) {
            @Override
            protected void convert(ViewHolder holder, DailyPicksBean.IssueListBean.ItemListBean itemListBean, int position) {
                if (itemListBean.getType().equals("video")) {
                    holder.setText(R.id.tv_video_title,itemListBean.getData().getTitle());
                    holder.setText(R.id.tv_video_time, ToTimeString.toTimeString(itemListBean.getData().getDuration()));
                    holder.setText(R.id.tv_video_type,itemListBean.getData().getType());
                    holder.setImageURI(R.id.drawee_videoDetail_image, Uri.parse(itemListBean.getData().getCover().getDetail()));
                }
            }
        };
        commonAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {

            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

        recycleVideo.setAdapter(commonAdapter);
    }

    @Override
    protected void init() {
        videoInfoPresenter = new VideoInfoPresenterImpl(this);

        layoutManager = new LinearLayoutManager(getActivity());
        recycleVideo.setLayoutManager(layoutManager);
        swipeVideoRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                videoInfoPresenter.getDataFromNet();
            }
        });
    }

    @Override
    public void setRefresh(boolean isRefresh) {
        swipeVideoRefresh.setRefreshing(isRefresh);
    }

    @Override
    public void showData(DailyPicksBean dailyPicksBean) {
        this.dailyPicksBean = dailyPicksBean;
        if (commonAdapter == null) {
            initRecycleAdapter(dailyPicksBean.getIssueList().get(0).getItemList());
            firstVisibleItem = layoutManager.findFirstVisibleItemPosition();
        } else {
            commonAdapter.notifyDataSetChanged();
        }
    }
}
