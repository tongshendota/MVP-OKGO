package com.example.pp03.peralppay.login.presenter;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.example.pp03.peralppay.GmApplication;
import com.example.pp03.peralppay.contacts.Contacts;
import com.example.pp03.peralppay.login.model.IUser;
import com.example.pp03.peralppay.login.model.UserModel;
import com.example.pp03.peralppay.login.request.GetSecretReq;
import com.example.pp03.peralppay.login.request.LoginReq;
import com.example.pp03.peralppay.login.view.ILoginView;
import com.example.pp03.peralppay.net.HttpRequestCallBack;
import com.example.pp03.peralppay.net.HttpUtils;
import com.example.pp03.peralppay.utils.MD5Util;
import com.example.pp03.peralppay.utils.SharedPreferencesUtil;
import com.example.pp03.peralppay.utils.ToastUtil;
import com.lzy.okgo.OkGo;

/**
 * Created by pp03 on 2017/11/28.
 */

public class ILoginPresenterCompl implements ILoginPresenter{
    ILoginView iLoginView;
    IUser user;
    Activity activity;

    public ILoginPresenterCompl(ILoginView iLoginView, Activity activity) {
        this.iLoginView = iLoginView;
        this.activity = activity;
        user = new UserModel();
    }
    @Override
    public void doLogin(final String name,final String password) {
        final int code = user.checkUserValidity(name, password);
        if (code == 0) {
            final GetSecretReq secretReq = new GetSecretReq();
            HttpUtils.getInstance().post(this, secretReq, new HttpRequestCallBack() {
                @Override
                public void onSuccess() {
                    if (secretReq.getBean()!=null){
                        String key = secretReq.getBean().key;
                        String sectetKey = secretReq.getBean().secretKey;
                        submitLogin(key,sectetKey,name,password);
                    }else{
                        iLoginView.onLoginResult(false, 400);
                    }
                }

                @Override
                public void onFail(int errcode, String msg) {
                    super.onFail(errcode, msg);
                    iLoginView.onLoginResult(false, 400);
                }
            });
        }

    }
    private void submitLogin(String key, String secretKey, final String phoneStr, String pwdStr) {

        final String md5Str = MD5Util.md5(pwdStr);
        String entryPwd = MD5Util.md5(md5Str+key);
        final LoginReq loginReq = new LoginReq(phoneStr,entryPwd,secretKey);
        HttpUtils.getInstance().post(this, loginReq, new HttpRequestCallBack(activity) {

            @Override
            public void onSuccess() {
                if (loginReq.getBean()!=null){
                    if (TextUtils.isEmpty(loginReq.getBean().lkey)){
                        ToastUtil.showCustomToast("登录失败");
                        iLoginView.onLoginResult(false, 400);
                    }else {
                        //1 保存登录态
                        SharedPreferencesUtil.saveData(GmApplication.getContext(), Contacts.KEY_LKEY,loginReq.getBean().lkey);
                        //2保存用户帐号密码

                        SharedPreferencesUtil.saveData(GmApplication.getContext(),Contacts.SP_FILE_KEY_USERNAME,phoneStr);
                        SharedPreferencesUtil.saveData(GmApplication.getContext(),Contacts.SP_FILE_KEY_PWD,md5Str);
                        //跳转主页
                        SharedPreferencesUtil.saveData(GmApplication.getContext(),Contacts.SP_FILE_KEY_ISAUTH,true);
                        iLoginView.onLoginResult(true, 200);
                    }
                }
            }

            @Override
            public void onFail(int errcode, String msg) {
                super.onFail(errcode, msg);
                iLoginView.onLoginResult(false, 400);
            }
        });
    }
    @Override
    public void setProgerssBarVisibility(int visibility) {
        iLoginView.onSetProgressBarVisibility(visibility);
    }
}
