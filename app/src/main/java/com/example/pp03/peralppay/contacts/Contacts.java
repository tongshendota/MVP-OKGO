package com.example.pp03.peralppay.contacts;

import java.io.File;

/**
 * Created by pp03 on 2017/11/28.
 */

public class Contacts {
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_SECTETKEY = "secretKey";
    public static final String KEY_AUTHCODE = "authCode";
    public static final String KEY_MOBILE = "mobile";
    public static final String KEY_TYPE = "type";
    public static final String KEY_LKEY = "lkey";
    public static final String KEY_PAYNO = "payNo";
    public static final String BILL_DATA = "billdata";
    public static final String KEY_PAGESIZE = "pageSize";
    public static final String KEY_PAGE = "page";
    public static final String KEY_BILLDATE = "billDate";
    public static final String KEY_NEEDSTATINFO = "needStatInfo";
    public static final String KEY_STATE = "state";
    public static final String KEY_DEVICEID = "deviceId";
    public static final String KEY_PLATFORM = "platform";
    public static final String KEY_URL = "url";
    public static final String KEY_AMOUNT = "amount";
    public static final String KEY_OSTYPE = "osType";
    public static final String KEY_VERSIONCODE = "versionCode";

    public static final String KEY_ACCOUNTNUM = "accountnum";
    public static final String KEY_ISGUIDE_BACKCHECK = "is_guide_backcheck";


    /**SharedPreferences 本地文件名称**/
    public static final String SP_FILE_DEFAULT = "client_preferences";

    public static final String SP_FILE_KEY_USERNAME = "u_name";

    public static final String SP_FILE_KEY_PWD = "u_pwd";

    public static final String SP_FILE_KEY_DEVICEID = "u_deviceid";
    /**是否完善商户资料**/
    public static final String SP_FILE_KEY_ISAUTH = "is_auth";
    /**
     * 账户相关
     * */
    public static final String INTERFACE_C_ACCOUNT = "account";

    /**获取密码加密key**/
    public static final String INTERFACE_GETKEY = "getLoginSecret";


    /**
     * offline
     */
    public static final String INTERFACE_C_OFFLINE = "offline";
    /**注册**/
    public static final String INTERFACE_REGISTER = "regUser";
    /**登录**/
    public static final String INTERFACE_LOGIN = "login";
    /**商户注册信息**/
    public static final String INTERFACE_REGISTMERCHANT = "registMerchant";
    /**今日交易**/
    public static final  String INTERFACE_GETTODAYDEAL = "getTodayDeal";
    /**获取商户信息**/
    public static final String INTERFACE_GETMERCHAINTINFO = "getMerchantInfo";
    /***获取订单详情*/
    public static final String INTERFACE_GETDEALDETAIL = "getDealDetail";
    /**刷新订单状态**/
    public static final String INTERFACE_GETDEALSTATE = "getDealState";
    /**获取账单数据**/
    public static final String INTERFACE_GETDAYBILL = "getDayBill";
    /**通知开关设置**/
    public static final String INTERFACE_PUSHSWITCH = "pushSwitch";
    /**获取push开关状态**/
    public static final String INTERFACE_PUSHSWITCHSTATE = "getPushState";
    /**获取报表数据**/
    public static final String INTERFACE_GETREPORTDATA = "getReportData";
    /**设备号注册**/
    public static final String INTERFACE_REGISTDEVICEID = "registDevice";
    /**获取个人中心数据接口**/
    public static final String INTERFACE_GETUSERCENTERINFO = "getUserCenterInfo";
    /**提款记录列表**/
    public static final String INTERFACE_GETDRAWLIST = "getDrawList";
    /**银行卡认证**/
    public static final String INTERFACE_VERIFYBANK = "verifyBank";

    public static final String INTERFACE_CHECK_APP_VERSION = "checkAppUpdate";

    public static final String INTERFACE_GET_ACCOUNT_INFO = "getAccountInfo";

    /**
     * 商户上传图片
     */
    public static final String INTERFACE_C_MERCHANT = "merchant";

    public static final String INTERFACE_UPLOADFILE = "uploadFile";

    public static final String INTERFACE_READIMGFILE = "readImageFile";

    /**
     * 通用
     */
    public static final String INTERFACE_C_COMMON = "common";
    /**发送短信验证码**/
    public static final String INTERFACE_SENDAUTHCODE = "sendAuthCode";

    public static final int INTENT_FLAG_FILL_MERCHANT_INFO = 1000;
    public static final int INTENT_FLAG_FILL_MERCHANT_STEP1 = 1001;
    public static final int INTENT_FLAG_FILL_MERCHANT_STEP2 = 1002;
    public static final int INTENT_FLAG_FILL_MERCHANT_STEP3 = 1003;
    public static final int INTENT_FLAG_PICK_PIC = 10008;
    public static final int INTENT_FLAG_PICK_PIC_SHOP = 10010;

    /** 标识字符串 0 */
    public static final String COMM_NUM0 = "0";
    /** 标识字符串 1 */
    public static final String COMM_NUM1 = "1";
    /** 标识字符串 2 */
    public static final String COMM_NUM2 = "2";
    /** 标识字符串 3 */
    public static final String COMM_NUM3 = "3";
    /** 标识字符串 4 */
    public static final String COMM_NUM4 = "4";
    /** 标识字符串 5 */
    public static final String COMM_NUM5 = "5";
    /** 标识字符串 6 */
    public static final String COMM_NUM6 = "6";
    /** 标识字符串 7 */
    public static final String COMM_NUM7 = "7";
    /** 标识字符串 8 */
    public static final String COMM_NUM8 = "8";
    /** 标识字符串 9 */
    public static final String COMM_NUM9 = "9";
    /** 标识字符串 -1 */
    public static final String COMM_NUM_1 = "-1";
    /** 常用字符串 "" */
    public static final String COMM_NULLMARK = "";

    public static final String KEY_NEED_FILL_MERCHANTINFO = "is_need_fill_merchant_info";


    public final static String APP_DOWNLOAD_PATH = File.separatorChar + "peralppay";//下载目录
}