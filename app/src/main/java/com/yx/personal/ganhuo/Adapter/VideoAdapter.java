package com.yx.personal.ganhuo.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yx.personal.ganhuo.bean.DailyPicksBean;
import com.yx.personal.ganhuo.R;
import com.yx.personal.ganhuo.utils.ToTimeString;

/**
 * Created by YX on 16/6/29.
 */
public class VideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private DailyPicksBean mDailyPicksBean;

    public VideoAdapter(Context context, DailyPicksBean dailyPicksBean) {
        mContext = context;
        mDailyPicksBean = dailyPicksBean;
    }

    public enum ITEM_TYPE {
        ITEMHEDA,
        ITEMDETAIL,
        ITEMBANNER,
        ITEMTEXT
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        if (viewType == ITEM_TYPE.ITEMHEDA.ordinal()) {
//            holder = new HeadViewHolder(LayoutInflater.from(
//                    mContext).inflate(R.layout.item_video_head, parent,
//                    false));
            holder = new HeadViewHolder(LayoutInflater.from(
                    mContext).inflate(R.layout.none, parent,
                    false));
        } else if (viewType == ITEM_TYPE.ITEMDETAIL.ordinal()) {
            holder = new DetailViewHolder(LayoutInflater.from(
                    mContext).inflate(R.layout.item_video_detail, parent,
                    false));
        }else if(viewType == ITEM_TYPE.ITEMBANNER.ordinal()){
//            holder = new BannerViewHolder(LayoutInflater.from(
//                    mContext).inflate(R.layout.item_video_banner, parent,
//                    false));
            holder = new BannerViewHolder(LayoutInflater.from(
                    mContext).inflate(R.layout.none, parent,
                    false));
        }else if(viewType == ITEM_TYPE.ITEMTEXT.ordinal()){
//            holder = new TextViewHolder(LayoutInflater.from(
//                    mContext).inflate(R.layout.item_video_text, parent,
//                    false));
            holder = new TextViewHolder(LayoutInflater.from(
                    mContext).inflate(R.layout.none, parent,
                    false));
        }

        return holder;
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        Log.e("onBindViewHolder",position+"");
            if(holder instanceof HeadViewHolder){
//                String url = mDailyPicksBean.getIssueList().get(0).getItemList().get(position).getData().getItemList().get(0).getData().getImage();
//                ((HeadViewHolder) holder).draweeView.setImageURI(Uri.parse(url));
            }else if(holder instanceof DetailViewHolder){
                String url = mDailyPicksBean.getIssueList().get(0).getItemList().get(position).getData().getCover().getDetail();
                ((DetailViewHolder) holder).draweeView.setImageURI(Uri.parse(url));
                ((DetailViewHolder) holder).title.setText(mDailyPicksBean.getIssueList().get(0).getItemList().get(position).getData().getTitle());
                ((DetailViewHolder) holder).type.setText("#"+mDailyPicksBean.getIssueList().get(0).getItemList().get(position).getData().getCategory());
                ((DetailViewHolder) holder).time.setText(ToTimeString.toTimeString(mDailyPicksBean.getIssueList().get(0).getItemList().get(position).getData().getDuration()));
                if (mOnItemClickListener != null)
                {
                    holder.itemView.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            int pos = holder.getLayoutPosition();
                            mOnItemClickListener.onItemClick(holder.itemView, pos);
                        }
                    });

                    holder.itemView.setOnLongClickListener(new View.OnLongClickListener()
                    {
                        @Override
                        public boolean onLongClick(View v)
                        {
                            int pos = holder.getLayoutPosition();
                            mOnItemClickListener.onItemLongClick(holder.itemView, pos);
                            return false;
                        }
                    });
                }
            }else if(holder instanceof BannerViewHolder){
//                String url = mDailyPicksBean.getIssueList().get(0).getItemList().get(position).getData().getImage();
//                ((BannerViewHolder) holder).draweeView.setImageURI(Uri.parse(url));
            }else if(holder instanceof TextViewHolder){
//                String text = mDailyPicksBean.getIssueList().get(0).getItemList().get(position).getData().getText();
//                ((TextViewHolder) holder).tv.setText(text);
            }
    }

    @Override
    public int getItemCount() {
//        return mDailyPicksBean.getIssueList().get(0).getItemList().size();
        int size = 0;
        for(int i= 0;i<mDailyPicksBean.getIssueList().get(0).getItemList().size();i++){
            if(mDailyPicksBean.getIssueList().get(0).getItemList().get(i).getType().equals("video")){
                size++;
            }
        }
        return size;
    }

    @Override
    public int getItemViewType(int position) {
        Log.e("getItemViewType",position+"");

        String type = mDailyPicksBean.getIssueList().get(0).getItemList().get(position).getType();
        Log.e("getItemViewType",type);
        if (type.equals("horizontalScrollCard")) {
            return ITEM_TYPE.ITEMHEDA.ordinal();
        } else if (type.equals("video")) {
            return ITEM_TYPE.ITEMDETAIL.ordinal();
        } else if (type.equals("banner1")) {
            return ITEM_TYPE.ITEMBANNER.ordinal();
        } else if (type.equals("textHeader")) {
            return ITEM_TYPE.ITEMTEXT.ordinal();
        } else {
            return super.getItemViewType(position);
        }

    }

    public class HeadViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView draweeView;

        public HeadViewHolder(View itemView) {
            super(itemView);
            draweeView = (SimpleDraweeView) itemView.findViewById(R.id.drawee_videoHead_image);
        }
    }
    public class DetailViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView draweeView;
        TextView title;
        TextView type;
        TextView time;

        public DetailViewHolder(View itemView) {
            super(itemView);
            draweeView = (SimpleDraweeView) itemView.findViewById(R.id.drawee_videoDetail_image);
            title = (TextView) itemView.findViewById(R.id.tv_video_title);
            type = (TextView) itemView.findViewById(R.id.tv_video_type);
            time = (TextView) itemView.findViewById(R.id.tv_video_time);

        }
    }

    public class TextViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public TextViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_video_textHeader);
        }
    }

    public class BannerViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView draweeView;

        public BannerViewHolder(View itemView) {
            super(itemView);
            draweeView = (SimpleDraweeView) itemView.findViewById(R.id.drawee_videoBanner_image);
        }
    }


    public interface OnItemClickListener
    {
        void onItemClick(View view, int position);
        void onItemLongClick(View view , int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickLitener(OnItemClickListener mOnItemClickListener)
    {
        this.mOnItemClickListener = mOnItemClickListener;
    }
}
