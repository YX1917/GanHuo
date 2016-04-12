package com.yx.personal.ganhuo.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;
import com.squareup.okhttp.Response;
import com.yx.personal.ganhuo.Adapter.FragmentAdapter;
import com.yx.personal.ganhuo.Bean.WelfareBean;
import com.yx.personal.ganhuo.NetWork.ApiCall;
import com.yx.personal.ganhuo.NetWork.OkHttpCallback;
import com.yx.personal.ganhuo.R;


import java.io.IOException;

import de.greenrobot.event.EventBus;

/**
 * Created by YX on 16/4/12.
 */
public class FragmentOne extends Fragment {
    private RecyclerView mRecyclerView;
    private WelfareBean welfareBean;
    private OkHttpCallback okHttpCallback;
    private FragmentAdapter fragmentAdapter;

    public FragmentOne() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fuli,container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Fresco.initialize(getActivity());
        initOkHttpCall();

        welfareBean = new WelfareBean();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.id_recyclerview);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        ApiCall.RequestWelfare().enqueue(okHttpCallback);


    }


    private void initOkHttpCall() {
        okHttpCallback = new OkHttpCallback(getActivity()) {
            @Override
            protected void handleApiSuccess(Response response) throws IOException {
                Gson gson = new Gson();
                welfareBean = gson.fromJson(response.body().string(),WelfareBean.class);
                fragmentAdapter = new FragmentAdapter(getActivity(),welfareBean);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mRecyclerView.setAdapter(fragmentAdapter);
                    }
                });



            }
        };
    }



}
