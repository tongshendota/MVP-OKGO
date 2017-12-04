package com.example.pp03.peralppay.utils;

import android.app.Application;
import android.os.Handler;

/**
 * Created by pp03 on 2017/11/13.
 */

public class My_Handler extends Application {
    // 共享变量
    private Handler handler = null;

    // set方法
    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    // get方法
    public Handler getHandler() {
        return handler;
    }

}
