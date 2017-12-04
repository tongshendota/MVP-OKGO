package com.example.pp03.peralppay.work.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by meixx on 2017/3/25.
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    protected Activity mActivity;
    protected View rootView;
    /**用以标记当前列表网络请求是不是刷新 加载更多为false*/
    protected boolean isRefresh;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(getFragmentLayoutResousId(), container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI();
        initData();
    }

    public abstract void initData();

    public abstract int getFragmentLayoutResousId();

    public abstract void initUI();

}
