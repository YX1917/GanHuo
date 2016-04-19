package com.yx.personal.ganhuo.Activity;

import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.facebook.common.logging.FLog;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.image.QualityInfo;
import com.yx.personal.ganhuo.R;

public class TestActivity extends AppCompatActivity {
    private SimpleDraweeView mSimpleDraweeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mSimpleDraweeView = (SimpleDraweeView) findViewById(R.id.my_image_view);
        mSimpleDraweeView.setImageURI(Uri.parse("http://my.csdn.net/uploads/avatar/4/E/8/1_y1scp.jpg"));
        ControllerListener controllerListener = new BaseControllerListener<ImageInfo>() {
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
                Log.e("TAG", "Intermediate image received");
            }

            @Override
            public void onFailure(String id, Throwable throwable) {
                Log.e("TAG","加载失败");
            }
        };
        DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                .setUri(Uri.parse("http://my.csdn.net/uploads/avatar/4/E/8/1_y1scp.jpg"))
                .setTapToRetryEnabled(true)
                .setControllerListener(controllerListener)
                .setOldController(mSimpleDraweeView.getController())
                .build();
        mSimpleDraweeView.setController(draweeController);
    }


}
