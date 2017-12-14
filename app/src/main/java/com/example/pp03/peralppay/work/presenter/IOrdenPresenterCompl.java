package com.example.pp03.peralppay.work.presenter;

import android.app.Activity;

import com.example.pp03.peralppay.work.fragment.ITodayView;
import com.example.pp03.peralppay.work.model.IOrdenModel;
import com.example.pp03.peralppay.work.model.OrdenModel;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by pp03 on 2017-12-14.
 */

public class IOrdenPresenterCompl implements IOrdenPresenter{
    Activity activity;
    ITodayView iTodayView;
    IOrdenModel iOrdenModel;
    public IOrdenPresenterCompl( ITodayView iTodayView,Activity activity){
        this.activity = activity;
        this.iTodayView = iTodayView;
        iOrdenModel = new OrdenModel();

    }
    @Override
    public void getData(String url) {
        OkGo.get(url)     // 请求方式和请求url
                .tag(this)                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        iTodayView.onGetDataResult(true,200,iOrdenModel.getData(response.toString()));
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        iTodayView.onGetDataResult(true,200,iOrdenModel.getData(response.toString()));
                    }
                });
    }
}
