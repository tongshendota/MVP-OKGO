package com.example.pp03.peralppay.net;

import android.app.Activity;

import com.example.pp03.peralppay.utils.Dialog_Until;


/**
 * Created by meixx on 2017/3/29.
 */

public abstract class HttpRequestCallBack {
    private Dialog_Until mloadingDialog;
    private Activity mActivity;
    public HttpRequestCallBack(Activity activity){
       mActivity = activity;
    }
    public HttpRequestCallBack(){
        mActivity = null;
    }
    public abstract void onSuccess();
    public void onFail(int errcode,String msg){

    }
    public void onBefore(){
        if (mActivity!=null&&!mActivity.isFinishing()) {
            mloadingDialog = new Dialog_Until(mActivity);
            mloadingDialog.show();
        }
    };
    public void onAfter(){
        if (mloadingDialog!=null&&mloadingDialog.isShowing()) {
            mloadingDialog.dismiss();
        }
    };

    public boolean needShowErrorTip(){
        return true;
    }
}
