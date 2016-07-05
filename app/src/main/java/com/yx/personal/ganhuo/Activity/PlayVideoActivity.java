package com.yx.personal.ganhuo.Activity;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.yx.personal.ganhuo.Media.CustomMediaContoller;
import com.yx.personal.ganhuo.Media.IjkVideoView;
import com.yx.personal.ganhuo.Media.VideoPlayView;
import com.yx.personal.ganhuo.R;

import tv.danmaku.ijk.media.player.IMediaPlayer;

public class PlayVideoActivity extends AppCompatActivity {

    private IjkVideoView videoItemView;
    private CustomMediaContoller mediaController;
    private View rView;
    private View player_btn, view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = findViewById(R.id.media_contoller);
        setContentView(R.layout.activity_play_viedo);
        videoItemView = (IjkVideoView) findViewById(R.id.main_video);
        mediaController = new CustomMediaContoller(this, videoItemView);

        videoItemView.setMediaController(mediaController);
        mediaController.setVisiable();
        videoItemView.setVideoURI(Uri.parse(getIntent().getStringExtra("URL")));
        videoItemView.start();

        videoItemView.setOnCompletionListener(new IMediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(IMediaPlayer mp) {
                finish();
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        videoItemView.stopPlayback();
        videoItemView.release(true);
        videoItemView=null;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (videoItemView!=null){
            videoItemView.stopPlayback();
        }
    }
}
