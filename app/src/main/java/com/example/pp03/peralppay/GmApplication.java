package com.example.pp03.peralppay;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;


import com.example.pp03.peralppay.contacts.Contacts;
import com.example.pp03.peralppay.utils.LocaleUtils;
import com.example.pp03.peralppay.utils.SharedPreferencesUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.store.PersistentCookieStore;

import java.lang.reflect.Field;
import java.util.Locale;
import java.util.logging.Level;

/**
 * Created by meixx on 2017/3/26.
 */

public class GmApplication extends Application {

    private static final String TAG = "GmApplication";
    public static Typeface typeFace;
    private static Context mContext;
    private static String lkey;
    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();
        initOkHttp();

//        initCloudChannel();
        getLkey();
        Locale _UserLocale= LocaleUtils.getUserLocale(mContext);
        LocaleUtils.updateLocale(this, _UserLocale);
    }
    public void setTypeface(){
        //华文彩云，加载外部字体assets/front/huawen_caiyun.ttf
        typeFace = Typeface.createFromAsset(getAssets(), "fonts/Arial.ttf");
        try
        {
            //与values/styles.xml中的<item name="android:typeface">sans</item>对应
//            Field field = Typeface.class.getDeclaredField("SERIF");
//            field.setAccessible(true);
//            field.set(null, typeFace);

//            Field field_1 = Typeface.class.getDeclaredField("DEFAULT");
//            field_1.setAccessible(true);
//            field_1.set(null, typeFace);

            //与monospace对应
//            Field field_2 = Typeface.class.getDeclaredField("MONOSPACE");
//            field_2.setAccessible(true);
//            field_2.set(null, typeFace);

            //与values/styles.xml中的<item name="android:typeface">sans</item>对应
            Field field_3 = Typeface.class.getDeclaredField("SANS_SERIF");
            field_3.setAccessible(true);
            field_3.set(null, typeFace);
        }
        catch (NoSuchFieldException e)
        {
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
    }
//    /**
//     * 初始化阿里云
//     */
//    private void initCloudChannel() {
//        LogUtils.e(TAG,"=============");
//        PushServiceFactory.init(this);
//
//        CloudPushService pushService = PushServiceFactory.getCloudPushService();
//        pushService.register(this, new CommonCallback() {
//            @Override
//            public void onSuccess(String response) {
//                LogUtils.d(TAG, "init cloudchannel success");
//            }
//            @Override
//            public void onFailed(String errorCode, String errorMessage) {
//                LogUtils.d(TAG, "init cloudchannel failed -- errorcode:" + errorCode + " -- errorMessage:" + errorMessage);
//            }
//        });
//    }

    /**
     * 初始化网络配置
     */
    private void initOkHttp() {
        //必须调用初始化
        OkGo.init(this);
        try {
            //以下都不是必须的，根据需要自行选择,一般来说只需要 debug,缓存相关,cookie相关的 就可以了
            OkGo.getInstance()
                    // 打开该调试开关,打印级别INFO,并不是异常,是为了显眼,不需要就不要加入该行
                    // 最后的true表示是否打印okgo的内部异常，一般打开方便调试错误
                    .debug("OkGo", Level.INFO, true)
                    //如果使用默认的 60秒,以下三行也不需要传
                    .setConnectTimeout(OkGo.DEFAULT_MILLISECONDS)  //全局的连接超时时间
                    .setReadTimeOut(OkGo.DEFAULT_MILLISECONDS)     //全局的读取超时时间
                    .setWriteTimeOut(OkGo.DEFAULT_MILLISECONDS)    //全局的写入超时时间
                    //可以全局统一设置缓存模式,默认是不使用缓存,可以不传,具体其他模式看 github 介绍 https://github.com/jeasonlzy/
                    .setCacheMode(CacheMode.NO_CACHE)
                    //可以全局统一设置缓存时间,默认永不过期,具体使用方法看 github 介绍
                    .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)
                    //可以全局统一设置超时重连次数,默认为三次,那么最差的情况会请求4次(一次原始请求,三次重连请求),不需要可以设置为0
                    .setRetryCount(0)
                    //如果不想让框架管理cookie（或者叫session的保持）,以下不需要
//              .setCookieStore(new MemoryCookieStore())            //cookie使用内存缓存（app退出后，cookie消失）
                    .setCookieStore(new PersistentCookieStore())        //cookie持久化存储，如果cookie不过期，则一直有效
                    //可以设置https的证书,以下几种方案根据需要自己设置
                    .setCertificates();                                //方法一：信任所有证书,不安全有风险

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Context getContext() {
        return mContext;
    }

    public static String getLkey(){
        if (TextUtils.isEmpty(lkey)){

          Object data= SharedPreferencesUtil.getData(getContext(),Contacts.KEY_LKEY,"1");
            lkey = (String)data;
        }
        return lkey;
    }

    public static void setLkey(String ck){
        lkey = ck;
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Locale _UserLocale=LocaleUtils.getUserLocale(mContext);
        //系统语言改变了应用保持之前设置的语言
        if (_UserLocale != null) {
            Locale.setDefault(_UserLocale);
            Configuration _Configuration = new Configuration(newConfig);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                _Configuration.setLocale(_UserLocale);
            } else {
                _Configuration.locale =_UserLocale;
            }
            getResources().updateConfiguration(_Configuration, getResources().getDisplayMetrics());
        }
    }
}
