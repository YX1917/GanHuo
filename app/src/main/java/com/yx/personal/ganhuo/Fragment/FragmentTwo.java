package com.yx.personal.ganhuo.Fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.yx.personal.ganhuo.R;

/**
 * Created by YX on 16/4/15.
 */
public class FragmentTwo extends Fragment {
    private SimpleDraweeView mSimpleDraweeView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_android, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mSimpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.my_image_view);
        mSimpleDraweeView.setImageURI(Uri.parse("http://my.csdn.net/uploads/avatar/4/E/8/1_y1scp.jpg"));
        DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                .setUri(Uri.parse("http://my.csdn.net/uploads/avatar/4/E/8/1_y1scp.jpg"))
                .setTapToRetryEnabled(true)
                .setOldController(mSimpleDraweeView.getController())
                .build();
        mSimpleDraweeView.setController(draweeController);
    }
}
