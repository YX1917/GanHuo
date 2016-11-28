package com.yx.personal.ganhuo.utils;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v4.content.ContextCompat;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.yx.personal.ganhuo.R;

/**
 * Created by YX on 16/4/14.
 * 未使用 待完善
 */
public class ImageLoadFresco {
    private static ImageLoadFresco instance;
    private static GenericDraweeHierarchyBuilder draweeHierarchyBuilder;
    private static GenericDraweeHierarchy draweeHierarchy;

    public static ImageLoadFresco getInstance(Context context) {
        if (null == instance) {
            instance = new ImageLoadFresco();
            draweeHierarchyBuilder = new GenericDraweeHierarchyBuilder(Resources.getSystem());
            draweeHierarchy = draweeHierarchyBuilder
                    .setFailureImage(ContextCompat.getDrawable(context, R.drawable.icon_failure), ScalingUtils.ScaleType.CENTER_INSIDE)
                    .build();
        }
        return instance;
    }

    public void ShowImg(SimpleDraweeView draweeView, Uri uri) {
        draweeView.setImageURI(uri);
        draweeView.setHierarchy(draweeHierarchy);
                DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setTapToRetryEnabled(true)
                .setOldController(draweeView.getController())
                .build();
        draweeView.setController(draweeController);


    }


}
