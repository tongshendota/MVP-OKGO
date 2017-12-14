package com.example.pp03.peralppay.work.model;

import com.example.pp03.peralppay.work.Bean.OrdenBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pp03 on 2017-12-14.
 */

public class OrdenModel implements IOrdenModel{
    @Override
    public List<OrdenBean> getData(String pare) {
            List<OrdenBean> list = new ArrayList<>();
        for(int i =1;i<20;i++){
            OrdenBean ordenBean = new OrdenBean();
            ordenBean.setNum(i+"");
            ordenBean.setState("已支付");
            ordenBean.setModel("现金");
            ordenBean.setSize(i+"");
            ordenBean.setSum(20*i+"");
            ordenBean.setTime("9:01");
            list.add(ordenBean);
        }
        return list;
    }
}
