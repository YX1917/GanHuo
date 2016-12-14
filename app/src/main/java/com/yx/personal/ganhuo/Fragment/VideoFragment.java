package com.yx.personal.ganhuo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yx.personal.ganhuo.R;
import com.yx.personal.ganhuo.activity.VideoInfoActivity;
import com.yx.personal.ganhuo.adapter.TypeVideoAdapter;
import com.yx.personal.ganhuo.bean.DailyPicksBean;
import com.yx.personal.ganhuo.contract.VideoInfoContract;
import com.yx.personal.ganhuo.presenter.VideoInfoPresenterImpl;
import com.yx.personal.ganhuo.recyclerview.MultiItemTypeAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by YX on 2016/11/28.
 */

public class VideoFragment extends BaseFragment implements VideoInfoContract.View {
    private static final String TAG = VideoFragment.class.getSimpleName();
    private VideoInfoPresenterImpl videoInfoPresenter;
    private LinearLayoutManager layoutManager;

    private DailyPicksBean dailyPicksBean;
    private int firstVisibleItem;
    private TypeVideoAdapter typeVideoAdapter;


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
        for (int i=0;i<issueList.size();i++) {
            if(!issueList.get(i).getType().equals("video")){
                issueList.remove(i);
            }
        }
        typeVideoAdapter = new TypeVideoAdapter(getContext(),issueList);
        typeVideoAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Intent intent = new Intent(getActivity(),VideoInfoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("VideoInfo", issueList.get(position).getData());
                intent.putExtras(bundle);
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        recycleVideo.setAdapter(typeVideoAdapter);
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
        if (typeVideoAdapter == null) {
            initRecycleAdapter(dailyPicksBean.getIssueList().get(0).getItemList());
            firstVisibleItem = layoutManager.findFirstVisibleItemPosition();
        } else {
            typeVideoAdapter.notifyDataSetChanged();
        }
    }
}
