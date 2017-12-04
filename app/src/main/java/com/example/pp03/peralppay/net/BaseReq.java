package com.example.pp03.peralppay.net;

import com.example.pp03.peralppay.utils.Url;


import java.util.HashMap;

/**
 * Created by meixx on 2017/3/27.
 */

public abstract class BaseReq {

    protected HashMap<String,String> paramsMap = new HashMap<>();
    /**获取c 类型**/
    public abstract String getInterfaceCtype();

    /**获取m 类型**/
    public abstract String getInterfaceMtype();

    protected abstract void parseResponseResult(String data);

    public String getRequestUrl(){
        return Url.Pay_url+"/index.php?c="+getInterfaceCtype()+"&m="+getInterfaceMtype();
    }

    public HashMap<String, String> getParamsMap() {
        return paramsMap;
    }
}
