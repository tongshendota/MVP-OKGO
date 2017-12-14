package com.example.pp03.peralppay.work.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;

import com.example.pp03.peralppay.R;
import com.example.pp03.peralppay.work.adapter.MyFragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by pp03 on 2017/11/30.
 */

public class OrderFragment extends BaseFragment {
    private ViewPager mPager;
    private ArrayList<Fragment> fragmentsList;
    private Fragment todayFragment;
    private Fragment yesterdayFragment;
    private Fragment longerFragment;
    private int currIndex = 0;
    private TextView today;
    private TextView yesterday;
    private TextView longer;
    @Override
    public void initData() {
        today.setOnClickListener(this);
        yesterday.setOnClickListener(this);
        longer.setOnClickListener(this);
        fragmentsList = new ArrayList<Fragment>();
        todayFragment = new TodayFragment();
        yesterdayFragment = new YesterdayFragment();
        longerFragment = new LongerFragment();
        fragmentsList.add(todayFragment);
        fragmentsList.add(yesterdayFragment);
        fragmentsList.add(longerFragment);
        mPager.setAdapter(new MyFragmentPagerAdapter(getChildFragmentManager(), fragmentsList));
        mPager.addOnPageChangeListener(new MyOnPageChangeListener());
        mPager.setCurrentItem(0);
        today.setTextColor(getResources().getColor(R.color.yellow));
    }

    @Override
    public int getFragmentLayoutResousId() {
        return R.layout.orderfragment;
    }

    @Override
    public void initUI() {
        today = (TextView)rootView.findViewById(R.id.today);
        yesterday = (TextView)rootView.findViewById(R.id.yesterday);
        longer = (TextView)rootView.findViewById(R.id.longer);

        mPager = (ViewPager) rootView.findViewById(R.id.vPager);

    }

    @Override
    public void onClick(View v) {
         switch (v.getId()){
             case R.id.today:
                 today.setTextColor(getResources().getColor(R.color.yellow));

                 mPager.setCurrentItem(0);
                 break;
             case R.id.yesterday:
                 yesterday.setTextColor(getResources().getColor(R.color.yellow));

                 mPager.setCurrentItem(1);
                 break;
             case R.id.longer:
                 longer.setTextColor(getResources().getColor(R.color.yellow));

                 mPager.setCurrentItem(2);
                 break;

         }
    }

    public void refreshData() {

    }

    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int arg0) {
            Animation animation = null;
            switch (arg0) {
                case 0:
                    today.setTextColor(getResources().getColor(R.color.yellow));
                    yesterday.setTextColor(getResources().getColor(R.color.text_color));
                    longer.setTextColor(getResources().getColor(R.color.text_color));
                    break;
                case 1:
                    yesterday.setTextColor(getResources().getColor(R.color.yellow));
                    today.setTextColor(getResources().getColor(R.color.text_color));
                    longer.setTextColor(getResources().getColor(R.color.text_color));
                    break;
                case 2:
                    longer.setTextColor(getResources().getColor(R.color.yellow));
                    today.setTextColor(getResources().getColor(R.color.text_color));
                    yesterday.setTextColor(getResources().getColor(R.color.text_color));
                    break;
            }
            currIndex = arg0;
//	            animation.setFillAfter(true);
//	            animation.setDuration(300);
//	            ivBottomLine.startAnimation(animation);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
