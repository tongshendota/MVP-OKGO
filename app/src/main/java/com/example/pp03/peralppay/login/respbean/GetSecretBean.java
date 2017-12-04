package com.example.pp03.peralppay.login.respbean;


import com.example.pp03.peralppay.net.ReflectBean;

/**
 * Created by Administrator on 2017/3/28.
 */

public class GetSecretBean extends ReflectBean {
    public String key;
    public String secretKey;

    @Override
    public String toString() {
        return "GetSecretBean{" +
                "key='" + key + '\'' +
                '}';
    }
}
