package com.example.pp03.peralppay.login.request;

import android.text.TextUtils;

import com.example.pp03.peralppay.contacts.Contacts;
import com.example.pp03.peralppay.login.respbean.GetSecretBean;
import com.example.pp03.peralppay.net.BaseReq;
import com.example.pp03.peralppay.net.JsonParseUtil;


/**
 * Created by Administrator on 2017/3/28.
 */

public class GetSecretReq extends BaseReq {
    private GetSecretBean bean;
    @Override
    public String getInterfaceCtype() {
        return Contacts.INTERFACE_C_ACCOUNT;
    }

    @Override
    public String getInterfaceMtype() {
        return Contacts.INTERFACE_GETKEY;
    }

    @Override
    protected void parseResponseResult(String data) {
        if (!TextUtils.isEmpty(data)){
            bean  = JsonParseUtil.parseObject(data,GetSecretBean.class);
        }
    }

    public GetSecretBean getBean() {
        return bean;
    }
}
