package com.yx.personal.ganhuo.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yx.personal.ganhuo.R;
import com.yx.personal.ganhuo.bean.DataBean;
import com.yx.personal.ganhuo.utils.AppManager;
import com.yx.personal.ganhuo.utils.ToTimeString;

import butterknife.BindView;
import butterknife.ButterKnife;

//import com.yx.personal.ganhuo.media.VideoPlayView;

public class VideoInfoActivity extends BaseActivity {

    @BindView(R.id.drawee_videoInfo_play)
    SimpleDraweeView draweeVideoInfoPlay;
    @BindView(R.id.drawee_videoInfo_blurred)
    SimpleDraweeView draweeVideoInfoBlurred;
    @BindView(R.id.tv_videoInfo_title)
    TextView tvVideoInfoTitle;
    @BindView(R.id.tv_videoInfo_type)
    TextView tvVideoInfoType;
    @BindView(R.id.tv_videoInfo_time)
    TextView tvVideoInfoTime;
    @BindView(R.id.tv_videoInfo_type1)
    TextView tvVideoInfoType1;
    @BindView(R.id.tv_videoInfo_type2)
    TextView tvVideoInfoType2;
    @BindView(R.id.tv_videoInfo_type3)
    TextView tvVideoInfoType3;
    @BindView(R.id.tv_videoInfo_type4)
    TextView tvVideoInfoType4;
    @BindView(R.id.tv_videoInfo_message)
    TextView tvVideoInfoMessage;
    @BindView(R.id.full_screen)
    FrameLayout fullScreen;

    private DataBean mDataBean;
//    private VideoPlayView videoItemView;
    private TextView[] textViews;

    @Override
    protected int getContentView() {
        return R.layout.activity_video_info;
    }

    @Override
    protected void setToolbar() {
        setTitle("精选视频");
        setIsShowBack(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
        ButterKnife.bind(this);
        getVideoInfo();
        initView();

    }

    protected void getVideoInfo() {
        mDataBean = (DataBean) getIntent().getSerializableExtra("VideoInfo");
    }

    /**
     * 初始化控件
     */
    private void initView() {
//        mPlay = (SimpleDraweeView) findViewById(R.id.drawee_videoInfo_play);
//        mBlurred = (SimpleDraweeView) findViewById(R.id.drawee_videoInfo_blurred);
//        mTitle = (TextView) findViewById(R.id.tv_videoInfo_title);
//        mType = (TextView) findViewById(R.id.tv_videoInfo_type);
//        mType1 = (TextView) findViewById(R.id.tv_videoInfo_type1);
//        mType2 = (TextView) findViewById(R.id.tv_videoInfo_type2);
//        mType3 = (TextView) findViewById(R.id.tv_videoInfo_type3);
//        mType4 = (TextView) findViewById(R.id.tv_videoInfo_type4);
//        textViews = new TextView[]{mType1, mType2, mType3, mType4};
//        mTime = (TextView) findViewById(R.id.tv_videoInfo_time);
//        mMessage = (TextView) findViewById(R.id.tv_videoInfo_message);

        draweeVideoInfoPlay.setImageURI(Uri.parse(mDataBean.getCover().getDetail()));
        draweeVideoInfoBlurred.setImageURI(Uri.parse(mDataBean.getCover().getBlurred()));
        tvVideoInfoTitle.setText(mDataBean.getTitle());
        tvVideoInfoType.setText(mDataBean.getCategory());
        textViews = new  TextView[]{tvVideoInfoType1, tvVideoInfoType2, tvVideoInfoType3, tvVideoInfoType4};
        for (int i = 0; i < mDataBean.getTags().size(); i++) {
            if (i <= 3) {
                textViews[i].setText(mDataBean.getTags().get(i).getName());
            }

        }
//        mType1.setText(mDataBean.getTags().get(0).getName());
//        mType2.setText(mDataBean.getTags().get(1).getName());
//        mType3.setText(mDataBean.getTags().get(2).getName());
//        mType4.setText(mDataBean.getTags().get(3)==null?"":mDataBean.getTags().get(3).getName());
        tvVideoInfoTime.setText(ToTimeString.toTimeString(mDataBean.getDuration()));
        tvVideoInfoMessage.setText(mDataBean.getDescription());

        draweeVideoInfoPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(VideoInfoActivity.this, PlayVideoActivity.class);
//                intent.putExtra("URL", mDataBean.getPlayUrl());
//                startActivity(intent);
//                videoItemView.start(mDataBean.getPlayInfo().get(0).getUrl());
//                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

                Intent intent = new Intent(VideoInfoActivity.this, PLPlayerActivity.class);
                intent.putExtra("URL", mDataBean.getPlayUrl());
                startActivity(intent);
            }
        });


//        videoItemView = new VideoPlayView(this);

    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
//        videoItemView.stop();
//        videoItemView.release();
//        videoItemView.onDestroy();
//        videoItemView = null;
    }

    @Override
    protected void onPause() {
        super.onPause();
//        if (videoItemView != null) {
//            videoItemView.stop();
//        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.e("VideoActivity", "转屏监听");
//        if (videoItemView != null) {
//            videoItemView.onChanged(newConfig);
//            if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
//                fullScreen.setVisibility(View.GONE);
//
//
//                videoItemView.setContorllerVisiable();
//            } else {
//                ViewGroup viewGroup = (ViewGroup) videoItemView.getParent();
//                if (viewGroup == null)
//                    return;
//                viewGroup.removeAllViews();
//                fullScreen.addView(videoItemView);
//
//                fullScreen.setVisibility(View.VISIBLE);
//            }
//        } else {

//            fullScreen.setVisibility(View.GONE);
//        }
    }


}
