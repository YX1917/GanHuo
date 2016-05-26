package com.yx.personal.ganhuo.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yx.personal.ganhuo.Bean.AndroidInfoBean;
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
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.title.setText(androidBeanList.get(position).getDesc());
        holder.date.setText(androidBeanList.get(position).getCreatedAt());
        holder.who.setText(androidBeanList.get(position).getWho());
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
}
