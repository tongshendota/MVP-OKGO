package com.example.pp03.peralppay.login.respbean;


import com.example.pp03.peralppay.net.ReflectBean;

/**
 * Created by Administrator on 2017/3/28.
 */

public class LoginBean extends ReflectBean {
    public int userId;
    public int login;
    public String lkey;
    public int isAuth;

    @Override
    public String toString() {
        return "LoginBean{" +
                "userId=" + userId +
                ", login=" + login +
                ", lkey='" + lkey + '\'' +
                '}';
    }
}
