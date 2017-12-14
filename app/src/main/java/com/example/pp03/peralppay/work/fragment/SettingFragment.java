package com.example.pp03.peralppay.work.fragment;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

import com.example.pp03.peralppay.R;
import com.example.pp03.peralppay.utils.LocaleUtils;
import com.example.pp03.peralppay.work.MainActivity;

import java.util.Locale;

/**
 * Created by pp03 on 2017-12-13.
 */

public class SettingFragment extends BaseFragment{
    TextView en;
    TextView cn;
    @Override
    public void initData() {

    }

    @Override
    public int getFragmentLayoutResousId() {
        return R.layout.settingfragment;
    }

    @Override
    public void initUI() {
         en =(TextView) rootView.findViewById(R.id.en);
         cn =(TextView) rootView.findViewById(R.id.cn);
        cn.setOnClickListener(this);
        en.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
         switch (v.getId()){
             case R.id.cn:
                 if (LocaleUtils.needUpdateLocale(getContext(), LocaleUtils.LOCALE_CHINESE)) {
                     LocaleUtils.updateLocale(getContext(), LocaleUtils.LOCALE_CHINESE);
                     restartAct();
                 }

                 break;
             case R.id.en:
                 if (LocaleUtils.needUpdateLocale(getContext(), LocaleUtils.LOCALE_ENGLISH)) {
                     LocaleUtils.updateLocale(getContext(), LocaleUtils.LOCALE_ENGLISH);
                     restartAct();
                 }

                 break;

         }
    }
private void restartAct(){
     Intent intent = new Intent(getActivity(), MainActivity.class);
     intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
     startActivity(intent);
     // 杀掉进程
     android.os.Process.killProcess(android.os.Process.myPid());
     System.exit(0);
    //清除Activity退出和进入的动画
    mActivity.overridePendingTransition(0,0);
}
}
