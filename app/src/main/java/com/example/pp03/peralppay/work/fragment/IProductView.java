package com.example.pp03.peralppay.work.fragment;

import com.example.pp03.peralppay.work.Bean.MenuBean;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by pp03 on 2017/12/4.
 */

public interface IProductView {

    void onGetDataResult(Boolean result, int code, List<MenuBean> pare);
    void onGetHoDataResult(Boolean result, int code, List<String> pare);
}
