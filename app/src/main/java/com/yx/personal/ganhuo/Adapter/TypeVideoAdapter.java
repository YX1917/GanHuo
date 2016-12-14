package com.yx.personal.ganhuo.adapter;

import android.content.Context;
import android.net.Uri;

import com.yx.personal.ganhuo.R;
import com.yx.personal.ganhuo.bean.DailyPicksBean;
import com.yx.personal.ganhuo.recyclerview.MultiItemTypeAdapter;
import com.yx.personal.ganhuo.recyclerview.base.ItemViewDelegate;
import com.yx.personal.ganhuo.recyclerview.base.ViewHolder;
import com.yx.personal.ganhuo.utils.ToTimeString;

import java.util.List;

/**
 * Created by YX on 2016/12/13.
 */

public class TypeVideoAdapter extends MultiItemTypeAdapter<DailyPicksBean.IssueListBean.ItemListBean> {

    public TypeVideoAdapter(Context context, List<DailyPicksBean.IssueListBean.ItemListBean> datas) {
        super(context, datas);
        addItemViewDelegate(new VideoType());
        addItemViewDelegate(new BannerType());
    }


    public class VideoType implements ItemViewDelegate<DailyPicksBean.IssueListBean.ItemListBean>{

        @Override
        public int getItemViewLayoutId() {
            return R.layout.item_video_detail;
        }

        @Override
        public boolean isForViewType(DailyPicksBean.IssueListBean.ItemListBean item, int position) {
            return item.getType().equals("video");
        }

        @Override
        public void convert(ViewHolder holder, DailyPicksBean.IssueListBean.ItemListBean itemListBean, int position) {
            holder.setText(R.id.tv_video_title,itemListBean.getData().getTitle());
            holder.setText(R.id.tv_video_time, ToTimeString.toTimeString(itemListBean.getData().getDuration()));
            holder.setText(R.id.tv_video_type,"# "+itemListBean.getData().getCategory());
            holder.setImageURI(R.id.drawee_videoDetail_image, Uri.parse(itemListBean.getData().getCover().getDetail()));
        }
    }

    public class BannerType implements ItemViewDelegate<DailyPicksBean.IssueListBean.ItemListBean>{

        @Override
        public int getItemViewLayoutId() {
            return R.layout.item_video_detail;
        }

        @Override
        public boolean isForViewType(DailyPicksBean.IssueListBean.ItemListBean item, int position) {
            return item.getType().equals("banner2");
        }

        @Override
        public void convert(ViewHolder holder, DailyPicksBean.IssueListBean.ItemListBean itemListBean, int position) {
            holder.setText(R.id.tv_video_title,"我只是个广告，不要点我");
            holder.setImageURI(R.id.drawee_videoDetail_image, Uri.parse(itemListBean.getData().getImage()));
        }
    }
}
