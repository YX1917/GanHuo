package com.yx.personal.ganhuo.Activity;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yx.personal.ganhuo.Bean.DataBean;
import com.yx.personal.ganhuo.R;
import com.yx.personal.ganhuo.Utils.AppManager;
import com.yx.personal.ganhuo.Utils.ToTimeString;

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

    @Override
    protected int getContentView() {
        return R.layout.activity_video_info;
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
        mTime= (TextView) findViewById(R.id.tv_videoInfo_time);
        mMessage =(TextView) findViewById(R.id.tv_videoInfo_message);

        mPlay.setImageURI(Uri.parse(mDataBean.getCover().getDetail()));
        mBlurred.setImageURI(Uri.parse(mDataBean.getCover().getBlurred()));
        mTitle.setText(mDataBean.getTitle());
        mType.setText(mDataBean.getCategory());
        mType1.setText(mDataBean.getTags().get(0).getName());
        mType2.setText(mDataBean.getTags().get(1).getName());
        mType3.setText(mDataBean.getTags().get(2).getName());
        mType4.setText(mDataBean.getTags().get(3).getName());
        mTime.setText(ToTimeString.toTimeString(mDataBean.getDuration()));
        mMessage.setText(mDataBean.getDescription());
    }

    /**
     * 初始化标题栏
     */
    private void initToolBar() {
        setTitle("精选视频");
        setIsShowBack(true);
    }


}
