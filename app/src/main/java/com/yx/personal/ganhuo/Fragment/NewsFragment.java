package com.yx.personal.ganhuo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yx.personal.ganhuo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by YX on 2016/11/28.
 */

public class NewsFragment extends Fragment {

    private List<Fragment> childFragmentList = new ArrayList<>();
    private List<String> tabNamesList = new ArrayList<>();

    @BindView(R.id.tab_switch)
    TabLayout tabSwitch;
    @BindView(R.id.vp_news_fragment)
    ViewPager vpNewsFragment;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_news, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        if (childFragmentList.isEmpty()){
            childFragmentList.add(new AndroidFragment());
            childFragmentList.add(new IOSFragment());
        }
        if (tabNamesList.isEmpty()){
            tabNamesList.add("Android");
            tabNamesList.add("iOS");
        }
        tabSwitch.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabSwitch.addTab(tabSwitch.newTab().setText("Android"));
        tabSwitch.addTab(tabSwitch.newTab().setText("iOS"));
        tabSwitch.setupWithViewPager(vpNewsFragment);

        vpNewsFragment.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return childFragmentList.get(position);
            }

            @Override
            public int getCount() {
                return childFragmentList.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return tabNamesList.get(position);
            }
        });

    }

    public void test(){

    }

}
