package com.example.pp03.peralppay.login.request;

import android.text.TextUtils;

import com.example.pp03.peralppay.contacts.Contacts;
import com.example.pp03.peralppay.login.respbean.LoginBean;
import com.example.pp03.peralppay.net.BaseReq;
import com.example.pp03.peralppay.net.JsonParseUtil;


/**
 * Created by meixx on 2017/3/28.
 */

public class LoginReq extends BaseReq {

    private LoginBean bean;
    public LoginReq(String phoneNum,String pwd,String sectetKey){
        paramsMap.put(Contacts.KEY_USERNAME,phoneNum);
        paramsMap.put(Contacts.KEY_PASSWORD,pwd);
        paramsMap.put(Contacts.KEY_SECTETKEY,sectetKey);
    }
    @Override
    public String getInterfaceCtype() {
        return Contacts.INTERFACE_C_OFFLINE;
    }

    @Override
    public String getInterfaceMtype() {
        return Contacts.INTERFACE_LOGIN;
    }

    @Override
    protected void parseResponseResult(String data) {
        if (!TextUtils.isEmpty(data)){
            bean = JsonParseUtil.parseObject(data,LoginBean.class);
        }
    }

    public LoginBean getBean() {
        return bean;
    }
}
