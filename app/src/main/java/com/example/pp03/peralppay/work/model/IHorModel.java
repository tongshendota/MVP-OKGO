package com.example.pp03.peralppay.work.model;

import com.example.pp03.peralppay.work.Bean.MenuBean;

import java.util.List;

/**
 * Created by pp03 on 2017/12/4.
 */

public interface IHorModel {
    List<MenuBean> getData(String pare);
    List<String> getHorData(String pare);
    List<MenuBean> getRestul(String pare);
}
