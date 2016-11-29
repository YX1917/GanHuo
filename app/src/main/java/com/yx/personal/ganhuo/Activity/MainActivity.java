package com.yx.personal.ganhuo.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.yx.personal.ganhuo.R;
import com.yx.personal.ganhuo.cudtomView.BottomNavigationViewEx;
import com.yx.personal.ganhuo.cudtomView.CustomViewPager;
import com.yx.personal.ganhuo.fragment.MineFragment;
import com.yx.personal.ganhuo.fragment.NewsFragment;
import com.yx.personal.ganhuo.fragment.PictureFragment;
import com.yx.personal.ganhuo.fragment.VideoFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    private final String TAG = getClass().getSimpleName();
    private List<Fragment> fragmentList = new ArrayList<>();
    private FragmentPagerAdapter pagerAdapter;


    @BindView(R.id.vp_fragment)
    CustomViewPager vpFragment;
    @BindView(R.id.bottom_nav)
    BottomNavigationViewEx bottomNav;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void setToolbar() {
        setTitle("I LOVE YOU");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initBottomNav();
        initViewPager();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
    }

    private void initViewPager() {
        if (fragmentList.isEmpty()) {
            fragmentList.add(new NewsFragment());
            fragmentList.add(new PictureFragment());
            fragmentList.add(new VideoFragment());
            fragmentList.add(new MineFragment());
        }
        pagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        };
        vpFragment.setAdapter(pagerAdapter);
        vpFragment.setCurrentItem(0);

    }

    private void initBottomNav() {
        bottomNav.enableAnimation(true);
        bottomNav.enableShiftingMode(false);
        bottomNav.enableItemShiftingMode(false);
        bottomNav.setTextVisibility(true);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bottom_news:
                        vpFragment.setCurrentItem(0);
                        break;
                    case R.id.bottom_picture:
                        vpFragment.setCurrentItem(1);
                        break;
                    case R.id.bottom_video:
                        vpFragment.setCurrentItem(2);
                        break;
                    case R.id.bottom_mine:
                        vpFragment.setCurrentItem(3);
                        break;
                }
                return true;
            }
        });

    }

}
