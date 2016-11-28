package com.yx.personal.ganhuo.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yx.personal.ganhuo.bean.DataBean;
import com.yx.personal.ganhuo.media.VideoPlayView;
import com.yx.personal.ganhuo.R;
import com.yx.personal.ganhuo.utils.AppManager;
import com.yx.personal.ganhuo.utils.ToTimeString;

public class VideoInfoActivity extends BaseActivity {
    private SimpleDraweeView mPlay;
    private SimpleDraweeView mBlurred;
    private DataBean mDataBean;
    private TextView mTitle;
    private TextView mType;
    private TextView mType1;
    private TextView mType2;
    private TextView mType3;
    private TextView mType4;
    private TextView mMessage;
    private TextView mTime;
    private TextView[] textViews;

    private VideoPlayView videoItemView;
    private FrameLayout fullScreen;

    @Override
    protected int getContentView() {
        return R.layout.activity_video_info;
    }

    @Override
    protected void setToolbar() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
        initToolBar();
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
        mPlay = (SimpleDraweeView) findViewById(R.id.drawee_videoInfo_play);
        mBlurred = (SimpleDraweeView) findViewById(R.id.drawee_videoInfo_blurred);
        mTitle = (TextView) findViewById(R.id.tv_videoInfo_title);
        mType = (TextView) findViewById(R.id.tv_videoInfo_type);
        mType1 = (TextView) findViewById(R.id.tv_videoInfo_type1);
        mType2 = (TextView) findViewById(R.id.tv_videoInfo_type2);
        mType3 = (TextView) findViewById(R.id.tv_videoInfo_type3);
        mType4 = (TextView) findViewById(R.id.tv_videoInfo_type4);
        textViews= new TextView[]{mType1, mType2, mType3, mType4};
        mTime = (TextView) findViewById(R.id.tv_videoInfo_time);
        mMessage = (TextView) findViewById(R.id.tv_videoInfo_message);

        mPlay.setImageURI(Uri.parse(mDataBean.getCover().getDetail()));
        mBlurred.setImageURI(Uri.parse(mDataBean.getCover().getBlurred()));
        mTitle.setText(mDataBean.getTitle());
        mType.setText(mDataBean.getCategory());
        for(int i=0;i<mDataBean.getTags().size();i++){
            if(i<=3){
                textViews[i].setText(mDataBean.getTags().get(i).getName());
            }

        }
//        mType1.setText(mDataBean.getTags().get(0).getName());
//        mType2.setText(mDataBean.getTags().get(1).getName());
//        mType3.setText(mDataBean.getTags().get(2).getName());
//        mType4.setText(mDataBean.getTags().get(3)==null?"":mDataBean.getTags().get(3).getName());
        mTime.setText(ToTimeString.toTimeString(mDataBean.getDuration()));
        mMessage.setText(mDataBean.getDescription());

        mPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VideoInfoActivity.this, PlayVideoActivity.class);
                intent.putExtra("URL", mDataBean.getPlayInfo().get(0).getUrl());
                startActivity(intent);
//                videoItemView.start(mDataBean.getPlayInfo().get(0).getUrl());
//                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            }
        });


        fullScreen = (FrameLayout) findViewById(R.id.full_screen);
        videoItemView = new VideoPlayView(this);

    }

    /**
     * 初始化标题栏
     */
    private void initToolBar() {
        setTitle("精选视频");
        setIsShowBack(true);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        videoItemView.stop();
        videoItemView.release();
        videoItemView.onDestroy();
        videoItemView = null;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (videoItemView != null) {
            videoItemView.stop();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.e("VideoActivity","转屏监听");
        if (videoItemView != null) {
            videoItemView.onChanged(newConfig);
            if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
                fullScreen.setVisibility(View.GONE);


                videoItemView.setContorllerVisiable();
            } else {
                ViewGroup viewGroup = (ViewGroup) videoItemView.getParent();
                if (viewGroup == null)
                    return;
                viewGroup.removeAllViews();
                fullScreen.addView(videoItemView);

                fullScreen.setVisibility(View.VISIBLE);
            }
        } else {

            fullScreen.setVisibility(View.GONE);
        }
    }


}
