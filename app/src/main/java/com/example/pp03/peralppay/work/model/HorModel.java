package com.example.pp03.peralppay.work.model;

import android.app.Activity;

import com.example.pp03.peralppay.work.Bean.MenuBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by pp03 on 2017/12/4.
 */

public class HorModel implements IHorModel{
    Activity activity;
         public HorModel(Activity activity){
             this.activity = activity;
         }

    @Override
    public List<MenuBean> getData(String pare) {
        List<MenuBean> list = new ArrayList<>();
        Random random = new Random();
        int a=random.nextInt(10);
        for(int i =0;i<20;i++){
            MenuBean  menuBean = new MenuBean();
            menuBean.setRemark("多辣");
            menuBean.setName("辣椒炒肉"+a);
            menuBean.setSummoneny(20.00+a);
            menuBean.setImg("http%3A%2F%2Fali.xinshipu.cn%2F20120703%2Foriginal%2F1341323816178.jpg");
            menuBean.setMoneny(20.00+a);
            menuBean.setSize(1);
            list.add(menuBean);
        }
        return list;
    }

    @Override
    public List<String> getHorData(String pare) {
        List<String> list = new ArrayList<>();
        for(int i = 0;i<10;i++){
            Random random = new Random();
            int a=random.nextInt(10);
            list.add("湘菜"+a);
        }
        return list;
    }

    @Override
    public List<MenuBean> getRestul(String pare) {
        return new ArrayList<>();
    }
}
