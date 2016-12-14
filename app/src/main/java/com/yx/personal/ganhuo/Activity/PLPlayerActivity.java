package com.yx.personal.ganhuo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.yx.personal.ganhuo.R;
import com.yx.personal.ganhuo.cudtomView.MediaController;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PLPlayerActivity extends AppCompatActivity {


    @BindView(R.id.PLVideoView)
    com.pili.pldroid.player.widget.PLVideoView PLVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plplayer);
        ButterKnife.bind(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        PLVideoView.setVideoPath(getIntent().getStringExtra("URL"));
        PLVideoView.setDisplayAspectRatio(PLVideoView.ASPECT_RATIO_FIT_PARENT);
        MediaController mediaController = new MediaController(this);
        PLVideoView.setMediaController(mediaController);
        PLVideoView.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PLVideoView.stopPlayback();
    }
}
