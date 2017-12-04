package com.example.pp03.peralppay.work.fragment;

import org.json.JSONObject;

/**
 * Created by pp03 on 2017/12/4.
 */

public interface IProductView {

    void onGetDataResult(Boolean result, int code, String pare);
    void onGetHoDataResult(Boolean result, int code, String pare);
}
