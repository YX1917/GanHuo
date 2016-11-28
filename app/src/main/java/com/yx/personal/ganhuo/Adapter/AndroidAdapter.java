package com.yx.personal.ganhuo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yx.personal.ganhuo.bean.AndroidInfoBean;
import com.yx.personal.ganhuo.R;

import java.util.List;

/**
 * Created by YX on 16/5/25.
 */
public class AndroidAdapter extends RecyclerView.Adapter<AndroidAdapter.MyViewHolder> {
    private Context mContext;
    private  List<AndroidInfoBean.ResultsBean> androidBeanList;


    public AndroidAdapter(Context mContext, List<AndroidInfoBean.ResultsBean> androidBeanList) {
        this.mContext = mContext;
        this.androidBeanList = androidBeanList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                mContext).inflate(R.layout.item_android, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.title.setText(androidBeanList.get(position).getDesc());
        holder.date.setText(androidBeanList.get(position).getCreatedAt());
        holder.who.setText(androidBeanList.get(position).getWho());

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
    }

    @Override
    public int getItemCount() {
        return androidBeanList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView date;
        TextView who;


        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.tv_android_title);
            date = (TextView) view.findViewById(R.id.tv_android_date);
            who = (TextView) view.findViewById(R.id.tv_android_who);

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
