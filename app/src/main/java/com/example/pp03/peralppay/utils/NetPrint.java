//package com.example.pp03.peralppay.utils;
//
///**
// * Created by pp03 on 2017/12/4.
// */
//
//import android.os.AsyncTask;
//
//import com.alibaba.fastjson.JSON;
//
//import org.json.JSONArray;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//import butterknife.internal.Utils;
//
///**
// * 网络打印机
// *
// *
// */
//class NetPrint extends AsyncTask<String, String, String> {
//    private String LocalYHID;
//    private List<SaveEnity> ListWz = new ArrayList<SaveEnity>();
//    private String Rr = "";
//    private String GysName = "";
//    private String title = "";
//    private String printtime = "";
//    private String errInfo = "";
//    private String TotlePrice = "";
//    private NetPrinter printer;
//    private String Md_Name;
//    private String Date_Time;
//
//    public NetPrint(String LocalYHID, String GysName,String Date_Time) {
//        this.LocalYHID = LocalYHID;
//        this.GysName = GysName;
//        this.Date_Time = Date_Time;
//        isclick = true;
//    }
//
//    @Override
//    protected String doInBackground(String... params) {
//        // TODO Auto-generated method stub
//        try {
//            printer = new NetPrinter();
//            if(!Utils.isEmpty(Const.print_Port)){
//                printer.Open(Const.print_IP, Integer.parseInt(Const.print_Port));
//            }
//            if(printer.IFOpen){
//                JSONArray ArrayItems = new JSONArray();
//                ArrayItems = usedatabase.getLocalData(LocalYHID);
//                TotlePrice = usedatabase.getTotlePrice(LocalYHID);
//                ListWz = JSON.parseArray(ArrayItems.toString(), SaveEnity.class);
//                MyLogger.showLogWithLineNum(5, ListWz.size() + "长度");
//                Collections.sort(ListWz, new SaComParator());
//
//                List<String> Select = new ArrayList<String>();
//                List<String> EndSelect = new ArrayList<String>();
//                for (int i = 0; i < ListWz.size(); i++) {
//                    SaveEnity save = ListWz.get(i);
//                    Select.add(save.DJX_CK_NAME);
//                }
//                for (String s : Select) {
//                    if (Collections.frequency(EndSelect, s) < 1) EndSelect.add(s);
//                }
//                for (int i = 0; i < EndSelect.size(); i++) {
//                    Rr += formatSSTest(EndSelect.get(i), ListWz);
//                }
//                String Name = "物料名";
//                String Num = "数量";
//                String Unit = "单位";
//                String Price = "单价";
//                String Spc = "";
//                for (int j = 0; j < wlname - getWordCount("物料名"); j++) {
//                    Spc = Spc + " ";
//                }
//                Log.e("物料名称长度"+wlname+"补空格", wlname-getWordCount("物料名")+"");
//                Name = Name + Spc;
//                Spc = "";
//                for (int i = 0; i < wlnum - getWordCount("数量"); i++) {
//                    Spc = Spc + " ";
//                }
//                Num = Spc + Num;
//                Log.e("数量长度"+wlnum+"补空格", wlnum-getWordCount("数量")+"");
//                Spc = "";
//
//                for (int i = 0; i < wlunit - getWordCount("单位"); i++) {
//                    Spc = Spc + " ";
//                }
//                Unit = Spc + Unit;
//                Spc = "";
//                Log.e("单位长度"+wlunit+"补空格", wlunit-getWordCount("单位")+"");
//                for (int i = 0; i < wlprice - getWordCount("单价"); i++) {
//                    Spc = Spc + " ";
//                }
//                Price = Spc + Price;
//                Spc = "";
//                Log.e("长度"+wlprice+"补空格", wlprice-getWordCount("单价")+"");
//                String printstr = Name + Price + Num + Unit;
//                title = "供应商: " + GysName + "\n" + printstr;
//                printtime = "打印时间:" + Date_Time + "\n\n";
//                Md_Name   = Const.MD_Name;
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    protected void onPreExecute() {
//        // TODO Auto-generated method stub
//        super.onPreExecute();
//    }
//
//    @Override
//    protected void onPostExecute(String result) {
//        // TODO Auto-generated method stub
//        super.onPostExecute(result);
//        if(printer.IFOpen){
//            String[] aaray = Rr.split("#");
//            printer.PrintText(title, 0, 0, 0);
//            printer.PrintEnter();
//            for (String s : aaray) {
//                printer.PrintText(s, 0, 0, 0);
//            }
//            printer.PrintEnter();
//            printer.PrintText(TotlePrice, 0, 0, 0);
//            printer.PrintEnter();
//            printer.PrintEnter();
//            printer.PrintText(printtime, 0, 0, 0);
//            printer.PrintEnter();
//            printer.PrintText(Md_Name, 1,1,0);
//            printer.PrintNewLines(5);
//            printer.CutPage(0);
//            printer.Close();
//            isclick = false;
//            MyToast.MyLogo(History.this, "打印完成");
//        }else{
//            MyToast.MyLogo(History.this, "未能连接打印机");
//        }
//    }
//}