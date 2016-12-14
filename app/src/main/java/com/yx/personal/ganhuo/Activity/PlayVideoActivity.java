package com.yx.personal.ganhuo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.yx.personal.ganhuo.R;

import butterknife.ButterKnife;

public class PlayVideoActivity extends AppCompatActivity {
//
//    @BindView(R.id.player_video)
//    VideoPlayView playerVideo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_viedo);
        ButterKnife.bind(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        playerVideo.start(getIntent().getStringExtra("URL"));
//
//        playerVideo.setCompletionListener(new VideoPlayView.CompletionListener() {
//            @Override
//            public void completion(IMediaPlayer mp) {
//                finish();
//            }
//        });
//
//
//    }
//
//    private int time;
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        if (playerVideo == null) {
//            playerVideo = new VideoPlayView(this);
//        } else {
//            Log.e("TAg", "继续播放");
//            playerVideo.seekTo(time);
//            playerVideo.start();
//        }
//
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//
//
//        playerVideo.stop();
//        playerVideo.release();
//        playerVideo.onDestroy();
//        playerVideo = null;
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        if (playerVideo != null) {
//            playerVideo.pause();
//            time = playerVideo.getCurrentPosition();
//            Log.e("播放时间", playerVideo.getCurrentPosition() + "");
//        }
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//    }
//
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            finish();
//            return false;
//        } else {
//            return super.onKeyDown(keyCode, event);
//        }

    }
}
