package com.yx.personal.ganhuo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.WindowManager;

import com.yx.personal.ganhuo.media.VideoPlayView;
import com.yx.personal.ganhuo.R;

import tv.danmaku.ijk.media.player.IMediaPlayer;

public class PlayVideoActivity extends AppCompatActivity {

    private VideoPlayView videoItemView;
    private GestureDetector mGestureDetector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_viedo);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        videoItemView = (VideoPlayView) findViewById(R.id.player_video);
        videoItemView.start(getIntent().getStringExtra("URL"));

        videoItemView.setCompletionListener(new VideoPlayView.CompletionListener() {
            @Override
            public void completion(IMediaPlayer mp) {
                finish();
            }
        });


    }

private int time;
    @Override
    protected void onResume() {
        super.onResume();
        if (videoItemView==null){
            videoItemView=new VideoPlayView(this);
        }else{
            Log.e("TAg","继续播放");
            videoItemView.seekTo(time);
            videoItemView.start();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


        videoItemView.stop();
        videoItemView.release();
        videoItemView.onDestroy();
        videoItemView=null;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (videoItemView!=null){
            videoItemView.pause();
            time = videoItemView.getCurrentPosition();
            Log.e("播放时间",videoItemView.getCurrentPosition()+"");
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            finish();
            return false;
        }else {
            return super.onKeyDown(keyCode, event);
        }

    }
}
