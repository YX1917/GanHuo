package com.yx.personal.ganhuo.cudtomView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.pili.pldroid.player.IMediaController;
import com.yx.personal.ganhuo.R;

/**
 * Created by YX on 2016/12/14.
 */

public class Controller extends FrameLayout implements IMediaController {
    private Context mContext;

    public Controller(Context context) {
        super(context);
        this.mContext = context.getApplicationContext();
        init();
    }

    private void init() {
        ((LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.media_contoller, this);
    }

    public Controller(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Controller(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setMediaPlayer(MediaPlayerControl mediaPlayerControl) {

    }

    @Override
    public void show() {

    }

    @Override
    public void show(int i) {

    }

    @Override
    public void hide() {

    }

    @Override
    public boolean isShowing() {
        return false;
    }

    @Override
    public void setEnabled(boolean b) {

    }

    @Override
    public void setAnchorView(View view) {

    }
}
