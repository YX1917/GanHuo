package com.yx.personal.ganhuo.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yx.personal.ganhuo.activity.PictureActivity;
import com.yx.personal.ganhuo.bean.WelfareBean;
import com.yx.personal.ganhuo.R;

import java.util.List;

/**
 * Created by YX on 16/4/12.
 */
public class FuLiAdapter extends RecyclerView.Adapter<FuLiAdapter.MyViewHolder> {
    private Context mContext;
    private List<WelfareBean.ResultsBean> welfareBeanList;

    public FuLiAdapter(Context mContext, List<WelfareBean.ResultsBean> welfareBeanList) {
        this.mContext = mContext;
        this.welfareBeanList = welfareBeanList;
    }




    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                mContext).inflate(R.layout.item_fuli, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tv.setText(welfareBeanList.get(position).getCreatedAt());
        final Uri uri = Uri.parse(welfareBeanList.get(position).getUrl());
        holder.draweeView.setImageURI(uri);
        holder.draweeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PictureActivity.class);
                intent.putExtra("url", welfareBeanList.get(position).getUrl());
                mContext.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return welfareBeanList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        SimpleDraweeView draweeView;

        public MyViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.id_num);
            draweeView = (SimpleDraweeView) view.findViewById(R.id.my_image_view);

        }
    }

}