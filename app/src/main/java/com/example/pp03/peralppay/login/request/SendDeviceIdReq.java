package com.example.pp03.peralppay.login.request;

import android.text.TextUtils;

import com.example.pp03.peralppay.GmApplication;
import com.example.pp03.peralppay.contacts.Contacts;
import com.example.pp03.peralppay.net.BaseReq;
import com.example.pp03.peralppay.utils.LogUtils;


/**
 * Created by Administrator on 2017/4/3.
 */

public class SendDeviceIdReq extends BaseReq {

    public SendDeviceIdReq(){
        paramsMap.put(Contacts.KEY_LKEY, GmApplication.getLkey());
        paramsMap.put(Contacts.KEY_DEVICEID, "");
        paramsMap.put(Contacts.KEY_PLATFORM,Contacts.COMM_NUM1);
    }

    @Override
    public String getInterfaceCtype() {
        return Contacts.INTERFACE_C_OFFLINE;
    }

    @Override
    public String getInterfaceMtype() {
        return Contacts.INTERFACE_REGISTDEVICEID;
    }

    @Override
    protected void parseResponseResult(String data) {
        if (!TextUtils.isEmpty(data)){
            LogUtils.e("deviceid regist:","regist ok-------->");
        }else{
            LogUtils.e("deviceid regist:","regist fail-------->");
        }
    }
}
