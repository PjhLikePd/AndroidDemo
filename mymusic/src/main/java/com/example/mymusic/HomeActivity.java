package com.example.mymusic;


import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.example.mymusic.base.BaseActivity;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.abs.IPagerNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

public class HomeActivity extends BaseActivity implements View.OnClickListener {

    private CHANNEL[] channels = new CHANNEL[]{
            CHANNEL.MY, CHANNEL.DISCORY, CHANNEL.FRIEND
    };

    private DrawerLayout drawerLayout;
    private View toggleView;
    private View searchView;
    private ViewPager viewPager;
    private HomePageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initData();
    }

    @Override
    protected void initView() {
        super.initView();

        drawerLayout = findViewById(R.id.dw_layout);
        toggleView = findViewById(R.id.tv_toggle);
        toggleView.setOnClickListener(this);
        searchView = findViewById(R.id.tv_search);
        searchView.setOnClickListener(this);

        // 初始化adapter
        viewPager = findViewById(R.id.view_pager);
        adapter = new HomePageAdapter(getSupportFragmentManager(), 1, channels);
        viewPager.setAdapter(adapter);

        // 初始化viewPager的指示器
        initMagicIndicator();
    }

    private void initMagicIndicator() {
        MagicIndicator indicator = findViewById(R.id.magic_indicator);
        indicator.setBackgroundColor(Color.WHITE);
        CommonNavigator navigator = new CommonNavigator(this);
        navigator.setAdjustMode(true); // 设置均分
        navigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return channels == null ? 0 : channels.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, int index) {
                SimplePagerTitleView titleView = new SimplePagerTitleView(context);

                titleView.setText(channels[index].getKey());
                titleView.setTextSize(19);
                titleView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                titleView.setNormalColor(Color.parseColor("#999999"));
                titleView.setSelectedColor(Color.parseColor("#333333"));
                titleView.setOnClickListener(v -> viewPager.setCurrentItem(index));

                return titleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                return null;
            }

            @Override
            public float getTitleWeight(Context context, int index) {
                return 1.0f;
            }
        });
        indicator.setNavigator(navigator);
        ViewPagerHelper.bind(indicator, viewPager);
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_toggle:
                if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    drawerLayout.closeDrawer(Gravity.LEFT);
                } else {
                    drawerLayout.openDrawer(Gravity.LEFT);
                }
                break;
        }
    }
}