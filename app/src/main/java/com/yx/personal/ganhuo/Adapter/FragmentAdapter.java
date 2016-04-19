package com.yx.personal.ganhuo.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.common.logging.FLog;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.image.QualityInfo;
import com.yx.personal.ganhuo.Activity.PictureActivity;
import com.yx.personal.ganhuo.Bean.WelfareBean;
import com.yx.personal.ganhuo.R;
import com.yx.personal.ganhuo.Utils.ImageLoadFresco;

import java.util.List;

/**
 * Created by YX on 16/4/12.
 */
public class FragmentAdapter extends RecyclerView.Adapter<FragmentAdapter.MyViewHolder> {
    private Context mContext;
    private List<WelfareBean.ResultsBean> welfareBeanList;
    private ControllerListener controllerListener;
    private   DraweeController draweeController;

    public FragmentAdapter(Context mContext, List<WelfareBean.ResultsBean> welfareBeanList) {
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
//        holder.draweeView.setImageURI(Uri.parse(welfareBeanList.get(position).getUrl()));
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
        controllerListener = new BaseControllerListener<ImageInfo>() {
            @Override
            public void onFinalImageSet(
                    String id,
                    @Nullable ImageInfo imageInfo,
                    @Nullable Animatable anim) {
                if (imageInfo == null) {
                    return;
                }
                QualityInfo qualityInfo = imageInfo.getQualityInfo();
                FLog.d("Final image received! " +
                                "Size %d x %d",
                        "Quality level %d, good enough: %s, full quality: %s",
                        imageInfo.getWidth(),
                        imageInfo.getHeight(),
                        qualityInfo.getQuality(),
                        qualityInfo.isOfGoodEnoughQuality(),
                        qualityInfo.isOfFullQuality());
            }

            @Override
            public void onIntermediateImageSet(String id, @Nullable ImageInfo imageInfo) {
                Log.e("TAG","Intermediate image received");
            }

            @Override
            public void onFailure(String id, Throwable throwable) {
                Log.e("TAG","加载失败");
            }
        };
        draweeController = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setTapToRetryEnabled(true)
                .setControllerListener(controllerListener)
                .setOldController(holder.draweeView.getController())
                .build();
       holder.draweeView.setController(draweeController);

//        ImageLoadFresco.getInstance(mContext).ShowImg(holder.draweeView,Uri.parse("http://ww2.sinaimg.cn/large/7a8aed7bjw1f2w0qujoecj20f00kzjt.jpg"));
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