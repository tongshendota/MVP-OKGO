package com.example.pp03.peralppay.work.fragment;

import com.example.pp03.peralppay.work.Bean.MenuBean;
import com.example.pp03.peralppay.work.Bean.OrdenBean;

import java.util.List;

/**
 * Created by pp03 on 2017-12-14.
 */

public interface ITodayView {
    void onGetDataResult(Boolean result, int code, List<OrdenBean> pare);
}
