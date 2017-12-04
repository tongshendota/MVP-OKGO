package com.example.pp03.peralppay.work.presenter;

import java.util.HashMap;

/**
 * Created by pp03 on 2017/12/4.
 */

public interface IGetDataPresenter {
    void getData(String url, HashMap<String,String> map);
    void getData(String url);
    void getHoData(String url);
}
