package com.example.pp03.peralppay.utils;


import com.example.pp03.peralppay.GmApplication;

/**
 * Created by pp03 on 2017/11/14.
 */

public class Url {
    public static String Pay_url = "https://api.peralppay.com";
    public static String getPay_url(String payAmount, String payType, String authCode){
        return Pay_url+"/api/offline/create_order_merch?payAmount="+payAmount+"&payType="+payType+"&lkey="+ GmApplication.getLkey()+"&authCode="+authCode;
    }
    public static String get_Report(String type){
        return Pay_url+"/index.php?c=offline&m=getReportData&reportType="+type+"&lkey="+GmApplication.getLkey();
    }
    public static String get_Pay_News(String pay_no){
        return Pay_url+"/index.php?c=offline&m=getDealState&lkey="+GmApplication.getLkey()+"&payNo="+pay_no;
    }
    public static String get_getDrawList(){
     return  Pay_url+"/index.php?c=offline&m=getDrawList&lkey="+GmApplication.getLkey();
    }
    public static String get_getMonDrawList(){
        return  Pay_url+"/index.php?c=offline&m=getDrawList&lkey="+GmApplication.getLkey()+"&type=4";
    }

}
