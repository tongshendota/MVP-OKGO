package com.example.pp03.peralppay.work.presenter;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.example.pp03.peralppay.login.model.UserModel;
import com.example.pp03.peralppay.login.view.ILoginView;
import com.example.pp03.peralppay.utils.LogUtils;
import com.example.pp03.peralppay.work.Bean.MenuBean;
import com.example.pp03.peralppay.work.fragment.IProductView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by pp03 on 2017/12/4.
 */

public class IGetDataPresenterCompl implements IGetDataPresenter {
    Activity activity;
    IProductView productView;
    public IGetDataPresenterCompl(IProductView iLoginView, Activity activity) {
        this.productView = iLoginView;
        this.activity = activity;
    }
    @Override
    public void getData(String url, HashMap<String, String> map) {
        OkGo.get(url)     // 请求方式和请求url
                .tag(this)                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        productView.onGetDataResult(true,200,s);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        productView.onGetDataResult(true,200,"");
                    }
                });
    }

    @Override
    public void getData(String url) {
        productView.onGetDataResult(true,200,url);
//        OkGo.get(url)     // 请求方式和请求url
//                .tag(this)                       // 请求的 tag, 主要用于取消对应的请求
//                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
//                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(String s, Call call, Response response) {
//
//                    }
//
//                    @Override
//                    public void onError(Call call, Response response, Exception e) {
//                        super.onError(call, response, e);
//                    }
//                });
    }

    @Override
    public void getHoData(String url) {
        productView.onGetHoDataResult(true,200,url);
    }
}
