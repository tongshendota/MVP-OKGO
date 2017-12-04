package com.example.pp03.peralppay.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.example.pp03.peralppay.R;
import com.wang.avi.AVLoadingIndicatorView;

/**
 * Created by pp03 on 2017/11/21.
 */

public class Dialog_Until extends Dialog{
      private AVLoadingIndicatorView loading;
      private TextView messageTv;
    private String messageStr;//从外界设置的消息文本



    public Dialog_Until(Context context) {
        super(context, R.style.MyDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_until);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);

        //初始化界面控件
        initView();
        //初始化界面数据
        initData();

    }



    /**
     * 初始化界面控件的显示数据
     */
    private void initData() {
        //如果用户自定了title和message
        if (messageStr != null) {
            messageTv.setText(messageStr);
        }
    }

    /**
     * 初始化界面控件
     */
    private void initView() {
        messageTv = (TextView) findViewById(R.id.message);
        loading = (AVLoadingIndicatorView)findViewById(R.id.loading);

    }
    public  void setmessage(String message){
        messageTv.setText(message);
    }
    /**
     * 从外界Activity为Dialog设置dialog的message
     *
     * @param message
     */
    public void setMessage(String message) {
        messageStr = message;
    }

    /**
     * 设置确定按钮和取消被点击的接口
     */
    public interface onYesOnclickListener {
        public void onYesClick();
    }

    public interface onNoOnclickListener {
        public void onNoClick();
    }
}
