package com.example.pp03.peralppay.login.model;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.example.pp03.peralppay.GmApplication;
import com.example.pp03.peralppay.contacts.Contacts;
import com.example.pp03.peralppay.login.LoginActivity;
import com.example.pp03.peralppay.login.request.GetSecretReq;
import com.example.pp03.peralppay.login.request.LoginReq;
import com.example.pp03.peralppay.net.HttpRequestCallBack;
import com.example.pp03.peralppay.net.HttpUtils;
import com.example.pp03.peralppay.utils.MD5Util;
import com.example.pp03.peralppay.utils.SharedPreferencesUtil;
import com.example.pp03.peralppay.utils.ToastUtil;
import com.example.pp03.peralppay.utils.Url;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;

import org.json.JSONObject;

import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.Response;

import static android.R.attr.key;

/**
 * Created by pp03 on 2017/11/28.
 */
public class UserModel implements IUser {

    String name;
    String passwd;

    public UserModel(String name, String passwd) {
        this.name = name;
        this.passwd = passwd;
    }
    public UserModel(){

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPasswd() {
        return passwd;
    }
    /**
     * 校验邮箱
     *
     * @param email
     * @return 校验通过返回true，否则返回false
     */
    public static final String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    public static boolean isEmail(String email) {
        return Pattern.matches(REGEX_EMAIL, email);
    }
    /**
     * 校验手机号
     *
     * @param mobile
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isMobile(String mobile) {
        return Pattern.matches(REGEX_MOBILE, mobile);
    }

    @Override
    public int checkUserValidity(String name, String password) {
        if (TextUtils.isEmpty(name)) {
            ToastUtil.showCustomToast(".请输入手机或邮箱");
            return -1;
        }
        if (!(isMobile(name)||isEmail(name))) {
            ToastUtil.showCustomToast("请填写正确的手机号码或邮箱");
            return -1;
        }

        if (TextUtils.isEmpty(password)) {
            ToastUtil.showCustomToast("密码不能为空");
            return -1;
        }
        if (password.length() != 6) {
            ToastUtil.showCustomToast("请填写正确的密码长度");
            return -1;
        }
        return 0;
    }

}
