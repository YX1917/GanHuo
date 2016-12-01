package com.yx.personal.ganhuo.utils;

import android.animation.ValueAnimator;
import android.view.View;
import android.widget.FrameLayout;

/**
 * A Animate Helper to control view's visibility
 * Created by wing on 11/5/16.
 */

public class TranslateAnimateHelper implements com.yx.personal.ganhuo.utils.AnimateHelper {
    public View mTarget;
    public float mStartY;
    public int mCurrentState = STATE_SHOW;
    public int mMode = MODE_TITLE;
    public static int MODE_TITLE = 233;
    public static int MODE_BOTTOM = 2333;
    private float mFirstY = 0;
    private float mMargin;
    private static TranslateAnimateHelper translateAnimateHelper;

    private TranslateAnimateHelper(View view) {
        mTarget = view;
        mFirstY = mTarget.getY();
        mMargin = ((FrameLayout.LayoutParams) mTarget.getLayoutParams()).topMargin
                + ((FrameLayout.LayoutParams) mTarget.getLayoutParams()).bottomMargin;
    }

    public static TranslateAnimateHelper getInstance(View view) {
        if (translateAnimateHelper == null) {
            translateAnimateHelper = new TranslateAnimateHelper(view);
        }
        return translateAnimateHelper;
    }

    public void show() {
        if (mMode == MODE_TITLE) {
            showTitle();
        } else if (mMode == MODE_BOTTOM) {
            showBottom();
        }
    }

    public void hideTitle() {
        ValueAnimator va = ValueAnimator.ofFloat(mTarget.getY(), -mTarget.getHeight());
        va.setDuration(300);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mTarget.setY((Float) valueAnimator.getAnimatedValue());
            }
        });
        va.start();
        mCurrentState = STATE_HIDE;
    }

    public void showTitle() {

        ValueAnimator va = ValueAnimator.ofFloat(mTarget.getY(), 0);
        va.setDuration(300);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mTarget.setY((Float) valueAnimator.getAnimatedValue());
            }
        });
        va.start();
        mCurrentState = STATE_SHOW;
    }

    public void hide() {
        if (mMode == MODE_TITLE) {
            hideTitle();
        } else if (mMode == MODE_BOTTOM) {
            hideBottom();
        }
    }

    public void showBottom() {

        ValueAnimator va = ValueAnimator.ofFloat(mTarget.getY(), mFirstY);
        va.setDuration(300);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mTarget.setY((Float) valueAnimator.getAnimatedValue());
            }
        });

        va.start();
        mCurrentState = STATE_SHOW;
    }

    public void hideBottom() {
        ValueAnimator va = ValueAnimator.ofFloat(mTarget.getY(), mFirstY + mTarget.getHeight() + mMargin);
        va.setDuration(300);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mTarget.setY((Float) valueAnimator.getAnimatedValue());
            }
        });

        va.start();
        mCurrentState = STATE_HIDE;
    }

    public void setStartY(float y) {
        mStartY = y;
    }

    public int getState() {
        return mCurrentState;
    }

    public void setMode(int mode) {
        mMode = mode;
    }

    private void setState(int state) {
        mCurrentState = state;
    }
}
